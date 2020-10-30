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
public class EnvironmentDto implements Serializable {

    private String envId;

    /** 温度 */
    private String temperature;

    /** 湿度 */
    private String humidity;

    /** 烟感 */
    private String smoke;

    /** PM2.5 */
    private String pm;

    /** 氧气 */
    private String oxygen;

    /** 硫化氢 */
    private String hydrothion;

    /** 风速 */
    private String wind;

    /** 机器人任务id */
    private String robotTaskId;

    /** 日期 */
    private String date;

    /** 描述 */
    private String description;

    /** 氨气 */
    private String ammoniaGas;

    /** 一氧化碳 */
    private String carbonicOxide;

    /** 可燃气体 */
    private String combustibleGas;
}