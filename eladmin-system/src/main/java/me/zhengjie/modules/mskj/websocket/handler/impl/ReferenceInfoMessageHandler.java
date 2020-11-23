package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.domain.Device;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class ReferenceInfoMessageHandler implements MessageHandler {
    final Cache cache;
    final DeviceService deviceService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject obj = JSONObject.fromObject(message);
        String deviceId = obj.getString("device_id");
        String devicePosition = obj.getString("device_position");

        //TODO better split robot running data from static data
        Device device = new Device();
        device.setDeviceId(deviceId);
        device.setMeterLocation(devicePosition);
        deviceService.update(device);
        cache.updateRobotAlertStatus(robotId, message);
        return null;
    }
}
