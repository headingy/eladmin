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
@Table(name="sys_media")
public class Media implements Serializable {

    @Id
    @Column(name = "media_id")
    @ApiModelProperty(value = "mediaId")
    private String mediaId;

    @Column(name = "media_name")
    @ApiModelProperty(value = "文件名（随机数）")
    private String mediaName;

    @Column(name = "media_real_name")
    @ApiModelProperty(value = "文件原始名")
    private String mediaRealName;

    @Column(name = "media_type")
    @ApiModelProperty(value = "类型（0.图片，1视频 2 其他，3音频）")
    private String mediaType;

    @Column(name = "type")
    @ApiModelProperty(value = "二级类型,0表示图片是可见光 ，1表示图片是热红外")
    private Integer type;

    @Column(name = "media_extension_name")
    @ApiModelProperty(value = "文件扩展名")
    private String mediaExtensionName;

    @Column(name = "path")
    @ApiModelProperty(value = "文件路径")
    private String path;

    @Column(name = "time_length")
    @ApiModelProperty(value = "时长，单位s")
    private Long timeLength;

    @Column(name = "create_time")
    @ApiModelProperty(value = "生成时间")
    private String createTime;

    @Column(name = "size")
    @ApiModelProperty(value = "文件大小")
    private String size;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    public void copy(Media source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}