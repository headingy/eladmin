package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.common.util.DateTimeUtils;
import me.zhengjie.modules.mskj.common.util.UUIDUtils;
import me.zhengjie.modules.mskj.domain.WarnInfo;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.service.WarnInfoService;
import me.zhengjie.modules.mskj.service.WarnSettingService;
import me.zhengjie.modules.mskj.service.dto.DeviceDto;
import me.zhengjie.modules.mskj.service.dto.WarnSettingDto;
import me.zhengjie.modules.mskj.service.dto.WarnSettingQueryCriteria;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EnvironmentalWarningMessageHandler implements MessageHandler {
    final WarnSettingService warnSettingService;
    final DeviceService deviceService;
    final WarnInfoService warnInfoService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SystemInfoMessage message = objectMapper.readValue(msg, SystemInfoMessage.class);
        //TODO write to warninfo db
        //要存到数据库
        WarnInfo reportInfo = new WarnInfo();
        JSONObject obj = JSONObject.fromObject(message);
        Integer infoType = Integer.parseInt(obj.getString("info_Type"));
        String taskId = obj.getString("task_id");
        String deviceId = obj.getString("device_id");//设备id
        DeviceDto device = deviceService.findById(deviceId);
        Integer deviceDirect = device.getDeviceDirect();//设备所属区域   到时候有地图就对应一下
        String deviceName = device.getDeviceName();//设备名称
        String content = deviceDirect + "区域" + deviceName + "附近";//消息内容

        String environment = obj.getString("environment");//环境告警项
        String environmentValue = obj.getString("thresholdValue");//环境告警项对应的值

        //根据告警项的warnKey查询告警设置表的信息
        WarnSettingDto warnSettingEntity = new WarnSettingDto();
        warnSettingEntity.setWarnKey(environment);
        WarnSettingQueryCriteria queryCriteria = new WarnSettingQueryCriteria();
        queryCriteria.setWarnKey(environment);
        List<WarnSettingDto> warnSettings = warnSettingService.queryAll(queryCriteria);
        if (warnSettings != null && warnSettings.size() > 0) {
            warnSettingEntity = warnSettings.get(0);//告警设置表的信息
        }
        content += warnSettingEntity.getWarnValue();

        Float thresholdUp = warnSettingEntity.getThresholdUp();//阈值上限
        Float thresholdDown = warnSettingEntity.getThresholdDown();//阈值下限
        if (Float.parseFloat(environmentValue) > thresholdUp) {
            //如果值比阈上限还高
            content += "过高";
            reportInfo.setResultStatus(0);
        } else if (Float.parseFloat(environmentValue) < thresholdDown) {
            //如果值比阈下限还高
            content += "过低";
            reportInfo.setResultStatus(0);
        } else {
            //正好在阈值内
            content += "正常";
            reportInfo.setResultStatus(1);
        }
        reportInfo.setInfoValue(content);
        reportInfo.setStatus(0);
        reportInfo.setThresholdValue(environmentValue + warnSettingEntity.getUnit());
        reportInfo.setLevel(warnSettingEntity.getLevel());
        reportInfo.setDeviceId(deviceId);
        reportInfo.setInfoId(UUIDUtils.generateUUID());
        reportInfo.setCreateTime(DateTimeUtils.getTodayDateTime());
        reportInfo.setInfoType(infoType);
        reportInfo.setRobotTaskId(taskId);
        warnInfoService.create(reportInfo);
        return null;
    }
}
