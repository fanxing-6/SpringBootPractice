package com.fx.websocketmodule.config;

import com.fx.websocketmodule.handler.SpringWebSocketHandler;
import com.fx.websocketmodule.interceptor.MyHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {

        registry.addHandler( new SpringWebSocketHandler(),"/websocket/**")
                .setAllowedOrigins("*")// 允许跨域
                .addInterceptors(new MyHandshakeInterceptor());
    }
}
