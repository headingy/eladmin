package me.zhengjie.modules.mskj.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(Include.NON_NULL)
public class DevicePic implements Serializable {
    //设备id
    private String deviceId;
    //设备名称
    private String deviceName;
    //可见光图片
    private String lightPic;
    //热红外图片
    private String infraredPic;
    //仪表值
    private String value;
    //结果状态
    private String result;
    //最高温度
    private String maxTemp;
    //温升
    private String tempUp;
    //地图ID5
    private String mapMd5;
}
