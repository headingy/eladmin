package me.zhengjie.modules.mskj.websocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class IndexMessage {
    String type;//"INDEX",
    String ultrasonicSensor;//"0,1,1,0,1", #超声波传感器
    String smokeSensor;//"0,1,0", #烟雾传感器
    String pmSensor;//"1", #PM2.5 传感器
    String pyroelectricSensor;//"1", #热释电传感器
    String temperatureSensor;//"0", #温度传感器
    String humiditySensor;//"1", #湿度传感器
    String laserRadar;//"1", #激光雷达
    String battery;//"21A 26V", #电池
    String speed;//"1.2", #车速
    String windSpeed;//"5", #风速
    String robotTemperature;//"25", #机内温度
    String temperature;//"23", #温度
    String humidity;//"45", #湿度
    String electricQuantity;//"20", #电量
    String single;//"4", #信号
    @JsonProperty("PM2.5")
    String pm25;//"15", #PM2.5
    String telecontrolTelemetrySignal;//"1", #遥控遥测信号
    String cloudPlatform;//"1", #云台自检
    String smoke;//"", #烟感
    String driver;//"1", #驱动器
    String robot_id;//"0dd5c80b22f1445cbe60170628fcef93", #机器人 ID
    String task_id;//"1216c97bd00941f5964db6bbb57a2319" #任务 ID
    String updateTime;
}
