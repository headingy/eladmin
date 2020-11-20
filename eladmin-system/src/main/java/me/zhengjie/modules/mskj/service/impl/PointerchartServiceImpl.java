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

import me.zhengjie.modules.mskj.domain.Pointerchart;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.PointerchartRepository;
import me.zhengjie.modules.mskj.service.PointerchartService;
import me.zhengjie.modules.mskj.service.dto.PointerchartDto;
import me.zhengjie.modules.mskj.service.dto.PointerchartQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.PointerchartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author Fu Ding
* @date 2020-11-20
**/
@Service
@RequiredArgsConstructor
public class PointerchartServiceImpl implements PointerchartService {

    private final PointerchartRepository pointerchartRepository;
    private final PointerchartMapper pointerchartMapper;

    @Override
    public Map<String,Object> queryAll(PointerchartQueryCriteria criteria, Pageable pageable){
        Page<Pointerchart> page = pointerchartRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(pointerchartMapper::toDto));
    }

    @Override
    public List<PointerchartDto> queryAll(PointerchartQueryCriteria criteria){
        return pointerchartMapper.toDto(pointerchartRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PointerchartDto findById(String chartid) {
        Pointerchart pointerchart = pointerchartRepository.findById(chartid).orElseGet(Pointerchart::new);
        ValidationUtil.isNull(pointerchart.getChartid(),"Pointerchart","chartid",chartid);
        return pointerchartMapper.toDto(pointerchart);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PointerchartDto create(Pointerchart resources) {
        resources.setChartid(IdUtil.simpleUUID()); 
        return pointerchartMapper.toDto(pointerchartRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Pointerchart resources) {
        Pointerchart pointerchart = pointerchartRepository.findById(resources.getChartid()).orElseGet(Pointerchart::new);
        ValidationUtil.isNull( pointerchart.getChartid(),"Pointerchart","id",resources.getChartid());
        pointerchart.copy(resources);
        pointerchartRepository.save(pointerchart);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String chartid : ids) {
            pointerchartRepository.deleteById(chartid);
        }
    }

    @Override
    public void download(List<PointerchartDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PointerchartDto pointerchart : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" annotation",  pointerchart.getAnnotation());
            map.put(" value",  pointerchart.getValue());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}