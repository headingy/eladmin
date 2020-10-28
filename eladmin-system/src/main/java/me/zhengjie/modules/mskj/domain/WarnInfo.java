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
* @date 2020-10-28
**/
@Entity
@Data
@Table(name="sys_warn_info")
public class WarnInfo implements Serializable {

    @Id
    @Column(name = "info_id")
    @ApiModelProperty(value = "ID")
    private String infoId;

    @Column(name = "robot_task_id")
    @ApiModelProperty(value = "机器人任务id")
    private String robotTaskId;

    @Column(name = "info_key")
    @ApiModelProperty(value = "告警项")
    private String infoKey;

    @Column(name = "info_value")
    @ApiModelProperty(value = "告警详细信息")
    private String infoValue;

    @Column(name = "threshold_value")
    @ApiModelProperty(value = "告警阈值")
    private String thresholdValue;

    @Column(name = "level")
    @ApiModelProperty(value = "告警级别(0，一般警告，1严重警告，2危机警告)")
    private Integer level;

    @Column(name = "status")
    @ApiModelProperty(value = "告警状态（0 未确认，1 已确认，2已删除）")
    private Integer status;

    @Column(name = "create_time")
    @ApiModelProperty(value = "告警时间")
    private String createTime;

    @Column(name = "ensure_time")
    @ApiModelProperty(value = "确认时间")
    private String ensureTime;

    @Column(name = "verify_type")
    @ApiModelProperty(value = "识别类型(0可见光，1可见光+热红外，2声音检测，3位置状态识别，4表计识别）")
    private Integer verifyType;

    @Column(name = "device_id")
    @ApiModelProperty(value = "告警设备")
    private String deviceId;

    @Column(name = "picture_id")
    @ApiModelProperty(value = "告警图片信息（关联media_id）")
    private String pictureId;

    @Column(name = "voice_id")
    @ApiModelProperty(value = "告警声音(关联media_id)")
    private String voiceId;

    @Column(name = "light_picture_id")
    @ApiModelProperty(value = "告警可见光图片(关联media_id)")
    private String lightPictureId;

    @Column(name = "video_id")
    @ApiModelProperty(value = "告警视频信息（关联media_id）")
    private String videoId;

    @Column(name = "confirmer")
    @ApiModelProperty(value = "确认人")
    private String confirmer;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    @Column(name = "info_type")
    @ApiModelProperty(value = "信息类型（0表示时实消息，1表示系统消息，2表示巡检点告警，3表示机体告警，4表示环境告警）")
    private Integer infoType;

    @Column(name = "result_status")
    @ApiModelProperty(value = "结果状态（0表示正常，1表示异常）")
    private Integer resultStatus;

    public void copy(WarnInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}