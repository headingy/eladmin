package me.zhengjie.modules.mskj.websocket.endpoint;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.websocket.service.RobotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ServerEndpoint(value = "/robot/cmd/{robotId}")
@Slf4j
@Component
public class RobotCmdEndpoint {
    private Map<String, Session> robotIdMap = new LinkedHashMap<>();
    private Map<String, String> sessionIdMap = new LinkedHashMap<>();
    static RobotMessageService robotMessageService;

    @Autowired
    public void setRobotMessageService(RobotMessageService service) {
        robotMessageService = service;
    }

    /**
     * 建立连接时
     *
     * @param session
     * @param robotId 机器人id
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("robotId") String robotId) throws IOException {
        session.setMaxTextMessageBufferSize(112000);
        session.setMaxIdleTimeout(TimeUnit.MILLISECONDS.convert(60, TimeUnit.SECONDS));

        if (robotIdMap.get(robotId) != null) {
            robotIdMap.replace(robotId, session);
        } else {
            robotIdMap.put(robotId, session);
        }
        if (sessionIdMap.get(session.getId()) != null) {
            sessionIdMap.replace(session.getId(), robotId);
        } else {
            sessionIdMap.put(session.getId(), robotId);
        }
        log.info("robot {} connected", robotId);

        robotMessageService.onOpen(robotId);
    }

    /**
     * 处理收到的消息
     *
     * @param session
     * @param message webSocket接收到的String类型的信息
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        String robotId = sessionIdMap.get(session.getId());
        log.info("message received from robot {}: {}", robotId, message);

        String ret = robotMessageService.onMessage(robotId, message);
        sendMessage(session, robotId, ret);
    }

    /**
     * 链路关闭时消息
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        String robotId = sessionIdMap.get(session.getId());
        robotIdMap.remove(robotId);
        sessionIdMap.remove(session.getId());
        log.info("connection with robot {} is closed for {}", robotId, reason.getReasonPhrase());

        robotMessageService.onClose(robotId);
    }


    /**
     * 出错时消息
     *
     * @param session
     */
    @OnError
    public void onError(Session session, Throwable error) {
        String robotId = sessionIdMap.get(session.getId());
        log.error(MessageFormat.format("ws error on robot {0}", robotId), error);

        robotMessageService.onError(robotId);
    }

    /**
     * 向机器人发送信息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session, String robotId, String message) throws IOException {
        if (message == null) return;
        session.getBasicRemote().sendText(message);
        log.info("message sent to robot {}: {}", robotId, message);
    }

}