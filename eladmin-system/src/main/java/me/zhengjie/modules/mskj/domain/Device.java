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
package me.zhengjie.modules.mskj.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author Fu Ding
* @date 2020-11-02
**/
@Entity
@Data
@Table(name="sys_device")
public class Device implements Serializable {

    @Id
    @Column(name = "device_id")
    @ApiModelProperty(value = "deviceId")
    private String deviceId;

    @Column(name = "device_name")
    @ApiModelProperty(value = "巡检设备名称")
    private String deviceName;

    @Column(name = "device_no")
    @ApiModelProperty(value = "设备编号")
    private String deviceNo;

    @Column(name = "device_location")
    @ApiModelProperty(value = "巡检设备坐标（x,y）")
    private String deviceLocation;

    @Column(name = "device_type")
    @ApiModelProperty(value = "巡检设备类型（常量）")
    private String deviceType;

    @Column(name = "device_direct")
    @ApiModelProperty(value = "设备所属区域")
    private Integer deviceDirect;

    @Column(name = "device_oriention")
    @ApiModelProperty(value = "设备朝向（0北 ，1东，2南，3西）")
    private Integer deviceOriention;

    @Column(name = "map_id")
    @ApiModelProperty(value = "地图id")
    private String mapId;

    @Column(name = "type")
    @ApiModelProperty(value = "类型")
    private String type;

    @Column(name = "defect_type")
    @ApiModelProperty(value = "缺陷类型(0电流致热型，1电压致热型，2表示无缺陷)")
    private Integer defectType;

    @Column(name = "inspect_type")
    @ApiModelProperty(value = "识别类型(0可见光，1可见光+热红外，2声音检测，3位置状态识别，4表计识别，5导航识别,6是否工作，7是否漏油）")
    private Integer inspectType;

    @Column(name = "pointer_type")
    @ApiModelProperty(value = "表针类型(0指针，1:LED数字，2一态档位，3两态档位，4三态档位，5指示针)")
    private String pointerType;

    @Column(name = "needle_type")
    @ApiModelProperty(value = "表计类型(0油位表，1温度表，2一态档位表，3，SF6压力表，4泄漏电流表，5表示无，不是表计类型 ,6两态档位表，7三态档位表，8 :LED彩色数字仪表，9:LED灰色数字仪表，10电气指示灯仪表 11多示数表）")
    private Integer needleType;

    @Column(name = "width")
    @ApiModelProperty(value = "宽度")
    private Float width;

    @Column(name = "height")
    @ApiModelProperty(value = "高度")
    private Float height;

    @Column(name = "radius")
    @ApiModelProperty(value = "半径")
    private Float radius;

    @Column(name = "color")
    @ApiModelProperty(value = "颜色")
    private String color;

    @Column(name = "angle")
    @ApiModelProperty(value = "角度偏差（x,y,z）")
    private String angle;

    @Column(name = "device_status")
    @ApiModelProperty(value = "设备运行状态（0.正常 1.损坏）")
    private Integer deviceStatus;

    @Column(name = "threshold")
    @ApiModelProperty(value = "告警阈值")
    private Float threshold;

    @Column(name = "meter_type")
    @ApiModelProperty(value = "仪表类型(设备类型为DV1028）仪表类型表的id")
    private String meterType;

    @Column(name = "diameter")
    @ApiModelProperty(value = "圆形表计直径")
    private Float diameter;

    @Column(name = "shape")
    @ApiModelProperty(value = "形状（高宽比）设备类型为DV1028")
    private Float shape;

    @Column(name = "install_angle")
    @ApiModelProperty(value = "表计安装角度")
    private Integer installAngle;

    @Column(name = "min_scale")
    @ApiModelProperty(value = "最小刻度（设备类型为DV1028）")
    private Float minScale;

    @Column(name = "min_scale_angle")
    @ApiModelProperty(value = "最小刻度对应角度（设备类型为DV1028）")
    private Float minScaleAngle;

    @Column(name = "mid_scale")
    @ApiModelProperty(value = "中间刻度（设备类型为DV1028）")
    private Float midScale;

    @Column(name = "mid_scale_angle")
    @ApiModelProperty(value = "中间刻度对应的角度（设备类型为DV1028）")
    private Float midScaleAngle;

    @Column(name = "max_scale")
    @ApiModelProperty(value = "最大刻度（设备类型为DV1028）")
    private Float maxScale;

    @Column(name = "max_scale_angle")
    @ApiModelProperty(value = "最大刻度对应角度（设备类型为DV1028）")
    private Float maxScaleAngle;

    @Column(name = "meter_location")
    @ApiModelProperty(value = "表盘在地图中的位置（x,y,z)设备类型为DV1028")
    private String meterLocation;

    @Column(name = "meter_angle")
    @ApiModelProperty(value = "表盘在地图中的角度（x,y,z,w）设备类型为DV1028")
    private String meterAngle;

    @Column(name = "even")
    @ApiModelProperty(value = "表盘是否均匀（0表示均匀，1表示不均匀）")
    private Integer even;

    @Column(name = "even_angle")
    @ApiModelProperty(value = "表盘不均匀时角度集合（以逗号分隔）")
    private String evenAngle;

    @Column(name = "scale")
    @ApiModelProperty(value = "表盘不均匀时刻度集合，以逗号分隔")
    private String scale;

    @Column(name = "fix",nullable = false)
    @NotNull
    @ApiModelProperty(value = "是否检修（0是，默认1否）")
    private Integer fix;

    @Column(name = "photo_media")
    @ApiModelProperty(value = "正面照的media_id")
    private String photoMedia;

    @Column(name = "temperature_rise")
    @ApiModelProperty(value = "温升")
    private Float temperatureRise;

    @Column(name = "threshold_up")
    @ApiModelProperty(value = "告警阈值上限")
    private String thresholdUp;

    @Column(name = "threshold_down")
    @ApiModelProperty(value = "告警阈值下限")
    private String thresholdDown;

    @Column(name = "three_phase")
    @ApiModelProperty(value = "是否三相设备（0是，默认1否）")
    private Integer threePhase;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    @Column(name = "center_location")
    @ApiModelProperty(value = "centerLocation")
    private String centerLocation;

    @Column(name = "charging_pile_status")
    @ApiModelProperty(value = "充电桩状态(0表示可用，1表示预约，2表示占用，3表示维修）")
    private Integer chargingPileStatus;

    @Column(name = "longitude_latitude")
    @ApiModelProperty(value = "经纬度")
    private String longitudeLatitude;

    @Column(name = "inspect_location")
    @ApiModelProperty(value = "巡检位置")
    private String inspectLocation;

    @Column(name = "charging_status")
    @ApiModelProperty(value = "充电桩状态（0表示空闲，1表示已预约，2表示充电中，3表示异常）")
    private Integer chargingStatus;

    @Column(name = "multichart_annotation")
    @ApiModelProperty(value = "multichartAnnotation")
    private String multichartAnnotation;

    @Column(name = "lists_num")
    @ApiModelProperty(value = "listsNum")
    private String listsNum;

    @Column(name = "scale_lists")
    @ApiModelProperty(value = "scaleLists")
    private String scaleLists;

    @Column(name = "angle_lists")
    @ApiModelProperty(value = "angleLists")
    private String angleLists;

    @Column(name = "device_sum")
    @ApiModelProperty(value = "deviceSum")
    private String deviceSum;

    public void copy(Device source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}