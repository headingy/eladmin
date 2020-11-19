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
package me.zhengjie.modules.mskj.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.domain.Map;
import me.zhengjie.modules.mskj.repository.MapRepository;
import me.zhengjie.modules.mskj.service.MapService;
import me.zhengjie.modules.mskj.service.dto.MapDto;
import me.zhengjie.modules.mskj.service.dto.MapQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.MapMapper;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Fu Ding
 * @description 服务实现
 * @date 2020-11-19
 **/
@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final MapRepository mapRepository;
    private final MapMapper mapMapper;

    @Override
    public java.util.Map<String, Object> queryAll(MapQueryCriteria criteria, Pageable pageable) {
        Page<Map> page = mapRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(mapMapper::toDto));
    }

    @Override
    public List<MapDto> queryAll(MapQueryCriteria criteria) {
        return mapMapper.toDto(mapRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public MapDto findById(String mapId) {
        Map map = mapRepository.findById(mapId).orElseGet(Map::new);
        ValidationUtil.isNull(map.getMapId(), "Map", "mapId", mapId);
        return mapMapper.toDto(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MapDto create(Map resources) {
        resources.setMapId(IdUtil.simpleUUID());
        return mapMapper.toDto(mapRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Map resources) {
        Map map = mapRepository.findById(resources.getMapId()).orElseGet(Map::new);
        ValidationUtil.isNull(map.getMapId(), "Map", "id", resources.getMapId());
        map.copy(resources);
        mapRepository.save(map);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String mapId : ids) {
            mapRepository.deleteById(mapId);
        }
    }

    @Override
    public void download(List<MapDto> all, HttpServletResponse response) throws IOException {
        List<java.util.Map<String, Object>> list = new ArrayList<>();
        for (MapDto mapDto : all) {
            java.util.Map<String, Object> map = new LinkedHashMap<>();
            map.put("地图名称", mapDto.getMapName());
            map.put("地图路径", mapDto.getPath());
            map.put("地图高度", mapDto.getMapHeight());
            map.put("地图宽度", mapDto.getMapWidth());
            map.put("缩放比例", mapDto.getRatio());
            map.put("描述", mapDto.getDescription());
            map.put("区域ID", mapDto.getLocationId());
            map.put(" file5", mapDto.getFile5());
            map.put(" file1", mapDto.getFile1());
            map.put(" file2", mapDto.getFile2());
            map.put(" file3", mapDto.getFile3());
            map.put(" file4", mapDto.getFile4());
            map.put(" file6", mapDto.getFile6());
            map.put(" file7", mapDto.getFile7());
            map.put(" file8", mapDto.getFile8());
            map.put(" file9", mapDto.getFile9());
            map.put(" file10", mapDto.getFile10());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}