package me.zhengjie.modules.mskj.websocket.common;

import lombok.RequiredArgsConstructor;
import me.zhengjie.utils.RedisUtils;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class Cache {
    private final RedisUtils redisUtils;

    /**
     * 更新机器人在线状态
     * 10s钟过期
     *
     * @param robotId - 用于生成cache的key
     * @param location - heartbeat上报的机器人location
     */
    public void updateRobotOnlineStatus(String robotId, String location) {
        redisUtils.set(getRobotOnlineStatusKey(robotId), location, 10);
    }

    private String getRobotOnlineStatusKey(String robotId) {
        return MessageFormat.format("robot:{0}:online", robotId);
    }
}
