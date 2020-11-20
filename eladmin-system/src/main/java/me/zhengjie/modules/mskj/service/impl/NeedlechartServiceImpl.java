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

import me.zhengjie.modules.mskj.domain.Needlechart;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.NeedlechartRepository;
import me.zhengjie.modules.mskj.service.NeedlechartService;
import me.zhengjie.modules.mskj.service.dto.NeedlechartDto;
import me.zhengjie.modules.mskj.service.dto.NeedlechartQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.NeedlechartMapper;
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
public class NeedlechartServiceImpl implements NeedlechartService {

    private final NeedlechartRepository needlechartRepository;
    private final NeedlechartMapper needlechartMapper;

    @Override
    public Map<String,Object> queryAll(NeedlechartQueryCriteria criteria, Pageable pageable){
        Page<Needlechart> page = needlechartRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(needlechartMapper::toDto));
    }

    @Override
    public List<NeedlechartDto> queryAll(NeedlechartQueryCriteria criteria){
        return needlechartMapper.toDto(needlechartRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NeedlechartDto findById(String chartid) {
        Needlechart needlechart = needlechartRepository.findById(chartid).orElseGet(Needlechart::new);
        ValidationUtil.isNull(needlechart.getChartid(),"Needlechart","chartid",chartid);
        return needlechartMapper.toDto(needlechart);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NeedlechartDto create(Needlechart resources) {
        resources.setChartid(IdUtil.simpleUUID()); 
        return needlechartMapper.toDto(needlechartRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Needlechart resources) {
        Needlechart needlechart = needlechartRepository.findById(resources.getChartid()).orElseGet(Needlechart::new);
        ValidationUtil.isNull( needlechart.getChartid(),"Needlechart","id",resources.getChartid());
        needlechart.copy(resources);
        needlechartRepository.save(needlechart);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String chartid : ids) {
            needlechartRepository.deleteById(chartid);
        }
    }

    @Override
    public void download(List<NeedlechartDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NeedlechartDto needlechart : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" value",  needlechart.getValue());
            map.put(" annotation",  needlechart.getAnnotation());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}