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

import me.zhengjie.modules.mskj.domain.WarnInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.repository.WarnInfoRepository;
import me.zhengjie.modules.mskj.service.WarnInfoService;
import me.zhengjie.modules.mskj.service.dto.WarnInfoDto;
import me.zhengjie.modules.mskj.service.dto.WarnInfoQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.WarnInfoMapper;
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
* @date 2020-10-28
**/
@Service
@RequiredArgsConstructor
public class WarnInfoServiceImpl implements WarnInfoService {

    private final WarnInfoRepository warnInfoRepository;
    private final WarnInfoMapper warnInfoMapper;

    @Override
    public Map<String,Object> queryAll(WarnInfoQueryCriteria criteria, Pageable pageable){
        Page<WarnInfo> page = warnInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(warnInfoMapper::toDto));
    }

    @Override
    public List<WarnInfoDto> queryAll(WarnInfoQueryCriteria criteria){
        return warnInfoMapper.toDto(warnInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public WarnInfoDto findById(String infoId) {
        WarnInfo warnInfo = warnInfoRepository.findById(infoId).orElseGet(WarnInfo::new);
        ValidationUtil.isNull(warnInfo.getInfoId(),"WarnInfo","infoId",infoId);
        return warnInfoMapper.toDto(warnInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WarnInfoDto create(WarnInfo resources) {
        resources.setInfoId(IdUtil.simpleUUID()); 
        return warnInfoMapper.toDto(warnInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WarnInfo resources) {
        WarnInfo warnInfo = warnInfoRepository.findById(resources.getInfoId()).orElseGet(WarnInfo::new);
        ValidationUtil.isNull( warnInfo.getInfoId(),"WarnInfo","id",resources.getInfoId());
        warnInfo.copy(resources);
        warnInfoRepository.save(warnInfo);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String infoId : ids) {
            warnInfoRepository.deleteById(infoId);
        }
    }

    @Override
    public void download(List<WarnInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WarnInfoDto warnInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("机器人任务id", warnInfo.getRobotTaskId());
            map.put("告警项", warnInfo.getInfoKey());
            map.put("告警详细信息", warnInfo.getInfoValue());
            map.put("告警阈值", warnInfo.getThresholdValue());
            map.put("告警级别(0，一般警告，1严重警告，2危机警告)", warnInfo.getLevel());
            map.put("告警状态（0 未确认，1 已确认，2已删除）", warnInfo.getStatus());
            map.put("告警时间", warnInfo.getCreateTime());
            map.put("确认时间", warnInfo.getEnsureTime());
            map.put("识别类型(0可见光，1可见光+热红外，2声音检测，3位置状态识别，4表计识别）", warnInfo.getVerifyType());
            map.put("告警设备", warnInfo.getDeviceId());
            map.put("告警图片信息（关联media_id）", warnInfo.getPictureId());
            map.put("告警声音(关联media_id)", warnInfo.getVoiceId());
            map.put("告警可见光图片(关联media_id)", warnInfo.getLightPictureId());
            map.put("告警视频信息（关联media_id）", warnInfo.getVideoId());
            map.put("确认人", warnInfo.getConfirmer());
            map.put("描述", warnInfo.getDescription());
            map.put("信息类型（0表示时实消息，1表示系统消息，2表示巡检点告警，3表示机体告警，4表示环境告警）", warnInfo.getInfoType());
            map.put("结果状态（0表示正常，1表示异常）", warnInfo.getResultStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}