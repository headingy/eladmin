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
@Table(name="sys_robot_task")
public class RobotTask implements Serializable {

    @Id
    @Column(name = "robot_task_id")
    @ApiModelProperty(value = "主键")
    private String robotTaskId;

    @Column(name = "robot_id")
    @ApiModelProperty(value = "机器人id")
    private String robotId;

    @Column(name = "task_id")
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @Column(name = "old_exec_time")
    @ApiModelProperty(value = "原执行时间")
    private String oldExecTime;

    @Column(name = "exec_time")
    @ApiModelProperty(value = "执行时间")
    private String execTime;

    @Column(name = "exec_status")
    @ApiModelProperty(value = "执行状态（默认0表示待执行，1表示正在执行，2表示已完成，3表示已终止，4表示不执行，5暂停）")
    private Integer execStatus;

    @Column(name = "exec_if")
    @ApiModelProperty(value = "是否执行（默认0是，1否）")
    private Integer execIf;

    @Column(name = "wait_device")
    @ApiModelProperty(value = "暂停的设备id")
    private String waitDevice;

    @Column(name = "end_time")
    @ApiModelProperty(value = "终止时间")
    private String endTime;

    @Column(name = "bz")
    @ApiModelProperty(value = "备注")
    private String bz;

    public void copy(RobotTask source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}