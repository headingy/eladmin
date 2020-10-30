package me.zhengjie.modules.mskj.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @description /
 * @date 2020-10-30
 **/
@Data
@Accessors(chain = true)
public class TaskReportDto implements Serializable {
    //任务id
    private String taskId;
    //任务名称
    private String name;
    //制定人
    private String creator;
    //巡检设备数
    private Integer numDevices;
    //检测仪器
    private String inspectType;
    //异常数
    private Integer abnormals;
    //巡检开始时间
    private String execTime;
    //异常率
    private String abnormalRate;
    //巡检结束时间
    private String overTime;
    //可见光和红外图像
    private List<DevicePic> devicePics;
    //设备热红外数据
    private List<DevicePic> infraReds;
    //设备仪表数据
    private List<DevicePic> needles;
    //告警设备
    private List<String> warnDevices;
    //温度
    private String temperature;
    //湿度
    private String humidity;
    //烟感
    private String smoke;
    //PM2.5
    private String pm25;
    //风速
    private String wind;
    //氧气
    private String oxygen;
    //硫化氢
    private String hydrothion;
    //一氧化碳
    private String carbonicOxide;
    //可燃气体
    private String combustibleGas;
    //氨气
    private String ammoniaGas;
    //地图ID5
    private String mapMd5;
}

