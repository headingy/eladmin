package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NopHandler implements MessageHandler {
    @Override
    public Object handle(String robotId, String nop) {
        log.warn("Nop for message received: {}", nop);
        return null;
    }
}
