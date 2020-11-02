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
public class RobotTaskDto implements Serializable {

    /** 主键 */
    private String robotTaskId;

    /** 机器人 */
    private RobotDto robot;

    /** 任务 */
    private TaskDto task;

    /** 原执行时间 */
    private String oldExecTime;

    /** 执行时间 */
    private String execTime;

    /** 执行状态（默认0表示待执行，1表示正在执行，2表示已完成，3表示已终止，4表示不执行，5暂停） */
    private Integer execStatus;

    /** 是否执行（默认0是，1否） */
    private Integer execIf;

    /** 暂停的设备id */
    private String waitDevice;

    /** 终止时间 */
    private String endTime;

    /** 备注 */
    private String bz;
}