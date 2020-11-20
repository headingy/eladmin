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
* @date 2020-11-20
**/
@Data
public class WarnSettingDto implements Serializable {

    private String warnId;

    /** 告警项 */
    private String warnKey;

    /** 告警详细信息 */
    private String warnValue;

    /** 告警阈值上限 */
    private Float thresholdUp;

    /** 告警阈值下限 */
    private Float thresholdDown;

    /** 进行短信通知（0表示未通知，1表示接受通知) */
    private Integer infoNotify;

    /** 重要情况（0表示重要，1表示紧急，2表示一般） */
    private Integer level;

    /** 声光开启（0表示开启，1表示未开启） */
    private Integer voiceLight;

    /** 单位 */
    private String unit;

    /** 接收者 */
    private String receiver;

    /** 告警设备 */
    private String deviceType;

    /** 描述 */
    private String description;
}