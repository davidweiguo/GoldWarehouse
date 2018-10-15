package com.goldwarehouse.websocket;

import com.goldwarehouse.bean.Echo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author guo_d
 * @date 2018/10/15
 */
public class WebSocketServiceClient {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServiceClient.class);

    private final int size = 50 * 1024 * 1024;

    private final int reconnectTime = 5000;

    private String url;

    private boolean isConnected = true;

    private List<Transport> transports;

    private WebSocketHttpHeaders wsHeaders;

    private StompSessionHandler sessionHandler;

    private List<WebSocketExtension> extensions;

    private StompSession stompSession;

    private Subject<Echo> echoSubject;

    public WebSocketServiceClient(String url) {
        this.url = url;
        transports = new ArrayList<>(1);
        CountDownLatch latch = new CountDownLatch(1);
        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
        webSocketContainer.setDefaultMaxTextMessageBufferSize(size);
        webSocketContainer.setDefaultMaxBinaryMessageBufferSize(size);

        WebSocketTransport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient(webSocketContainer));
        transports.add(webSocketTransport);
        org.springframework.web.socket.client.WebSocketClient webSocketClient = new SockJsClient(transports);

        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setInboundMessageSizeLimit(size);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new DefaultManagedTaskScheduler());

        extensions = new ArrayList<>();
        extensions.add(new WebSocketExtension("permessage-deflate"));
        extensions.add(new WebSocketExtension("client_max_window_bits"));

        wsHeaders = new WebSocketHttpHeaders();
        wsHeaders.setSecWebSocketExtensions(extensions);
        wsHeaders.set("Accept-Encoding", "gzip, deflate, sdch");

        sessionHandler = new MyStompSessionHandler(latch);
        stompClient.connect(url, wsHeaders, sessionHandler);
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyStompSessionHandler extends StompSessionHandlerAdapter {
        private CountDownLatch latch;

        MyStompSessionHandler(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            stompSession = session;
            session.setAutoReceipt(true);
            logger.info("connectedHeaders: {}", connectedHeaders);

            StompFrameHandlerImplement echoHandler = new StompFrameHandlerImplement(Echo.class);
            stompSession.subscribe("/user/queue/echoResponse", echoHandler);
            latch.countDown();
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            logger.error("handleTransportError", exception);

            if (exception instanceof ConnectionLostException || !isConnected) {
                // if connection lost, call this
                isConnected = false;
                while (!isConnected) {
                    try {
                        Thread.sleep(reconnectTime);

                        WebSocketClient webSocketClient = new SockJsClient(transports);
                        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
                        stompClient.setInboundMessageSizeLimit(size);
                        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
                        // for heartbeats
                        stompClient.setTaskScheduler(new DefaultManagedTaskScheduler());
                        stompClient.connect(url, wsHeaders, sessionHandler);

                        isConnected = true;
                    } catch (Exception e) {
                        logger.error("error {}", e.getMessage());
                    }
                }
            }
        }
    }

    private class StompFrameHandlerImplement implements StompFrameHandler {

        private Class clz;

        public StompFrameHandlerImplement(Class clz) {
            this.clz = clz;
        }

        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return this.clz;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            logger.info("StompHeaders: {}", stompHeaders);

            if (o instanceof Echo) {
                echoSubject.onNext((Echo) o);
                echoSubject.onComplete();
            }
        }
    }

    public Subject<Echo> getEchoResponse(String name) {
        echoSubject = PublishSubject.<Echo>create().toSerialized();
        stompSession.send("/app/echo", name);
        return echoSubject;
    }

    public static void main(String[] args) {
        WebSocketServiceClient client = new WebSocketServiceClient("ws://localhost:8080/base");
        client.getEchoResponse("Trump").subscribe(new Observer<Echo>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(Echo echo) {
                System.out.println(echo.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
