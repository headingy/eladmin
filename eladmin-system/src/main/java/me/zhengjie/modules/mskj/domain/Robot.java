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
* @date 2020-10-27
**/
@Entity
@Data
@Table(name="sys_robot")
public class Robot implements Serializable {

    @Id
    @Column(name = "robot_id")
    @ApiModelProperty(value = "robotId")
    private String robotId;

    @Column(name = "robot_no")
    @ApiModelProperty(value = "机器人编号")
    private String robotNo;

    @Column(name = "name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "机器人名称")
    private String name;

    @Column(name = "action_status")
    @ApiModelProperty(value = "机器人运行状态")
    private Integer actionStatus;

    @Column(name = "mode")
    @ApiModelProperty(value = "机器人当前运行模式（0任务模式，1紧急定位模式，2后台遥控模式，3手持遥控模式，4正在充电）")
    private Integer mode;

    @Column(name = "connect_status")
    @ApiModelProperty(value = "机器人连接状态（0表示未连接，1表示已连接）")
    private Integer connectStatus;

    @Column(name = "location")
    @ApiModelProperty(value = "机器人位置信息")
    private String location;

    @Column(name = "auth")
    @ApiModelProperty(value = "机器人安全证书")
    private String auth;

    @Column(name = "power")
    @ApiModelProperty(value = "剩余电量")
    private Float power;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    @Column(name = "driver_status")
    @ApiModelProperty(value = "四驱状态(1,0,1,0)")
    private String driverStatus;

    @Column(name = "speed")
    @ApiModelProperty(value = "车速")
    private Float speed;

    @Column(name = "net")
    @ApiModelProperty(value = "网络信号值")
    private Float net;

    @Column(name = "devices_status")
    @ApiModelProperty(value = "传感器设备状态")
    private String devicesStatus;

    @Column(name = "channel_light")
    @ApiModelProperty(value = "可见光通道")
    private Integer channelLight;

    @Column(name = "channel_infrared")
    @ApiModelProperty(value = "热红外通道")
    private Integer channelInfrared;

    @Column(name = "control")
    @ApiModelProperty(value = "当前是否受控（默认0是，1否）")
    private Integer control;

    @Column(name = "robot_ip")
    @ApiModelProperty(value = "机体ip")
    private String robotIp;

    @Column(name = "light_ip")
    @ApiModelProperty(value = "可见光视频ip")
    private String lightIp;

    @Column(name = "infrared_ip")
    @ApiModelProperty(value = "热红外视频ip")
    private String infraredIp;

    @Column(name = "light_port")
    @ApiModelProperty(value = "可见光端口")
    private String lightPort;

    @Column(name = "infrared_port")
    @ApiModelProperty(value = "热红外端口")
    private String infraredPort;

    @Column(name = "light_name")
    @ApiModelProperty(value = "可见光用户名")
    private String lightName;

    @Column(name = "infrared_name")
    @ApiModelProperty(value = "热红外用户名")
    private String infraredName;

    @Column(name = "light_password")
    @ApiModelProperty(value = "可见光密码")
    private String lightPassword;

    @Column(name = "infrared_password")
    @ApiModelProperty(value = "热红外密码")
    private String infraredPassword;

    @Column(name = "face_recognition")
    @ApiModelProperty(value = "0代表巡检，1代表人脸识别")
    private String faceRecognition;

    @OneToOne
    @JoinColumn(name = "map_id")
    @ApiModelProperty(value = "地图")
    private Map map;

    public void copy(Robot source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}