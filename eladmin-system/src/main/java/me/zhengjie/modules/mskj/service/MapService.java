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
package me.zhengjie.modules.mskj.service;

import me.zhengjie.modules.mskj.domain.Map;
import me.zhengjie.modules.mskj.service.dto.MapDto;
import me.zhengjie.modules.mskj.service.dto.MapQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Fu Ding
 * @description 服务接口
 * @date 2020-11-19
 **/
public interface MapService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    java.util.Map<String, Object> queryAll(MapQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List<MapDto>
     */
    List<MapDto> queryAll(MapQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param mapId ID
     * @return MapDto
     */
    MapDto findById(String mapId);

    /**
     * 创建
     *
     * @param resources /
     * @return MapDto
     */
    MapDto create(Map resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Map resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    void deleteAll(String[] ids);

    /**
     * 导出数据
     *
     * @param all      待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<MapDto> all, HttpServletResponse response) throws IOException;
}