package me.zhengjie.modules.mskj.websocket.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhengjie.modules.mskj.websocket.dto.HeartRequest;
import me.zhengjie.modules.mskj.websocket.dto.HeartResponse;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeartMessageHandler implements MessageHandler {
    @Override
    public Object handle(String robotId, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HeartRequest request = objectMapper.readValue(message, HeartRequest.class);
        HeartResponse response = HeartResponse.builder().result("1").serverTime("").build();
        //TODO 保存机器人地址
        return response;
    }
}
