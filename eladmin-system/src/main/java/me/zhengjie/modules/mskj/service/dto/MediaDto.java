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
public class MediaDto implements Serializable {

    private String mediaId;

    /** 文件名（随机数） */
    private String mediaName;

    /** 文件原始名 */
    private String mediaRealName;

    /** 类型（0.图片，1视频 2 其他，3音频） */
    private String mediaType;

    /** 二级类型,0表示图片是可见光 ，1表示图片是热红外 */
    private Integer type;

    /** 文件扩展名 */
    private String mediaExtensionName;

    /** 文件路径 */
    private String path;

    /** 时长，单位s */
    private Long timeLength;

    /** 生成时间 */
    private String createTime;

    /** 文件大小 */
    private String size;

    /** 描述 */
    private String description;
}