/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.mskj.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @description /
 * @date 2020-11-02
 **/
@Data
public class DeviceDto implements Serializable {

    private String deviceId;

    /**
     * 巡检设备名称
     */
    private String deviceName;

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 巡检设备坐标（x,y）
     */
    private String deviceLocation;

    /**
     * 巡检设备类型（常量）
     */
    private String deviceType;

    /**
     * 设备所属区域
     */
    private Integer deviceDirect;

    /**
     * 设备朝向（0北 ，1东，2南，3西）
     */
    private Integer deviceOriention;

    /**
     * 地图id
     */
    private String mapId;

    /**
     * 类型
     */
    private String type;

    /**
     * 缺陷类型(0电流致热型，1电压致热型，2表示无缺陷)
     */
    private Integer defectType;

    /**
     * 识别类型(0可见光，1可见光+热红外，2声音检测，3位置状态识别，4表计识别，5导航识别,6是否工作，7是否漏油）
     */
    private Integer inspectType;

    /**
     * 表针类型(0指针，1:LED数字，2一态档位，3两态档位，4三态档位，5指示针)
     */
    private String pointerType;

    /**
     * 表计类型(0油位表，1温度表，2一态档位表，3，SF6压力表，4泄漏电流表，5表示无，不是表计类型 ,6两态档位表，7三态档位表，8 :LED彩色数字仪表，9:LED灰色数字仪表，10电气指示灯仪表 11多示数表）
     */
    private Integer needleType;

    /**
     * 宽度
     */
    private Float width;

    /**
     * 高度
     */
    private Float height;

    /**
     * 半径
     */
    private Float radius;

    /**
     * 颜色
     */
    private String color;

    /**
     * 角度偏差（x,y,z）
     */
    private String angle;

    /**
     * 设备运行状态（0.正常 1.损坏）
     */
    private Integer deviceStatus;

    /**
     * 告警阈值
     */
    private Float threshold;

    /**
     * 仪表类型(设备类型为DV1028）仪表类型表的id
     */
    private String meterType;

    /**
     * 圆形表计直径
     */
    private Float diameter;

    /**
     * 形状（高宽比）设备类型为DV1028
     */
    private Float shape;

    /**
     * 表计安装角度
     */
    private Integer installAngle;

    /**
     * 最小刻度（设备类型为DV1028）
     */
    private Float minScale;

    /**
     * 最小刻度对应角度（设备类型为DV1028）
     */
    private Float minScaleAngle;

    /**
     * 中间刻度（设备类型为DV1028）
     */
    private Float midScale;

    /**
     * 中间刻度对应的角度（设备类型为DV1028）
     */
    private Float midScaleAngle;

    /**
     * 最大刻度（设备类型为DV1028）
     */
    private Float maxScale;

    /**
     * 最大刻度对应角度（设备类型为DV1028）
     */
    private Float maxScaleAngle;

    /**
     * 表盘在地图中的位置（x,y,z)设备类型为DV1028
     */
    private String meterLocation;

    /**
     * 表盘在地图中的角度（x,y,z,w）设备类型为DV1028
     */
    private String meterAngle;

    /**
     * 表盘是否均匀（0表示均匀，1表示不均匀）
     */
    private Integer even;

    /**
     * 表盘不均匀时角度集合（以逗号分隔）
     */
    private String evenAngle;

    /**
     * 表盘不均匀时刻度集合，以逗号分隔
     */
    private String scale;

    /**
     * 是否检修（0是，默认1否）
     */
    private Integer fix;

    /**
     * 正面照的media_id
     */
    private String photoMedia;

    /**
     * 温升
     */
    private Float temperatureRise;

    /**
     * 告警阈值上限
     */
    private String thresholdUp;

    /**
     * 告警阈值下限
     */
    private String thresholdDown;

    /**
     * 是否三相设备（0是，默认1否）
     */
    private Integer threePhase;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 描述
     */
    private String description;

    private String centerLocation;

    /**
     * 充电桩状态(0表示可用，1表示预约，2表示占用，3表示维修）
     */
    private Integer chargingPileStatus;

    /**
     * 经纬度
     */
    private String longitudeLatitude;

    /**
     * 巡检位置
     */
    private String inspectLocation;

    /**
     * 充电桩状态（0表示空闲，1表示已预约，2表示充电中，3表示异常）
     */
    private Integer chargingStatus;

    private String multichartAnnotation;

    private String listsNum;

    private String scaleLists;

    private String angleLists;

    private String deviceSum;
}