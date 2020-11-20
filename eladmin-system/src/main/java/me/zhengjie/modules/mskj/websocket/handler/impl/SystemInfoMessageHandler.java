package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.common.util.DateTimeUtils;
import me.zhengjie.modules.mskj.common.util.UUIDUtils;
import me.zhengjie.modules.mskj.domain.WarnInfo;
import me.zhengjie.modules.mskj.service.WarnInfoService;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class SystemInfoMessageHandler implements MessageHandler {
    final WarnInfoService warnInfoService;

    @Override
    public Object handle(String robotId, String message) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SystemInfoMessage message = objectMapper.readValue(msg, SystemInfoMessage.class);
        WarnInfo reportInfo = new WarnInfo();
        JSONObject obj = JSONObject.fromObject(message);
        Integer infoType = Integer.parseInt(obj.getString("info_Type"));
        String taskId = obj.getString("task_id");
        reportInfo.setInfoId(UUIDUtils.generateUUID());
        reportInfo.setCreateTime(DateTimeUtils.getTodayDateTime());
        reportInfo.setInfoType(infoType);
        reportInfo.setRobotTaskId(taskId);
        warnInfoService.create(reportInfo);
        return null;
    }
}
