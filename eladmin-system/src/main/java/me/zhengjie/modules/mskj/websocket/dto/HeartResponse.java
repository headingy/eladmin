package me.zhengjie.modules.mskj.websocket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HeartResponse {
    String result;
    String serverTime;
}
