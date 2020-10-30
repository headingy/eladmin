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

import me.zhengjie.modules.mskj.domain.Device;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.DeviceRepository;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.service.dto.DeviceDto;
import me.zhengjie.modules.mskj.service.dto.DeviceQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.DeviceMapper;
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
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public Map<String,Object> queryAll(DeviceQueryCriteria criteria, Pageable pageable){
        Page<Device> page = deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deviceMapper::toDto));
    }

    @Override
    public List<DeviceDto> queryAll(DeviceQueryCriteria criteria){
        return deviceMapper.toDto(deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public DeviceDto findById(String deviceId) {
        Device device = deviceRepository.findById(deviceId).orElseGet(Device::new);
        ValidationUtil.isNull(device.getDeviceId(),"Device","deviceId",deviceId);
        return deviceMapper.toDto(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceDto create(Device resources) {
        resources.setDeviceId(IdUtil.simpleUUID()); 
        return deviceMapper.toDto(deviceRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Device resources) {
        Device device = deviceRepository.findById(resources.getDeviceId()).orElseGet(Device::new);
        ValidationUtil.isNull( device.getDeviceId(),"Device","id",resources.getDeviceId());
        device.copy(resources);
        deviceRepository.save(device);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String deviceId : ids) {
            deviceRepository.deleteById(deviceId);
        }
    }

    @Override
    public void download(List<DeviceDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DeviceDto device : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("巡检设备名称", device.getDeviceName());
            map.put("设备编号", device.getDeviceNo());
            map.put("巡检设备坐标（x,y）", device.getDeviceLocation());
            map.put("巡检设备类型（常量）", device.getDeviceType());
            map.put("设备所属区域", device.getDeviceDirect());
            map.put("设备朝向（0北 ，1东，2南，3西）", device.getDeviceOriention());
            map.put("地图id", device.getMapId());
            map.put("类型", device.getType());
            map.put("缺陷类型(0电流致热型，1电压致热型，2表示无缺陷)", device.getDefectType());
            map.put("识别类型(0可见光，1可见光+热红外，2声音检测，3位置状态识别，4表计识别，5导航识别,6是否工作，7是否漏油）", device.getInspectType());
            map.put("表针类型(0指针，1:LED数字，2一态档位，3两态档位，4三态档位，5指示针)", device.getPointerType());
            map.put("表计类型(0油位表，1温度表，2一态档位表，3，SF6压力表，4泄漏电流表，5表示无，不是表计类型 ,6两态档位表，7三态档位表，8 :LED彩色数字仪表，9:LED灰色数字仪表，10电气指示灯仪表 11多示数表）", device.getNeedleType());
            map.put("宽度", device.getWidth());
            map.put("高度", device.getHeight());
            map.put("半径", device.getRadius());
            map.put("颜色", device.getColor());
            map.put("角度偏差（x,y,z）", device.getAngle());
            map.put("设备运行状态（0.正常 1.损坏）", device.getDeviceStatus());
            map.put("告警阈值", device.getThreshold());
            map.put("仪表类型(设备类型为DV1028）仪表类型表的id", device.getMeterType());
            map.put("圆形表计直径", device.getDiameter());
            map.put("形状（高宽比）设备类型为DV1028", device.getShape());
            map.put("表计安装角度", device.getInstallAngle());
            map.put("最小刻度（设备类型为DV1028）", device.getMinScale());
            map.put("最小刻度对应角度（设备类型为DV1028）", device.getMinScaleAngle());
            map.put("中间刻度（设备类型为DV1028）", device.getMidScale());
            map.put("中间刻度对应的角度（设备类型为DV1028）", device.getMidScaleAngle());
            map.put("最大刻度（设备类型为DV1028）", device.getMaxScale());
            map.put("最大刻度对应角度（设备类型为DV1028）", device.getMaxScaleAngle());
            map.put("表盘在地图中的位置（x,y,z)设备类型为DV1028", device.getMeterLocation());
            map.put("表盘在地图中的角度（x,y,z,w）设备类型为DV1028", device.getMeterAngle());
            map.put("表盘是否均匀（0表示均匀，1表示不均匀）", device.getEven());
            map.put("表盘不均匀时角度集合（以逗号分隔）", device.getEvenAngle());
            map.put("表盘不均匀时刻度集合，以逗号分隔", device.getScale());
            map.put("是否检修（0是，默认1否）", device.getFix());
            map.put("正面照的media_id", device.getPhotoMedia());
            map.put("温升", device.getTemperatureRise());
            map.put("告警阈值上限", device.getThresholdUp());
            map.put("告警阈值下限", device.getThresholdDown());
            map.put("是否三相设备（0是，默认1否）", device.getThreePhase());
            map.put("创建时间", device.getCreateTime());
            map.put("描述", device.getDescription());
            map.put(" centerLocation",  device.getCenterLocation());
            map.put("充电桩状态(0表示可用，1表示预约，2表示占用，3表示维修）", device.getChargingPileStatus());
            map.put("经纬度", device.getLongitudeLatitude());
            map.put("巡检位置", device.getInspectLocation());
            map.put("充电桩状态（0表示空闲，1表示已预约，2表示充电中，3表示异常）", device.getChargingStatus());
            map.put(" multichartAnnotation",  device.getMultichartAnnotation());
            map.put(" listsNum",  device.getListsNum());
            map.put(" scaleLists",  device.getScaleLists());
            map.put(" angleLists",  device.getAngleLists());
            map.put(" deviceSum",  device.getDeviceSum());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}