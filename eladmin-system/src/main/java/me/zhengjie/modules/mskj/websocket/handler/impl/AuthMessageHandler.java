package me.zhengjie.modules.mskj.websocket.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhengjie.modules.mskj.common.util.UUIDUtils;
import me.zhengjie.modules.mskj.websocket.dto.AuthRequest;
import me.zhengjie.modules.mskj.websocket.dto.AuthResponse;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthMessageHandler implements MessageHandler {
    @Override
    public Object handle(String robotId, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthRequest req = objectMapper.readValue(message, AuthRequest.class);
        assert robotId.equals(req.getRobotId());
        AuthResponse response = AuthResponse.builder().robotId(robotId).auth(UUIDUtils.generateUUID()).build();
        return response;
    }
}
