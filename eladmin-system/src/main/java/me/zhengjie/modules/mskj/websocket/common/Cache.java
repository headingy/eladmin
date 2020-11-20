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
    @Value("${mskj.cache.expire.robotEnvironmentStatus}")
    int robotEnvironmentStatusExpire;
    @Value("${mskj.cache.expire.robotHardwareStatus}")
    int robotHardwareStatusExpire;

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

    public void updateRobotEnvironmentStatus(String robotId, String status) {
        String key = getRobotEnvironmentStatusKey(robotId);
        log.debug("cache {} set to {}, expire in {} seconds", key, status, robotEnvironmentStatusExpire);
        redisUtils.set(key, status, robotEnvironmentStatusExpire);
    }

    private String getRobotEnvironmentStatusKey(String robotId) {
        return MessageFormat.format("robot:{0}:environ", robotId);
    }

    public void updateRobotHardwareStatus(String robotId, String status) {
        String key = getRobotHardwareStatusKey(robotId);
        log.debug("cache {} set to {}, expire in {} seconds", key, status, robotHardwareStatusExpire);
        redisUtils.set(key, status, robotHardwareStatusExpire);
    }

    private String getRobotHardwareStatusKey(String robotId) {
        return MessageFormat.format("robot:{0}:hardware", robotId);
    }
}