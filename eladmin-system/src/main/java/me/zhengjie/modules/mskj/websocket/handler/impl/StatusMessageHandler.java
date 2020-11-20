package me.zhengjie.modules.mskj.websocket.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.dto.StatusMessage;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
@Slf4j
public class StatusMessageHandler implements MessageHandler {
    final Cache cache;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StatusMessage status = new StatusMessage();
        try {
            status = objectMapper.readValue(message, StatusMessage.class);
        } catch (IOException e) {
            log.error(MessageFormat.format("failed parsing STATUS message: {0}", message), e);
        }
        log.debug(MessageFormat.format("STATUS message: {0}", status.toString()));

        cache.updateRobotHardwareStatus(robotId, message);
        return null;
    }
}
