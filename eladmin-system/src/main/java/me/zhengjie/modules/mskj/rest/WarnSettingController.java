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
import me.zhengjie.modules.mskj.domain.WarnSetting;
import me.zhengjie.modules.mskj.service.WarnSettingService;
import me.zhengjie.modules.mskj.service.dto.WarnSettingQueryCriteria;
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
* @date 2020-11-20
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "WarnSetting管理")
@RequestMapping("/api/warnSetting")
public class WarnSettingController {

    private final WarnSettingService warnSettingService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('warnSetting:list')")
    public void download(HttpServletResponse response, WarnSettingQueryCriteria criteria) throws IOException {
        warnSettingService.download(warnSettingService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询WarnSetting")
    @ApiOperation("查询WarnSetting")
    @PreAuthorize("@el.check('warnSetting:list')")
    public ResponseEntity<Object> query(WarnSettingQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(warnSettingService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增WarnSetting")
    @ApiOperation("新增WarnSetting")
    @PreAuthorize("@el.check('warnSetting:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody WarnSetting resources){
        return new ResponseEntity<>(warnSettingService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改WarnSetting")
    @ApiOperation("修改WarnSetting")
    @PreAuthorize("@el.check('warnSetting:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody WarnSetting resources){
        warnSettingService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除WarnSetting")
    @ApiOperation("删除WarnSetting")
    @PreAuthorize("@el.check('warnSetting:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        warnSettingService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}