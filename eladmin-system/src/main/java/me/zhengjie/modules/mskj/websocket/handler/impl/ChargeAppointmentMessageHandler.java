package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChargeAppointmentMessageHandler implements MessageHandler {
    final DeviceService deviceService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject obj = JSONObject.fromObject(message);
        String map_id = obj.getString("map_id");
        String deviceId = obj.getString("charge_pile_id");
        String robot_id = obj.getString("robot_id");
        JSONObject resp = new JSONObject();
        resp.put("type", "CHARGE_APPOINTMENT_RESPONSE");
        resp.put("mao_id", map_id);
        resp.put("charge_pile_id", deviceId);
        resp.put("robot_id", robot_id);
        //如果充电桩的状态为可用, 将充电桩状态设置为预约
        if (deviceService.claimChargingPile(deviceId)) {
            resp.put("state", "1");//预约成功
        } else {
            resp.put("state", "0");//预约失败
        }
        return resp.toString();
    }
}
