package me.zhengjie.modules.mskj.websocket.handler.impl;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.common.util.DateTimeUtils;
import me.zhengjie.modules.mskj.common.util.UUIDUtils;
import me.zhengjie.modules.mskj.domain.Environment;
import me.zhengjie.modules.mskj.service.EnvironmentService;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.dto.IndexMessage;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Slf4j
public class IndexMessageHandler implements MessageHandler {
    final EnvironmentService environmentService;
    final Cache cache;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        IndexMessage indexMessage = new IndexMessage();
        try {
            indexMessage = objectMapper.readValue(message, IndexMessage.class);
            indexMessage.setUpdateTime(LocalDateTime.now().toString());
            log.debug(MessageFormat.format("parsing INDEX message: {0}", indexMessage.toString()));

        } catch (IOException e) {
            log.error(MessageFormat.format("failed parsing INDEX message: {0}", message), e);
        }

        //上报的消息json转对象
        JSONObject obj = JSONObject.fromObject(message);

        String date = DateTimeUtils.formatData("yyyy年MM月dd日");
        int week = DateTimeUtils.dayForWeek(DateTimeUtils.getTodayDate());
        String week2 = "";
        switch (week) {
            //心跳
            case 1:
                week2 = "一";
                break;
            case 2:
                week2 = "二";
                break;
            //上报的消息
            case 3:
                week2 = "三";
                break;
            case 4:
                week2 = "四";
                break;
            case 5:
                week2 = "五";
                break;
            case 6:
                week2 = "六";
                break;
            case 7:
                week2 = "日";
                break;
            default:
        }
        String time = date + "   " + "星期" + week2;
        obj.put("time", time);

        String temperature = obj.getString("Temperature");
        String humidity = obj.getString("Humidity");
        String PM = obj.getString("PM2.5");
        String smoke = obj.getString("Smoke");
        String windSpeed = obj.getString("WindSpeed");
        String taskId = obj.getString("task_id");
        //保存环境信息
        Environment environment = new Environment();
        environment.setEnvId(UUIDUtils.generateUUID());
        environment.setTemperature(temperature);
        environment.setHumidity(humidity);
        environment.setPm(PM);
        environment.setSmoke(smoke);
        environment.setWind(windSpeed);
        environment.setDate(DateTimeUtils.getTodayDateTime());
        environment.setRobotTaskId(taskId);
        environmentService.create(environment);

        cache.updateRobotDeviceStatus(robotId, obj.toString());
        return null;
    }
}
