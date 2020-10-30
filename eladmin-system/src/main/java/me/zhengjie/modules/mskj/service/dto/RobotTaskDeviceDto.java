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
public class RobotTaskDeviceDto implements Serializable {

    /** 主键 */
    private String robotTaskDeviceId;

    /** 机器人id */
    private String robotId;

    /** 机器人任务id */
    private String robotTaskId;

    /** 记录时间 */
    private String createTime;

    /** 设备id */
    private String deviceId;

    /** 巡检表值 */
    private String value;

    /** 采集的图片信息（视频图像记录的id集合，以逗号隔开） */
    private String mediaIds;

    /** 环境湿度 */
    private String humidity;

    /** 环境温度 */
    private String temperature;

    /** 设备最低温 */
    private String minTemp;

    /** 设备最高温 */
    private String maxTemp;

    /** 处理意见 */
    private String handleView;

    /** 三相设备的类型（0表示A相，1表示B相，2表示C相） */
    private Integer phaseType;

    /** 巡检结果（0表示正常，1表示异常） */
    private String result;

    /** 备注 */
    private String bz;
}