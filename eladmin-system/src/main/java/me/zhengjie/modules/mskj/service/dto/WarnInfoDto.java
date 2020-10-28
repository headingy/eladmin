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
* @date 2020-10-28
**/
@Data
public class WarnInfoDto implements Serializable {

    /** ID */
    private String infoId;

    /** 机器人任务id */
    private String robotTaskId;

    /** 告警项 */
    private String infoKey;

    /** 告警详细信息 */
    private String infoValue;

    /** 告警阈值 */
    private String thresholdValue;

    /** 告警级别(0，一般警告，1严重警告，2危机警告) */
    private Integer level;

    /** 告警状态（0 未确认，1 已确认，2已删除） */
    private Integer status;

    /** 告警时间 */
    private String createTime;

    /** 确认时间 */
    private String ensureTime;

    /** 识别类型(0可见光，1可见光+热红外，2声音检测，3位置状态识别，4表计识别） */
    private Integer verifyType;

    /** 告警设备 */
    private String deviceId;

    /** 告警图片信息（关联media_id） */
    private String pictureId;

    /** 告警声音(关联media_id) */
    private String voiceId;

    /** 告警可见光图片(关联media_id) */
    private String lightPictureId;

    /** 告警视频信息（关联media_id） */
    private String videoId;

    /** 确认人 */
    private String confirmer;

    /** 描述 */
    private String description;

    /** 信息类型（0表示时实消息，1表示系统消息，2表示巡检点告警，3表示机体告警，4表示环境告警） */
    private Integer infoType;

    /** 结果状态（0表示正常，1表示异常） */
    private Integer resultStatus;
}