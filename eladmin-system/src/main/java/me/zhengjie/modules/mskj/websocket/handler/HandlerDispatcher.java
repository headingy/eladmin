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
    final MessageHandler environmentalWarningMessageHandler;
    final MessageHandler indexMessageHandler;
    final MessageHandler statusMessageHandler;
    final MessageHandler alertMessageHandler;
    final MessageHandler taskMessageHandler;

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
                        /** 系统消息
                         * 可见光抓拍XXXXX设备   /  云台转动，角度东北45度
                         */
                    case "3":
                        /**
                         * 机体告警
                         */
                        return systemInfoMessageHandler;
                    case "2":
                        /**
                         * 巡检点
                         * 设备名称+表针类型 表计读数25，状态正常
                         * XXXXA相最高温度为20°，温升10°
                         * XXXXX刀闸分合状态为合，状态正常
                         * XXXXXX结果无法识别
                         */
                        return inspectionInfoMessageHandler;
                    case "4":
                        /**
                         * 环境告警
                         * 消息格式：
                         * 消息内容：XXX区XXX设备附近XXXX数值过高
                         *
                         * 根据设备id查询在哪个区域哪个设备
                         * 根据不同数值查询阈值比较大小
                         */
                        return environmentalWarningMessageHandler;
                    case "0":
                        /**
                         * 实时消息:时间+内容
                         * 设备名称+表针类型,+识别类型,+识别结果
                         */
                    default:
                        return nopHandler;
                }
            case Const.MessageType.MEDIA:
                return nopHandler;
            case Const.MessageType.INDEX:
                return indexMessageHandler;
            //机器人状态
            case Const.MessageType.STATUS:
                return statusMessageHandler;
            //首页警告信息
            case Const.MessageType.ALERT:
                return alertMessageHandler;
            case Const.MessageType.TASK:
                return taskMessageHandler;
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
