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

import me.zhengjie.modules.mskj.domain.Robot;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.RobotRepository;
import me.zhengjie.modules.mskj.service.RobotService;
import me.zhengjie.modules.mskj.service.dto.RobotDto;
import me.zhengjie.modules.mskj.service.dto.RobotQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.RobotMapper;
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
* @date 2020-10-27
**/
@Service
@RequiredArgsConstructor
public class RobotServiceImpl implements RobotService {

    private final RobotRepository robotRepository;
    private final RobotMapper robotMapper;

    @Override
    public Map<String,Object> queryAll(RobotQueryCriteria criteria, Pageable pageable){
        Page<Robot> page = robotRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(robotMapper::toDto));
    }

    @Override
    public List<RobotDto> queryAll(RobotQueryCriteria criteria){
        return robotMapper.toDto(robotRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public RobotDto findById(String robotId) {
        Robot robot = robotRepository.findById(robotId).orElseGet(Robot::new);
        ValidationUtil.isNull(robot.getRobotId(),"Robot","robotId",robotId);
        return robotMapper.toDto(robot);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RobotDto create(Robot resources) {
        resources.setRobotId(IdUtil.simpleUUID()); 
        return robotMapper.toDto(robotRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Robot resources) {
        Robot robot = robotRepository.findById(resources.getRobotId()).orElseGet(Robot::new);
        ValidationUtil.isNull( robot.getRobotId(),"Robot","id",resources.getRobotId());
        robot.copy(resources);
        robotRepository.save(robot);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String robotId : ids) {
            robotRepository.deleteById(robotId);
        }
    }

    @Override
    public void download(List<RobotDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RobotDto robot : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("机器人编号", robot.getRobotNo());
            map.put("机器人名称", robot.getName());
            map.put("机器人运行状态", robot.getActionStatus());
            map.put("机器人当前运行模式（0任务模式，1紧急定位模式，2后台遥控模式，3手持遥控模式，4正在充电）", robot.getMode());
            map.put("机器人连接状态（0表示未连接，1表示已连接）", robot.getConnectStatus());
            map.put("机器人位置信息", robot.getLocation());
            map.put("机器人安全证书", robot.getAuth());
            map.put("剩余电量", robot.getPower());
            map.put("描述", robot.getDescription());
            map.put("四驱状态(1,0,1,0)", robot.getDriverStatus());
            map.put("车速", robot.getSpeed());
            map.put("网络信号值", robot.getNet());
            map.put("传感器设备状态", robot.getDevicesStatus());
            map.put("可见光通道", robot.getChannelLight());
            map.put("热红外通道", robot.getChannelInfrared());
            map.put("当前是否受控（默认0是，1否）", robot.getControl());
            map.put("机体ip", robot.getRobotIp());
            map.put("可见光视频ip", robot.getLightIp());
            map.put("热红外视频ip", robot.getInfraredIp());
            map.put("可见光端口", robot.getLightPort());
            map.put("热红外端口", robot.getInfraredPort());
            map.put("可见光用户名", robot.getLightName());
            map.put("热红外用户名", robot.getInfraredName());
            map.put("可见光密码", robot.getLightPassword());
            map.put("热红外密码", robot.getInfraredPassword());
            map.put("0代表巡检，1代表人脸识别", robot.getFaceRecognition());
            map.put("地图ID", robot.getMapId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}