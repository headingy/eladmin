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
import me.zhengjie.modules.mskj.domain.WarnInfo;
import me.zhengjie.modules.mskj.service.WarnInfoService;
import me.zhengjie.modules.mskj.service.dto.WarnInfoQueryCriteria;
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
* @date 2020-10-28
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "告警消息管理")
@RequestMapping("/api/warnInfo")
public class WarnInfoController {

    private final WarnInfoService warnInfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('warnInfo:list')")
    public void download(HttpServletResponse response, WarnInfoQueryCriteria criteria) throws IOException {
        warnInfoService.download(warnInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询告警消息")
    @ApiOperation("查询告警消息")
    @PreAuthorize("@el.check('warnInfo:list')")
    public ResponseEntity<Object> query(WarnInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(warnInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增告警消息")
    @ApiOperation("新增告警消息")
    @PreAuthorize("@el.check('warnInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody WarnInfo resources){
        return new ResponseEntity<>(warnInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改告警消息")
    @ApiOperation("修改告警消息")
    @PreAuthorize("@el.check('warnInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody WarnInfo resources){
        warnInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除告警消息")
    @ApiOperation("删除告警消息")
    @PreAuthorize("@el.check('warnInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        warnInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}