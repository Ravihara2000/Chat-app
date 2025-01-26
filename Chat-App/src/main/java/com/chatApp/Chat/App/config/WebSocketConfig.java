package com.chatApp.Chat.App.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //Register STOMP Endpoint.This method registers the WebSocket endpoint that clients will connect to.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/landon-chat-app").withSockJS();// Enables fallback options for browsers that don’t support WebSocket (using SockJS).

    }
//Configure Message Broker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
    // Enables a simple in-memory message broker. Messages sent to destinations prefixed with /topic will be broadcast to all connected clients subscribed to that topic.
    //Messages sent to destinations prefixed with /app will be routed to @MessageMapping methods in your controllers.
}
