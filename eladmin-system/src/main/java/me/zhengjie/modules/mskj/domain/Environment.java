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
@Table(name="sys_environment")
public class Environment implements Serializable {

    @Id
    @Column(name = "env_id")
    @ApiModelProperty(value = "envId")
    private String envId;

    @Column(name = "temperature")
    @ApiModelProperty(value = "温度")
    private String temperature;

    @Column(name = "humidity")
    @ApiModelProperty(value = "湿度")
    private String humidity;

    @Column(name = "smoke")
    @ApiModelProperty(value = "烟感")
    private String smoke;

    @Column(name = "pm")
    @ApiModelProperty(value = "PM2.5")
    private String pm;

    @Column(name = "oxygen")
    @ApiModelProperty(value = "氧气")
    private String oxygen;

    @Column(name = "hydrothion")
    @ApiModelProperty(value = "硫化氢")
    private String hydrothion;

    @Column(name = "wind")
    @ApiModelProperty(value = "风速")
    private String wind;

    @Column(name = "robot_task_id")
    @ApiModelProperty(value = "机器人任务id")
    private String robotTaskId;

    @Column(name = "date")
    @ApiModelProperty(value = "日期")
    private String date;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    @Column(name = "ammonia_gas")
    @ApiModelProperty(value = "氨气")
    private String ammoniaGas;

    @Column(name = "carbonic_oxide")
    @ApiModelProperty(value = "一氧化碳")
    private String carbonicOxide;

    @Column(name = "combustible_gas")
    @ApiModelProperty(value = "可燃气体")
    private String combustibleGas;

    public void copy(Environment source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}