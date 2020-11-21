package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.domain.Robot;
import me.zhengjie.modules.mskj.service.RobotService;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChargeMessageHandler implements MessageHandler {
    final Cache cache;
    final RobotService robotService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject obj = JSONObject.fromObject(message);
        String taskId = obj.getString("task_id");
        cache.updateRobotChargeTask(robotId, taskId);

        //修改机器人表中的电量
        Robot robot = new Robot();
        robot.setRobotId(robotId);
        robot.setPower(Float.valueOf(100));
        robotService.update(robot);
        return null;
    }
}
