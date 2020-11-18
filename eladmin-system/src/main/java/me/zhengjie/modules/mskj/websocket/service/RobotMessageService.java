package me.zhengjie.modules.mskj.websocket.service;

import java.io.IOException;

public interface RobotMessageService {
    default void onOpen(String robotId) {};
    default void onClose(String robotId) {};
    default void onError(String robotId) {};
    String onMessage(String robotId, String message) throws IOException;
}
