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
package me.zhengjie.modules.mskj.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.mskj.domain.Media;
import me.zhengjie.modules.mskj.service.MediaService;
import me.zhengjie.modules.mskj.service.dto.MediaQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author Fu Ding
* @date 2020-10-30
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "媒体文件管理")
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('media:list')")
    public void download(HttpServletResponse response, MediaQueryCriteria criteria) throws IOException {
        mediaService.download(mediaService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询媒体文件")
    @ApiOperation("查询媒体文件")
    @PreAuthorize("@el.check('media:list')")
    public ResponseEntity<Object> query(MediaQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(mediaService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增媒体文件")
    @ApiOperation("新增媒体文件")
    @PreAuthorize("@el.check('media:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Media resources){
        return new ResponseEntity<>(mediaService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改媒体文件")
    @ApiOperation("修改媒体文件")
    @PreAuthorize("@el.check('media:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Media resources){
        mediaService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除媒体文件")
    @ApiOperation("删除媒体文件")
    @PreAuthorize("@el.check('media:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        mediaService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}