package com.goldwarehouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author guo_d
 * @date 2018/10/15
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 为客户提供服务接入端，并支持 SockJS
        registry.addEndpoint("/base").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 服务端发送协议前缀，P2P：/user/queue，发布订阅：/topic
        config.enableSimpleBroker("/queue", "/topic");
        // 客户端请求前缀
        config.setApplicationDestinationPrefixes("/app");
    }
}
