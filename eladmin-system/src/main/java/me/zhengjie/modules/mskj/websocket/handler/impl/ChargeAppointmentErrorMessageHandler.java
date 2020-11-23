package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChargeAppointmentErrorMessageHandler implements MessageHandler {
    final Cache cache;
    final DeviceService deviceService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject obj = JSONObject.fromObject(message);
        String map_id = obj.getString("map_id");
        String deviceId = obj.getString("charge_pile_id");
        String robot_id = obj.getString("robot_id");
        //根据robot_id获取当前存的不可达列表
        String unreachable = cache.getRobotUnreachable(robotId);
        if (unreachable == null || unreachable.equals("")) {//不存在
            //List<String> devices = new ArrayList<>();
            //devices.add(deviceId);
            //String de = devices.toString();
            cache.updateRobotUnreachable(robot_id, deviceId);
            //考虑到一般不会有不可达的充电桩，所以没有封装成数组
        } else {
            cache.updateRobotUnreachable(robot_id, deviceId);
        }
        return null;
    }
}
