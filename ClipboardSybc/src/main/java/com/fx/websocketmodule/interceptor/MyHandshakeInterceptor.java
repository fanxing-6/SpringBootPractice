package com.fx.websocketmodule.interceptor;


import com.fx.usermanagementmodule.domain.entity.LoginUser;
import com.fx.usermanagementmodule.utils.JwtUtil;
import com.fx.usermanagementmodule.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Component
public class MyHandshakeInterceptor implements HandshakeInterceptor
{
    private static RedisCache redisCache;

    @Autowired
    public void setRedisCache(RedisCache redisCache)
    {
        MyHandshakeInterceptor.redisCache = redisCache;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception
    {
        if (request instanceof ServletServerHttpRequest)
        {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            String token = httpServletRequest.getParameter("token");
            String deviceId = httpServletRequest.getParameter("device_id");
            String deviceName = httpServletRequest.getParameter("name");
            //todo 验证其他参数上传格式 格式不对 返回false
            System.out.println("===============================================================");
            System.out.println("token:"+ token);
            System.out.println("deviceId"+ deviceId);
            System.out.println("deviceName"+deviceName);
            if (!StringUtils.hasText(deviceId) || deviceId.length() != 8) return false;
            if (!StringUtils.hasText(token))
            {
                return false;
            }
            Claims claims = null;
            try
            {
                claims = JwtUtil.parseJWT(token);
            } catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            String userId = claims.getSubject();
            //从redis中获取用户信息

            LoginUser loginUser = redisCache.getCacheObject("login:" + userId);
            //如果获取不到
            if (Objects.isNull(loginUser))
            {
                return false;
            }
            // 成功取到 用户 那就保存其他参数
            attributes.put("userId", loginUser.getUser().getId().toString());
            attributes.put("deviceId",deviceId);
            attributes.put("deviceName",deviceName);
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception)
    {
    }
}