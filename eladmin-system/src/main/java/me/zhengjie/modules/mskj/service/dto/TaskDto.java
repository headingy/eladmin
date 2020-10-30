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
* @website https://el-admin.vip
* @description /
* @author Fu Ding
* @date 2020-10-30
**/
@Data
public class TaskDto implements Serializable {

    /** 主键 */
    private String taskId;

    /** 任务类型（默认0立即执行，1定时执行，2周期执行） */
    private Integer taskExecType;

    /** 巡检类型（默认0常规巡检，1例行巡检，2特巡巡检，3专项巡检） */
    private Integer taskType;

    /** 定时策略（task_exec_type为1时不能为null） */
    private String cron;

    /** 执行周期（task_exec_type为2时不能为空） */
    private String period;

    /** 任务名称 */
    private String name;

    /** 任务状态（0待执行，1暂停，2已终止，3已完成，4正在执行） */
    private Integer status;

    /** 任务创建时间 */
    private String createTime;

    /** 任务开始执行时间 */
    private String execTime;

    /** 巡检路径规划（0最优路径，1先后顺序） */
    private Integer road;

    /** 任务终止时间 */
    private String overTime;

    /** 任务创建人 */
    private String creater;

    /** 任务内容 */
    private String content;

    /** 巡检点（巡检点主键集合） */
    private String devices;

    /** 巡检结束状态（0返回充电，1原地待命，2返回原点） */
    private Integer endStatus;

    /** 任务执行结果 */
    private String result;

    /** 描述 */
    private String description;
}