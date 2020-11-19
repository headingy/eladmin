package me.zhengjie.modules.mskj.websocket.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhengjie.modules.mskj.websocket.common.Const;
import me.zhengjie.modules.mskj.websocket.handler.HandlerDispatcher;
import me.zhengjie.modules.mskj.websocket.service.RobotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RobotMessageServiceImpl implements RobotMessageService {

    HandlerDispatcher handlerDispatcher;

    @Autowired
    public void setHandlerDispatcher(HandlerDispatcher dispatcher) {
        handlerDispatcher = dispatcher;
    }

    @Override
    public void onOpen(String robotId) {
        //TODO 更改数据库中connect_status

    }

    @Override
    public void onClose(String robotId) {

    }

    @Override
    public String onMessage(String robotId, String message) throws IOException {
        /**
         *  判断接收到的是什么类型的数据---根据type
         *  1.注册认证 		 调用处理注册认证的方法
         *  2.上报信息 		 调用处理上报消息的方法
         *  3.心跳		     调用处理心跳的方法
         *  4.控制指令的返回   调用处理控制指令返回的方法
         *  5.视频流地址
         *  6.index  首页显示
         *  7.图像
         *  8.充电完成后返回的消息
         */

//        String robotId = jsonObject.getString("robot_id");
        //根据机器人上报不同的消息类型来进行不同的操作
        ObjectMapper objectMapper = new ObjectMapper();
        Object rspObj = handlerDispatcher.getTheMessageHandler(message).handle(robotId, message);
        String rspMessage = objectMapper.writeValueAsString(rspObj);

        switch (rspMessage) {
            //心跳
            case Const.MessageType.CMD_HEART:
                break;
            //请求认证证书
            case Const.MessageType.CMD_AUTH:
                rspMessage = robotAuth(message);
                break;
            //上报的消息
            case Const.MessageType.CMD_REPORT:
                dealRobotMessage(message);
                break;
            case Const.MessageType.MEDIA:
                liveResult(message);
                break;
            case Const.MessageType.INDEX:
                indexMessage(message);
                break;
            //机器人状态
            case Const.MessageType.STATUS:
                statusMessage(message);
                break;
            //首页警告信息
            case Const.MessageType.ALERT:
                alertMessage(message);
                break;
            case Const.MessageType.TASK:
                taskMessage(message);
                break;
            case Const.MessageType.CHARGE:
                chargeComplete(message);
                break;
            case Const.MessageType.FILE:
                saveFile(message);
                break;
            //人脸识别图片上传
            case Const.MessageType.FACE_RECOGNITION:
                faceRecognition(message);
                break;
            //上传机器人位置信息
            case Const.MessageType.REFERENCEINFO:
                saveLocation(message);
                break;
            //机器人请求充电
            case Const.MessageType.CHARGE_REQUEST:
                rspMessage = chargeResponse(message);
                break;
            //机器人开始充电
            case Const.MessageType.CHARGE_GOING:
                updateChargeStatus(message);
                break;
            case Const.MessageType.CHARGE_APPOINTMENT:
                rspMessage = chargeAppointment(message);
                break;
            //机器人到充电桩不可达
            case Const.MessageType.CHARGE_APPOINTMENT_ERROR:
                chargeAppointmentError(message);
                break;
            case Const.MessageType.CHARGE_FINISHED:
                updateStatus(message);
                break;
            case Const.MessageType.CHARGE_EXCEPTION:
                updateException(message);
                break;
            case Const.MessageType.patrolResult:
                requestFile(message);
                break;
            //控制指令的返回
            default:
                robotReturn(message);
                break;
        }

        return rspMessage;
    }

    private String chargeAppointment(String message) {
        return null;
    }

    private String chargeResponse(String message) {
        return null;
    }

    private void indexMessage(String message) {
    }

    private void liveResult(String message) {
    }

    private void statusMessage(String message) {
    }

    private void taskMessage(String message) {
    }

    private void alertMessage(String message) {
    }

    private void chargeComplete(String message) {
    }

    private void saveFile(String message) {
    }

    private void faceRecognition(String message) {
    }

    private void saveLocation(String message) {
    }

    private void updateChargeStatus(String message) {
    }

    private void chargeAppointmentError(String message) {
    }

    private void updateStatus(String message) {
    }

    private void updateException(String message) {
    }

    private void requestFile(String message) {
    }

    private void robotReturn(String message) {
    }

    private void dealRobotMessage(String message) {
    }

    private String robotAuth(String message) {
        return message;
    }

}
