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
import me.zhengjie.modules.mskj.domain.RobotTaskDevice;
import me.zhengjie.modules.mskj.repository.RobotTaskDeviceRepository;
import me.zhengjie.modules.mskj.service.RobotTaskDeviceService;
import me.zhengjie.modules.mskj.service.dto.RobotTaskDeviceDto;
import me.zhengjie.modules.mskj.service.dto.RobotTaskDeviceQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.RobotTaskDeviceMapper;
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
import java.util.Map;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author Fu Ding
* @date 2020-10-30
**/
@Service
@RequiredArgsConstructor
public class RobotTaskDeviceServiceImpl implements RobotTaskDeviceService {

    private final RobotTaskDeviceRepository robotTaskDeviceRepository;
    private final RobotTaskDeviceMapper robotTaskDeviceMapper;

    @Override
    public Map<String,Object> queryAll(RobotTaskDeviceQueryCriteria criteria, Pageable pageable){
        Page<RobotTaskDevice> page = robotTaskDeviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(robotTaskDeviceMapper::toDto));
    }

    @Override
    public List<RobotTaskDeviceDto> queryAll(RobotTaskDeviceQueryCriteria criteria){
        return robotTaskDeviceMapper.toDto(robotTaskDeviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public RobotTaskDeviceDto findById(String robotTaskDeviceId) {
        RobotTaskDevice robotTaskDevice = robotTaskDeviceRepository.findById(robotTaskDeviceId).orElseGet(RobotTaskDevice::new);
        ValidationUtil.isNull(robotTaskDevice.getRobotTaskDeviceId(),"RobotTaskDevice","robotTaskDeviceId",robotTaskDeviceId);
        return robotTaskDeviceMapper.toDto(robotTaskDevice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RobotTaskDeviceDto create(RobotTaskDevice resources) {
        resources.setRobotTaskDeviceId(IdUtil.simpleUUID()); 
        return robotTaskDeviceMapper.toDto(robotTaskDeviceRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RobotTaskDevice resources) {
        RobotTaskDevice robotTaskDevice = robotTaskDeviceRepository.findById(resources.getRobotTaskDeviceId()).orElseGet(RobotTaskDevice::new);
        ValidationUtil.isNull( robotTaskDevice.getRobotTaskDeviceId(),"RobotTaskDevice","id",resources.getRobotTaskDeviceId());
        robotTaskDevice.copy(resources);
        robotTaskDeviceRepository.save(robotTaskDevice);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String robotTaskDeviceId : ids) {
            robotTaskDeviceRepository.deleteById(robotTaskDeviceId);
        }
    }

    @Override
    public void download(List<RobotTaskDeviceDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RobotTaskDeviceDto robotTaskDevice : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("机器人id", robotTaskDevice.getRobotId());
            map.put("机器人任务", robotTaskDevice.getRobotTask());
            map.put("记录时间", robotTaskDevice.getCreateTime());
            map.put("设备", robotTaskDevice.getDevice());
            map.put("巡检表值", robotTaskDevice.getValue());
            map.put("采集的图片信息（视频图像记录的id集合，以逗号隔开）", robotTaskDevice.getMediaIds());
            map.put("环境湿度", robotTaskDevice.getHumidity());
            map.put("环境温度", robotTaskDevice.getTemperature());
            map.put("设备最低温", robotTaskDevice.getMinTemp());
            map.put("设备最高温", robotTaskDevice.getMaxTemp());
            map.put("处理意见", robotTaskDevice.getHandleView());
            map.put("三相设备的类型（0表示A相，1表示B相，2表示C相）", robotTaskDevice.getPhaseType());
            map.put("巡检结果（0表示正常，1表示异常）", robotTaskDevice.getResult());
            map.put("备注", robotTaskDevice.getBz());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}