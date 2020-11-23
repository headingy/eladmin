package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.domain.Device;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.service.TaskService;
import me.zhengjie.modules.mskj.service.dto.DeviceDto;
import me.zhengjie.modules.mskj.service.dto.DeviceQueryCriteria;
import me.zhengjie.modules.mskj.service.dto.TaskDto;
import me.zhengjie.modules.mskj.service.dto.TaskQueryCriteria;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChargeRequestMessageHandler implements MessageHandler {
    final TaskService taskService;
    final DeviceService deviceService;

    @Override
    public Object handle(String robotId, String message) {
        JSONObject obj = new JSONObject();
        TaskQueryCriteria criteria = new TaskQueryCriteria();
        criteria.setStatus(Collections.singletonList(4));
        List<TaskDto> tasks = taskService.queryAll(criteria);
        if (tasks != null && tasks.size() > 0) {
            log.info("no task executing with robot {}", robotId);
            return obj.toString();
        }

        DeviceQueryCriteria deviceQueryCriteria = new DeviceQueryCriteria();
        deviceQueryCriteria.setChargingPileStatus(0);
        List<DeviceDto> deviceDtos = deviceService.queryAll(deviceQueryCriteria);
        if (deviceDtos == null || deviceDtos.size() <= 0) {
            log.warn("got no device for the CHARGE request: {}", message);
            return null;
        }

        //修改状态
        Device device = new Device();
        device.setDeviceId(deviceDtos.get(0).getDeviceId());
        device.setChargingPileStatus(1);
        deviceService.update(device);

        obj.put("charge_pile_id", deviceDtos.get(0).getDeviceId());
        obj.put("type", "CHARGE_RESPONSE");
        return obj.toString();
    }
}
