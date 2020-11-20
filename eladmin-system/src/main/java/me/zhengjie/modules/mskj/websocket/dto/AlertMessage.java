package me.zhengjie.modules.mskj.websocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AlertMessage {
    String robot_id;
    String alert_msg;
}
