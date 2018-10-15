package com.goldwarehouse.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class WebSocketClient {

    private static final Logger log = LoggerFactory.getLogger(WebSocketClient.class);

    private static final String URI_STR = "ws://localhost/websocket/davidguo";

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        log.info("Client connection open");
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("Client received message: {}", message);
    }

    @OnClose
    public void onClose() {
        log.info("Client connection closed");
    }

    private void start() {
        WebSocketContainer container = null;
        try {
            container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(WebSocketClient.class, URI.create(URI_STR));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            session.getBasicRemote().sendText("Hello World");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient();
        client.start();
        client.sendMessage();
    }
}
