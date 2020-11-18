package me.zhengjie.modules.mskj.websocket.handler;

import me.zhengjie.modules.mskj.websocket.common.Const;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static me.zhengjie.modules.mskj.websocket.common.Const.MessageType.*;

@Component
public class HandlerDispatcher {
    @Qualifier("heartMessageHandler")
    MessageHandler heartMessageHandler;
    @Qualifier("authMessageHandler")
    MessageHandler authMessageHandler;

    public MessageHandler getMessageHandler(String type) {
        switch (type) {
            case CMD_HEART:
                return heartMessageHandler;
            case Const.MessageType.CMD_AUTH:
                return authMessageHandler;
            //上报的消息
            case Const.MessageType.CMD_REPORT:
            case Const.MessageType.MEDIA:
            case Const.MessageType.INDEX:
            //机器人状态
            case Const.MessageType.STATUS:
            //首页警告信息
            case Const.MessageType.ALERT:
            case Const.MessageType.TASK:
            case Const.MessageType.CHARGE:
            case Const.MessageType.FILE:
            //人脸识别图片上传
            case Const.MessageType.FACE_RECOGNITION:
            //上传机器人位置信息
            case Const.MessageType.REFERENCEINFO:
            //机器人请求充电
            case Const.MessageType.CHARGE_REQUEST:
            //机器人开始充电
            case Const.MessageType.CHARGE_GOING:
            case Const.MessageType.CHARGE_APPOINTMENT:
            //机器人到充电桩不可达
            case Const.MessageType.CHARGE_APPOINTMENT_ERROR:
            case Const.MessageType.CHARGE_FINISHED:
            case Const.MessageType.CHARGE_EXCEPTION:
            case Const.MessageType.patrolResult:
            //控制指令的返回
            default:
                break;
        }
        return null;
    }
}
