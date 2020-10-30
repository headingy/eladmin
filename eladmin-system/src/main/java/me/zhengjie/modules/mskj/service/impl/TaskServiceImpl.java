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

import me.zhengjie.modules.mskj.domain.Task;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.TaskRepository;
import me.zhengjie.modules.mskj.service.TaskService;
import me.zhengjie.modules.mskj.service.dto.TaskDto;
import me.zhengjie.modules.mskj.service.dto.TaskQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.TaskMapper;
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
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public Map<String,Object> queryAll(TaskQueryCriteria criteria, Pageable pageable){
        Page<Task> page = taskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(taskMapper::toDto));
    }

    @Override
    public List<TaskDto> queryAll(TaskQueryCriteria criteria){
        return taskMapper.toDto(taskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public TaskDto findById(String taskId) {
        Task task = taskRepository.findById(taskId).orElseGet(Task::new);
        ValidationUtil.isNull(task.getTaskId(),"Task","taskId",taskId);
        return taskMapper.toDto(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TaskDto create(Task resources) {
        resources.setTaskId(IdUtil.simpleUUID()); 
        return taskMapper.toDto(taskRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Task resources) {
        Task task = taskRepository.findById(resources.getTaskId()).orElseGet(Task::new);
        ValidationUtil.isNull( task.getTaskId(),"Task","id",resources.getTaskId());
        task.copy(resources);
        taskRepository.save(task);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String taskId : ids) {
            taskRepository.deleteById(taskId);
        }
    }

    @Override
    public void download(List<TaskDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TaskDto task : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务类型（默认0立即执行，1定时执行，2周期执行）", task.getTaskExecType());
            map.put("巡检类型（默认0常规巡检，1例行巡检，2特巡巡检，3专项巡检）", task.getTaskType());
            map.put("定时策略（task_exec_type为1时不能为null）", task.getCron());
            map.put("执行周期（task_exec_type为2时不能为空）", task.getPeriod());
            map.put("任务名称", task.getName());
            map.put("任务状态（0待执行，1暂停，2已终止，3已完成，4正在执行）", task.getStatus());
            map.put("任务创建时间", task.getCreateTime());
            map.put("任务开始执行时间", task.getExecTime());
            map.put("巡检路径规划（0最优路径，1先后顺序）", task.getRoad());
            map.put("任务终止时间", task.getOverTime());
            map.put("任务创建人", task.getCreater());
            map.put("任务内容", task.getContent());
            map.put("巡检点（巡检点主键集合）", task.getDevices());
            map.put("巡检结束状态（0返回充电，1原地待命，2返回原点）", task.getEndStatus());
            map.put("任务执行结果", task.getResult());
            map.put("描述", task.getDescription());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}