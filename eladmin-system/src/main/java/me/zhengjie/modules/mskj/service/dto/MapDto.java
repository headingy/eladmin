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
* @date 2020-11-19
**/
@Data
public class MapDto implements Serializable {

    /** 地图ID */
    private String mapId;

    /** 地图名称 */
    private String mapName;

    /** 地图路径 */
    private String path;

    /** 地图高度 */
    private Float mapHeight;

    /** 地图宽度 */
    private Float mapWidth;

    /** 缩放比例 */
    private Float ratio;

    /** 描述 */
    private String description;

    /** 区域ID */
    private String locationId;

    private String file5;

    private String file1;

    private String file2;

    private String file3;

    private String file4;

    private String file6;

    private String file7;

    private String file8;

    private String file9;

    private String file10;
}