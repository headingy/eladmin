package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.common.util.DateTimeUtils;
import me.zhengjie.modules.mskj.common.util.UUIDUtils;
import me.zhengjie.modules.mskj.domain.RobotTask;
import me.zhengjie.modules.mskj.domain.Task;
import me.zhengjie.modules.mskj.domain.WarnInfo;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.service.RobotTaskService;
import me.zhengjie.modules.mskj.service.TaskService;
import me.zhengjie.modules.mskj.service.WarnInfoService;
import me.zhengjie.modules.mskj.service.dto.DeviceDto;
import me.zhengjie.modules.mskj.service.dto.RobotTaskDto;
import me.zhengjie.modules.mskj.service.dto.WarnInfoDto;
import me.zhengjie.modules.mskj.service.dto.WarnInfoQueryCriteria;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class TaskMessageHandler implements MessageHandler {
    final Cache cache;
    final RobotTaskService robotTaskService;
    final TaskService taskService;
    final DeviceService deviceService;
    final WarnInfoService warnInfoService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject obj = JSONObject.fromObject(message);
        String robotTaskId = obj.getString("task_id");
        String status = obj.getString("status");
        String deviceId = obj.getString("current_point");
        String alarm = obj.optString("alarm", "");
        RobotTaskDto robotTask = robotTaskService.findById(robotTaskId);
        if (robotTask == null) {
            log.warn("robot task not found by id {}", robotTaskId);
            return null;
        }

        obj.replace("task_id", robotTask.getTask().getName());
        DeviceDto device = deviceService.findById(deviceId);
        String deviceName = device.getDeviceName();

        //根据任务id在告警信息表中查询告警数
        WarnInfoQueryCriteria criteria = new WarnInfoQueryCriteria();
        criteria.setRobotTaskId(robotTaskId);
        List<WarnInfoDto> warnInfos = warnInfoService.queryAll(criteria);
        obj.put("warnDevicesNum", warnInfos.size());

        //如果运行状态为working 则正在运行中  end 任务结束
        String statusTxt;
        switch (status) {
            case "0":
                statusTxt = "执行中";
                break;
            case "1":
                statusTxt = "执行中";
                WarnInfo warnInfo = new WarnInfo();
                warnInfo.setInfoId(UUIDUtils.generateUUID());
                warnInfo.setCreateTime(DateTimeUtils.getTodayDateTime());
                warnInfo.setInfoType(2);
                warnInfo.setRobotTaskId(robotTaskId);
                if (alarm.equals("0")) {
                    warnInfo.setInfoValue(deviceName + "：巡检正常");
                    warnInfo.setStatus(0);
                    warnInfo.setResultStatus(0);
                } else if (alarm.equals("1")) {
                    warnInfo.setInfoValue(deviceName + "：机器人无法到达");
                    warnInfo.setResultStatus(1);
                    warnInfo.setStatus(1);
                }
                warnInfo.setLevel(0);
                warnInfo.setDeviceId(deviceId);
                warnInfoService.create(warnInfo);
                break;
            case "2":
                statusTxt = "执行完成";
                //如果任务完成，修改任务状态为已完成
                RobotTask robotTaskEntity = new RobotTask();
                robotTaskEntity.setRobotTaskId(robotTaskId);
                robotTaskEntity.setExecStatus(2);
                robotTaskEntity.setEndTime(DateTimeUtils.getTodayDateTime());
                robotTaskService.update(robotTaskEntity);
                //如果该任务是立即执行的任务，则把任务表中的状态改为已完成
                Integer taskExecType = robotTask.getTask().getTaskExecType();
                if (taskExecType == 0) {
                    Task task = new Task();
                    task.setTaskId(robotTask.getTask().getTaskId());
                    task.setStatus(3);
                    taskService.update(task);
                }
                break;
            default:
                log.warn("status {} not supported in TASK message", status);
                return null;
        }
        obj.replace("status", statusTxt);
        obj.replace("current_point", device.getDeviceName());
        obj.replace("robot_id", robotTask.getRobot().getName());
        //缓存起来在暂停任务时用
        cache.updateRobotCurrentPoint(robotId, deviceId);
        cache.updateRobotCurrentTask(robotId, robotTaskId);
        cache.updateRobotTaskMessage(robotId, deviceId);
        return null;
    }
}
