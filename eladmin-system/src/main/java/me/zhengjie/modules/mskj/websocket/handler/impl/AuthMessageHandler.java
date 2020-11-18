package me.zhengjie.modules.mskj.websocket.handler.impl;

import me.zhengjie.modules.mskj.websocket.dto.AuthResponse;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;

public class AuthMessageHandler  implements MessageHandler {
    @Override
    public Object handle(Object req) {
        AuthResponse response = AuthResponse.builder().build();
        return response;
    }
}
