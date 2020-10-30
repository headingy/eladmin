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
@Table(name="sys_task")
public class Task implements Serializable {

    @Id
    @Column(name = "task_id")
    @ApiModelProperty(value = "主键")
    private String taskId;

    @Column(name = "task_exec_type")
    @ApiModelProperty(value = "任务类型（默认0立即执行，1定时执行，2周期执行）")
    private Integer taskExecType;

    @Column(name = "task_type")
    @ApiModelProperty(value = "巡检类型（默认0常规巡检，1例行巡检，2特巡巡检，3专项巡检）")
    private Integer taskType;

    @Column(name = "cron")
    @ApiModelProperty(value = "定时策略（task_exec_type为1时不能为null）")
    private String cron;

    @Column(name = "period")
    @ApiModelProperty(value = "执行周期（task_exec_type为2时不能为空）")
    private String period;

    @Column(name = "name")
    @ApiModelProperty(value = "任务名称")
    private String name;

    @Column(name = "status")
    @ApiModelProperty(value = "任务状态（0待执行，1暂停，2已终止，3已完成，4正在执行）")
    private Integer status;

    @Column(name = "create_time")
    @ApiModelProperty(value = "任务创建时间")
    private String createTime;

    @Column(name = "exec_time")
    @ApiModelProperty(value = "任务开始执行时间")
    private String execTime;

    @Column(name = "road")
    @ApiModelProperty(value = "巡检路径规划（0最优路径，1先后顺序）")
    private Integer road;

    @Column(name = "over_time")
    @ApiModelProperty(value = "任务终止时间")
    private String overTime;

    @Column(name = "creater")
    @ApiModelProperty(value = "任务创建人")
    private String creater;

    @Column(name = "content")
    @ApiModelProperty(value = "任务内容")
    private String content;

    @Column(name = "devices")
    @ApiModelProperty(value = "巡检点（巡检点主键集合）")
    private String devices;

    @Column(name = "end_status")
    @ApiModelProperty(value = "巡检结束状态（0返回充电，1原地待命，2返回原点）")
    private Integer endStatus;

    @Column(name = "result")
    @ApiModelProperty(value = "任务执行结果")
    private String result;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    public void copy(Task source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}