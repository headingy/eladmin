package me.zhengjie.modules.mskj.websocket.handler.impl;

import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class NopHandler implements MessageHandler {
    @Override
    public Object handle(String robotId, String nop) {
        return null;
    }
}
