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
public class ChargeFinishedMessageHandler implements MessageHandler {
    final DeviceService deviceService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject obj = JSONObject.fromObject(message);
        //TODO no similar logic in current code
        String deviceId = obj.getString("charge_pile_id");
        if (deviceService.freeChargingPile(deviceId)) {
        } else {
            log.warn("freeChargingPile failed");
        }
        return null;
    }
}
