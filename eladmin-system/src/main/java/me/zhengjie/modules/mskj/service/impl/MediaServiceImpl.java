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
import me.zhengjie.modules.mskj.domain.Media;
import me.zhengjie.modules.mskj.repository.MediaRepository;
import me.zhengjie.modules.mskj.service.MediaService;
import me.zhengjie.modules.mskj.service.dto.MediaDto;
import me.zhengjie.modules.mskj.service.dto.MediaQueryCriteria;
import me.zhengjie.modules.mskj.service.mapstruct.MediaMapper;
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
 * @author Fu Ding
 * @website https://el-admin.vip
 * @description 服务实现
 * @date 2020-10-30
 **/
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    @Override
    public Map<String, Object> queryAll(MediaQueryCriteria criteria, Pageable pageable) {
        Page<Media> page = mediaRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(mediaMapper::toDto));
    }

    @Override
    public List<MediaDto> queryAll(MediaQueryCriteria criteria) {
        return mediaMapper.toDto(mediaRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public MediaDto findById(String mediaId) {
        Media media = mediaRepository.findById(mediaId).orElseGet(Media::new);
        ValidationUtil.isNull(media.getMediaId(), "Media", "mediaId", mediaId);
        return mediaMapper.toDto(media);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MediaDto create(Media resources) {
        resources.setMediaId(IdUtil.simpleUUID());
        return mediaMapper.toDto(mediaRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Media resources) {
        Media media = mediaRepository.findById(resources.getMediaId()).orElseGet(Media::new);
        ValidationUtil.isNull(media.getMediaId(), "Media", "id", resources.getMediaId());
        media.copy(resources);
        mediaRepository.save(media);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String mediaId : ids) {
            mediaRepository.deleteById(mediaId);
        }
    }

    @Override
    public void download(List<MediaDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MediaDto media : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("文件名（随机数）", media.getMediaName());
            map.put("文件原始名", media.getMediaRealName());
            map.put("类型（0.图片，1视频 2 其他，3音频）", media.getMediaType());
            map.put("二级类型,0表示图片是可见光 ，1表示图片是热红外", media.getType());
            map.put("文件扩展名", media.getMediaExtensionName());
            map.put("文件路径", media.getPath());
            map.put("时长，单位s", media.getTimeLength());
            map.put("生成时间", media.getCreateTime());
            map.put("文件大小", media.getSize());
            map.put("描述", media.getDescription());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}