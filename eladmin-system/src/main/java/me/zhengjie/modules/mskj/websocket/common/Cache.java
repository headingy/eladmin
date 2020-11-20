package me.zhengjie.modules.mskj.websocket.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
@Slf4j
public class Cache {
    private final RedisUtils redisUtils;

    @Value("${mskj.cache.expire.robotOnlineStatus}")
    int robotOnlineStatusExpire;
    @Value("${mskj.cache.expire.robotDeviceStatus}")
    int robotDeviceStatusExpire;

    /**
     * 更新机器人在线状态
     * 10s钟过期
     *
     * @param robotId  - 用于生成cache的key
     * @param location - heartbeat上报的机器人location
     */
    public void updateRobotOnlineStatus(String robotId, String location) {
        String key = getRobotOnlineStatusKey(robotId);
        log.debug("cache {} set to {}, expire in {} seconds", key, location, robotOnlineStatusExpire);
        redisUtils.set(key, location, robotOnlineStatusExpire);
    }

    private String getRobotOnlineStatusKey(String robotId) {
        return MessageFormat.format("robot:{0}:online", robotId);
    }

    public void updateRobotDeviceStatus(String robotId, String status) {
        String key = getRobotDeviceStatusKey(robotId);
        log.debug("cache {} set to {}, expire in {} seconds", key, status, robotDeviceStatusExpire);
        redisUtils.set(key, status, robotDeviceStatusExpire);
    }

    private String getRobotDeviceStatusKey(String robotId) {
        return MessageFormat.format("robot:{0}:status", robotId);
    }
}
