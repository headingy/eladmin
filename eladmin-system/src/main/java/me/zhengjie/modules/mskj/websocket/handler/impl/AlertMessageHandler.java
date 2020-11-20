package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class AlertMessageHandler implements MessageHandler {
    final Cache cache;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        cache.updateRobotAlertStatus(robotId, message);
        return null;
    }
}
