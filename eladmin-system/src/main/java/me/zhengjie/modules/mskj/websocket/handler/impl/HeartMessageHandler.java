package me.zhengjie.modules.mskj.websocket.handler.impl;

import me.zhengjie.modules.mskj.websocket.dto.HeartRequest;
import me.zhengjie.modules.mskj.websocket.dto.HeartResponse;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class HeartMessageHandler implements MessageHandler {
    @Override
    public Object handle(Object req) {
        HeartRequest request = (HeartRequest)req;
        HeartResponse response = HeartResponse.builder().result("1").serverTime("").build();
        //TODO 保存机器人地址
        return response;
    }
}
