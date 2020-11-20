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
* @date 2020-11-20
**/
@Entity
@Data
@Table(name="sys_warn_setting")
public class WarnSetting implements Serializable {

    @Id
    @Column(name = "warn_id")
    @ApiModelProperty(value = "warnId")
    private String warnId;

    @Column(name = "warn_key")
    @ApiModelProperty(value = "告警项")
    private String warnKey;

    @Column(name = "warn_value")
    @ApiModelProperty(value = "告警详细信息")
    private String warnValue;

    @Column(name = "threshold_up")
    @ApiModelProperty(value = "告警阈值上限")
    private Float thresholdUp;

    @Column(name = "threshold_down")
    @ApiModelProperty(value = "告警阈值下限")
    private Float thresholdDown;

    @Column(name = "info_notify")
    @ApiModelProperty(value = "进行短信通知（0表示未通知，1表示接受通知)")
    private Integer infoNotify;

    @Column(name = "level")
    @ApiModelProperty(value = "重要情况（0表示重要，1表示紧急，2表示一般）")
    private Integer level;

    @Column(name = "voice_light")
    @ApiModelProperty(value = "声光开启（0表示开启，1表示未开启）")
    private Integer voiceLight;

    @Column(name = "unit")
    @ApiModelProperty(value = "单位")
    private String unit;

    @Column(name = "receiver")
    @ApiModelProperty(value = "接收者")
    private String receiver;

    @Column(name = "device_type")
    @ApiModelProperty(value = "告警设备")
    private String deviceType;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    public void copy(WarnSetting source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}