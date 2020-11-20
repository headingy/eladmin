package me.zhengjie.modules.mskj.websocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class RobotStatusMessage {
    String type; // 必须是STATUS
    // 所有字段均为字符串， 1 代表正常， 0 代表不正常
    String laser; // 雷达状态， 1 代表正常， 0 代表不正常
    String imu; // IMU 状态
    String odom; // 里程计状态
    String camera; // 摄像头状态
    String gps; // GPS 状态
    String sonar; // 超声波状态
    @JsonProperty("PTZ")
    String PTZ; // 云台状态
    String lifting; // 升降杆状态
    String license; // 许可证状态
    String location; // 定位模块状态
    String navigation; // 导航模块状态
    String patrol; // 巡检调度模块状态
    String robot_id;
}
