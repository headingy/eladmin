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

import me.zhengjie.modules.mskj.domain.RobotTask;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.RobotTaskRepository;
import me.zhengjie.modules.mskj.service.RobotTaskService;
import me.zhengjie.modules.mskj.service.dto.RobotTaskDto;
import me.zhengjie.modules.mskj.service.dto.RobotTaskQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.RobotTaskMapper;
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
public class RobotTaskServiceImpl implements RobotTaskService {

    private final RobotTaskRepository robotTaskRepository;
    private final RobotTaskMapper robotTaskMapper;

    @Override
    public Map<String,Object> queryAll(RobotTaskQueryCriteria criteria, Pageable pageable){
        Page<RobotTask> page = robotTaskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(robotTaskMapper::toDto));
    }

    @Override
    public List<RobotTaskDto> queryAll(RobotTaskQueryCriteria criteria){
        return robotTaskMapper.toDto(robotTaskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public RobotTaskDto findById(String robotTaskId) {
        RobotTask robotTask = robotTaskRepository.findById(robotTaskId).orElseGet(RobotTask::new);
        ValidationUtil.isNull(robotTask.getRobotTaskId(),"RobotTask","robotTaskId",robotTaskId);
        return robotTaskMapper.toDto(robotTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RobotTaskDto create(RobotTask resources) {
        resources.setRobotTaskId(IdUtil.simpleUUID()); 
        return robotTaskMapper.toDto(robotTaskRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RobotTask resources) {
        RobotTask robotTask = robotTaskRepository.findById(resources.getRobotTaskId()).orElseGet(RobotTask::new);
        ValidationUtil.isNull( robotTask.getRobotTaskId(),"RobotTask","id",resources.getRobotTaskId());
        robotTask.copy(resources);
        robotTaskRepository.save(robotTask);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String robotTaskId : ids) {
            robotTaskRepository.deleteById(robotTaskId);
        }
    }

    @Override
    public void download(List<RobotTaskDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RobotTaskDto robotTask : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("机器人id", robotTask.getRobotId());
            map.put("任务id", robotTask.getTaskId());
            map.put("原执行时间", robotTask.getOldExecTime());
            map.put("执行时间", robotTask.getExecTime());
            map.put("执行状态（默认0表示待执行，1表示正在执行，2表示已完成，3表示已终止，4表示不执行，5暂停）", robotTask.getExecStatus());
            map.put("是否执行（默认0是，1否）", robotTask.getExecIf());
            map.put("暂停的设备id", robotTask.getWaitDevice());
            map.put("终止时间", robotTask.getEndTime());
            map.put("备注", robotTask.getBz());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}