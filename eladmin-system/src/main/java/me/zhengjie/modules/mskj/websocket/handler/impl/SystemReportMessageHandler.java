package me.zhengjie.modules.mskj.websocket.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhengjie.modules.mskj.websocket.dto.SystemReportMessage;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SystemReportMessageHandler implements MessageHandler {
    @Override
    public Object handle(String robotId, String msg) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SystemReportMessage message = objectMapper.readValue(msg, SystemReportMessage.class);
        return null;
    }
}
