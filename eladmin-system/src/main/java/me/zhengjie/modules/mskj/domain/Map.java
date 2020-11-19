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
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author Fu Ding
* @date 2020-11-19
**/
@Entity
@Data
@Table(name="sys_map")
public class Map implements Serializable {

    @Id
    @Column(name = "map_id")
    @ApiModelProperty(value = "地图ID")
    private String mapId;

    @Column(name = "map_name")
    @ApiModelProperty(value = "地图名称")
    private String mapName;

    @Column(name = "path")
    @ApiModelProperty(value = "地图路径")
    private String path;

    @Column(name = "map_height")
    @ApiModelProperty(value = "地图高度")
    private Float mapHeight;

    @Column(name = "map_width")
    @ApiModelProperty(value = "地图宽度")
    private Float mapWidth;

    @Column(name = "ratio")
    @ApiModelProperty(value = "缩放比例")
    private Float ratio;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    @Column(name = "location_id")
    @ApiModelProperty(value = "区域ID")
    private String locationId;

    @Column(name = "file5")
    @ApiModelProperty(value = "file5")
    private String file5;

    @Column(name = "file1")
    @ApiModelProperty(value = "file1")
    private String file1;

    @Column(name = "file2")
    @ApiModelProperty(value = "file2")
    private String file2;

    @Column(name = "file3")
    @ApiModelProperty(value = "file3")
    private String file3;

    @Column(name = "file4")
    @ApiModelProperty(value = "file4")
    private String file4;

    @Column(name = "file6")
    @ApiModelProperty(value = "file6")
    private String file6;

    @Column(name = "file7")
    @ApiModelProperty(value = "file7")
    private String file7;

    @Column(name = "file8")
    @ApiModelProperty(value = "file8")
    private String file8;

    @Column(name = "file9")
    @ApiModelProperty(value = "file9")
    private String file9;

    @Column(name = "file10")
    @ApiModelProperty(value = "file10")
    private String file10;

    public void copy(Map source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}