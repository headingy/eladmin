package me.zhengjie.modules.mskj.websocket.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.common.util.DateTimeUtils;
import me.zhengjie.modules.mskj.domain.Map;
import me.zhengjie.modules.mskj.domain.Robot;
import me.zhengjie.modules.mskj.service.RobotService;
import me.zhengjie.modules.mskj.service.dto.RobotDto;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.dto.HeartRequest;
import me.zhengjie.modules.mskj.websocket.dto.HeartResponse;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class HeartMessageHandler implements MessageHandler {
    private final Cache cache;
    private final RobotService robotService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HeartRequest req = objectMapper.readValue(message, HeartRequest.class);
        assert robotId.equals(req.getRobotId());
        cache.updateRobotOnlineStatus(robotId, req.getLocation());

        RobotDto robot = robotService.findById(robotId);
        if (!robot.getMap().getMapId().equals(req.getMapId())) {
            Robot resource = new Robot();
            Map map = new Map();
            resource.setRobotId(robotId);
            map.setMapId(req.getMapId());
            resource.setMap(map);
            robotService.update(resource);
        }

        HeartResponse response = HeartResponse.builder().result("1").serverTime(DateTimeUtils.getTodayDateTime()).build();
        return response;
    }
}
