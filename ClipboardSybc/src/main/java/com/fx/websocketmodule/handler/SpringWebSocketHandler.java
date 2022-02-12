package com.fx.websocketmodule.handler;

import ch.qos.logback.classic.spi.ClassPackagingData;
import com.alibaba.fastjson.JSON;
import com.fx.usermanagementmodule.domain.ResponseResult;
import com.fx.usermanagementmodule.enums.AppHttpCodeEnum;
import com.fx.usermanagementmodule.utils.JwtUtil;
import com.fx.usermanagementmodule.utils.RedisCache;
import com.fx.websocketmodule.domain.entity.Clip;
import com.fx.websocketmodule.domain.entity.Device;
import com.fx.websocketmodule.service.ClipService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;

@Component
public class SpringWebSocketHandler implements WebSocketHandler
{
    private static ClipService clipService;

    @Autowired
    public void setClipService(ClipService clipService)
    {
        SpringWebSocketHandler.clipService = clipService;
    }

    private static RedisCache redisCache;

    @Autowired
    public void setRedisCache(RedisCache redisCache)
    {
        SpringWebSocketHandler.redisCache = redisCache;
    }

    private static final Map<String, WebSocketSession> usersSessions;

    static
    {
        usersSessions = new HashMap<>();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {

        //成功建立链接之后,保存用户和设备信息
        String userId = (String) session.getAttributes().get("userId");
        String deviceId = (String) session.getAttributes().get("deviceId");
        String deviceName = (String) session.getAttributes().get("deviceName");
        Device device = new Device(userId, deviceId, deviceName);
        String redisKey = "userId:" + userId + ":device:" + deviceId;

        // 用户id 设备id作为建放到Map中
        usersSessions.put(redisKey, session);
        redisCache.setCacheObject(redisKey, device);
        TextMessage textMessage = new TextMessage(JSON.toJSONString(ResponseResult.okResult("设备注册成功")));
        session.sendMessage(textMessage);

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception
    {
        String userId = (String) session.getAttributes().get("userId");
        Clip clip = JSON.parseObject(message.getPayload().toString(), Clip.class);
        clip.setUserId(Long.valueOf(userId));
        // 同步消息到所有设备
        if (clip.getSyncedFlag() == 1)
        {
            Set<String> keySet = usersSessions.keySet();
            for (String key : keySet)
            {
                if (key.startsWith("userId" + userId))
                {
                    broadcastMessage(clip, usersSessions.get(key));
                }
            }
        }
        // 获取收藏内容
        if (clip.getFavoriteFlag() == 1)
        {
            getFavoriteMessage(session);
        }

    }

    public void getFavoriteMessage(WebSocketSession session) throws IOException
    {
        List<Clip> clips = clipService.getFavorite();
        session.sendMessage(new
                TextMessage(JSON.toJSONString(ResponseResult.okResult(clips))));
    }


    public void saveMessage(Clip clip, WebSocketSession session) throws IOException
    {
        clipService.save(clip);
        session.sendMessage(new
                TextMessage(JSON.toJSONString(ResponseResult.okResult("保存成功"))));
    }

    public void broadcastMessage(Clip clip, WebSocketSession session) throws IOException
    {
        clipService.save(clip);
        session.sendMessage(new TextMessage(JSON.toJSONString(ResponseResult.okResult(clip))));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception
    {
        String userId = (String) session.getAttributes().get("userId");
        String deviceId = (String) session.getAttributes().get("deviceId");
        String redisKey = "userId:" + userId + ":device:" + deviceId;
        usersSessions.remove(redisKey);
        redisCache.deleteObject(redisKey);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception
    {
        String userId = (String) session.getAttributes().get("userId");
        String deviceId = (String) session.getAttributes().get("deviceId");
        String redisKey = "userId:" + userId + ":device:" + deviceId;
        usersSessions.remove(redisKey);
        redisCache.deleteObject(redisKey);
    }

    @Override
    public boolean supportsPartialMessages()
    {
        return false;
    }
}
