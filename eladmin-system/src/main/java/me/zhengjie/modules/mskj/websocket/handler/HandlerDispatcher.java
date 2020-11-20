package me.zhengjie.modules.mskj.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.websocket.common.Const;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HandlerDispatcher {
    final MessageHandler nopHandler;
    final MessageHandler heartMessageHandler;
    final MessageHandler authMessageHandler;
    final MessageHandler systemInfoMessageHandler;
    final MessageHandler inspectionInfoMessageHandler;

    public MessageHandler getTheMessageHandler(String message) {
        JSONObject object = JSONObject.parseObject(message);
        switch ((String)object.get("type")) {
            case Const.MessageType.CMD_HEART:
                return heartMessageHandler;
            case Const.MessageType.CMD_AUTH:
                return authMessageHandler;
            //上报的消息
            case Const.MessageType.CMD_REPORT:
                switch ((String)object.get("info_Type")){
                    case "1":
                        return systemInfoMessageHandler;
                    case "2":
                        return inspectionInfoMessageHandler;
                    case "3":
                    case "0":
                    default:
                        return nopHandler;
                }
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
