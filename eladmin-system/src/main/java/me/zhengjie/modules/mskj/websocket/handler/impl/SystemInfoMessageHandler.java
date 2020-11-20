package me.zhengjie.modules.mskj.websocket.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhengjie.modules.mskj.websocket.dto.SystemInfoMessage;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SystemInfoMessageHandler implements MessageHandler {
    @Override
    public Object handle(String robotId, String msg) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SystemInfoMessage message = objectMapper.readValue(msg, SystemInfoMessage.class);
        //TODO write to warninfo db
        return null;
    }
}
