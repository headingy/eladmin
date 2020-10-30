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

import me.zhengjie.modules.mskj.domain.Environment;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.EnvironmentRepository;
import me.zhengjie.modules.mskj.service.EnvironmentService;
import me.zhengjie.modules.mskj.service.dto.EnvironmentDto;
import me.zhengjie.modules.mskj.service.dto.EnvironmentQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.EnvironmentMapper;
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
* @date 2020-10-30
**/
@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentRepository environmentRepository;
    private final EnvironmentMapper environmentMapper;

    @Override
    public Map<String,Object> queryAll(EnvironmentQueryCriteria criteria, Pageable pageable){
        Page<Environment> page = environmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(environmentMapper::toDto));
    }

    @Override
    public List<EnvironmentDto> queryAll(EnvironmentQueryCriteria criteria){
        return environmentMapper.toDto(environmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public EnvironmentDto findById(String envId) {
        Environment environment = environmentRepository.findById(envId).orElseGet(Environment::new);
        ValidationUtil.isNull(environment.getEnvId(),"Environment","envId",envId);
        return environmentMapper.toDto(environment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EnvironmentDto create(Environment resources) {
        resources.setEnvId(IdUtil.simpleUUID()); 
        return environmentMapper.toDto(environmentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Environment resources) {
        Environment environment = environmentRepository.findById(resources.getEnvId()).orElseGet(Environment::new);
        ValidationUtil.isNull( environment.getEnvId(),"Environment","id",resources.getEnvId());
        environment.copy(resources);
        environmentRepository.save(environment);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String envId : ids) {
            environmentRepository.deleteById(envId);
        }
    }

    @Override
    public void download(List<EnvironmentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (EnvironmentDto environment : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("温度", environment.getTemperature());
            map.put("湿度", environment.getHumidity());
            map.put("烟感", environment.getSmoke());
            map.put("PM2.5", environment.getPm());
            map.put("氧气", environment.getOxygen());
            map.put("硫化氢", environment.getHydrothion());
            map.put("风速", environment.getWind());
            map.put("机器人任务id", environment.getRobotTaskId());
            map.put("日期", environment.getDate());
            map.put("描述", environment.getDescription());
            map.put("氨气", environment.getAmmoniaGas());
            map.put("一氧化碳", environment.getCarbonicOxide());
            map.put("可燃气体", environment.getCombustibleGas());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}