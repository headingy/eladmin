package me.zhengjie.modules.mskj.websocket.handler;

import java.io.IOException;

public interface MessageHandler {
    Object handle(String robotId, String message) throws IOException;
}
