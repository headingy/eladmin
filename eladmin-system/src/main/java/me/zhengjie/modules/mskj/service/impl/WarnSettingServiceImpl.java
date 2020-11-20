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

import me.zhengjie.modules.mskj.domain.WarnSetting;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.WarnSettingRepository;
import me.zhengjie.modules.mskj.service.WarnSettingService;
import me.zhengjie.modules.mskj.service.dto.WarnSettingDto;
import me.zhengjie.modules.mskj.service.dto.WarnSettingQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.WarnSettingMapper;
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
public class WarnSettingServiceImpl implements WarnSettingService {

    private final WarnSettingRepository warnSettingRepository;
    private final WarnSettingMapper warnSettingMapper;

    @Override
    public Map<String,Object> queryAll(WarnSettingQueryCriteria criteria, Pageable pageable){
        Page<WarnSetting> page = warnSettingRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(warnSettingMapper::toDto));
    }

    @Override
    public List<WarnSettingDto> queryAll(WarnSettingQueryCriteria criteria){
        return warnSettingMapper.toDto(warnSettingRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public WarnSettingDto findById(String warnId) {
        WarnSetting warnSetting = warnSettingRepository.findById(warnId).orElseGet(WarnSetting::new);
        ValidationUtil.isNull(warnSetting.getWarnId(),"WarnSetting","warnId",warnId);
        return warnSettingMapper.toDto(warnSetting);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WarnSettingDto create(WarnSetting resources) {
        resources.setWarnId(IdUtil.simpleUUID()); 
        return warnSettingMapper.toDto(warnSettingRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WarnSetting resources) {
        WarnSetting warnSetting = warnSettingRepository.findById(resources.getWarnId()).orElseGet(WarnSetting::new);
        ValidationUtil.isNull( warnSetting.getWarnId(),"WarnSetting","id",resources.getWarnId());
        warnSetting.copy(resources);
        warnSettingRepository.save(warnSetting);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String warnId : ids) {
            warnSettingRepository.deleteById(warnId);
        }
    }

    @Override
    public void download(List<WarnSettingDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WarnSettingDto warnSetting : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("告警项", warnSetting.getWarnKey());
            map.put("告警详细信息", warnSetting.getWarnValue());
            map.put("告警阈值上限", warnSetting.getThresholdUp());
            map.put("告警阈值下限", warnSetting.getThresholdDown());
            map.put("进行短信通知（0表示未通知，1表示接受通知)", warnSetting.getInfoNotify());
            map.put("重要情况（0表示重要，1表示紧急，2表示一般）", warnSetting.getLevel());
            map.put("声光开启（0表示开启，1表示未开启）", warnSetting.getVoiceLight());
            map.put("单位", warnSetting.getUnit());
            map.put("接收者", warnSetting.getReceiver());
            map.put("告警设备", warnSetting.getDeviceType());
            map.put("描述", warnSetting.getDescription());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}