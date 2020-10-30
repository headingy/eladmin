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
import me.zhengjie.modules.mskj.domain.Environment;
import me.zhengjie.modules.mskj.service.EnvironmentService;
import me.zhengjie.modules.mskj.service.dto.EnvironmentQueryCriteria;
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
@Api(tags = "环境数据管理")
@RequestMapping("/api/environment")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('environment:list')")
    public void download(HttpServletResponse response, EnvironmentQueryCriteria criteria) throws IOException {
        environmentService.download(environmentService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询环境数据")
    @ApiOperation("查询环境数据")
    @PreAuthorize("@el.check('environment:list')")
    public ResponseEntity<Object> query(EnvironmentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(environmentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增环境数据")
    @ApiOperation("新增环境数据")
    @PreAuthorize("@el.check('environment:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Environment resources){
        return new ResponseEntity<>(environmentService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改环境数据")
    @ApiOperation("修改环境数据")
    @PreAuthorize("@el.check('environment:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Environment resources){
        environmentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除环境数据")
    @ApiOperation("删除环境数据")
    @PreAuthorize("@el.check('environment:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        environmentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}