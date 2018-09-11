package com.goldwarehouse.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.userId = userId;
        log.info("Open new connection from user: {}", userId);
    }

    @OnClose
    public void onClose() {
        log.info("Connection closed for user: {}", userId);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("Received message: {} from user: {}", message, userId);
        session.getBasicRemote().sendText("Response for request from user: " + userId);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Exception happens for user: {}", userId);
        error.printStackTrace();
    }
}
