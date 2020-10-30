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
* @date 2020-10-30
**/
@Entity
@Data
@Table(name="sys_robot_task_device")
public class RobotTaskDevice implements Serializable {

    @Id
    @Column(name = "robot_task_device_id")
    @ApiModelProperty(value = "主键")
    private String robotTaskDeviceId;

    @Column(name = "robot_id")
    @ApiModelProperty(value = "机器人id")
    private String robotId;

    @Column(name = "robot_task_id")
    @ApiModelProperty(value = "机器人任务id")
    private String robotTaskId;

    @Column(name = "create_time")
    @ApiModelProperty(value = "记录时间")
    private String createTime;

    @Column(name = "device_id")
    @ApiModelProperty(value = "设备id")
    private String deviceId;

    @Column(name = "value")
    @ApiModelProperty(value = "巡检表值")
    private String value;

    @Column(name = "media_ids")
    @ApiModelProperty(value = "采集的图片信息（视频图像记录的id集合，以逗号隔开）")
    private String mediaIds;

    @Column(name = "humidity")
    @ApiModelProperty(value = "环境湿度")
    private String humidity;

    @Column(name = "temperature")
    @ApiModelProperty(value = "环境温度")
    private String temperature;

    @Column(name = "min_temp")
    @ApiModelProperty(value = "设备最低温")
    private String minTemp;

    @Column(name = "max_temp")
    @ApiModelProperty(value = "设备最高温")
    private String maxTemp;

    @Column(name = "handle_view")
    @ApiModelProperty(value = "处理意见")
    private String handleView;

    @Column(name = "phase_type")
    @ApiModelProperty(value = "三相设备的类型（0表示A相，1表示B相，2表示C相）")
    private Integer phaseType;

    @Column(name = "result")
    @ApiModelProperty(value = "巡检结果（0表示正常，1表示异常）")
    private String result;

    @Column(name = "bz")
    @ApiModelProperty(value = "备注")
    private String bz;

    public void copy(RobotTaskDevice source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}