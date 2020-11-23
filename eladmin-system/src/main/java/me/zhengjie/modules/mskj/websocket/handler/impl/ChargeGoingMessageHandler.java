package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.domain.Device;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChargeGoingMessageHandler implements MessageHandler {
    final DeviceService deviceService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject obj = JSONObject.fromObject(message);
        String deviceId = obj.getString("device_id");
        Device device = new Device();
        device.setDeviceId(deviceId);
        device.setChargingPileStatus(2);
        deviceService.update(device);
        return null;
    }
}
