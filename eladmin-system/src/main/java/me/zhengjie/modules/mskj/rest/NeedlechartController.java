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
import me.zhengjie.modules.mskj.domain.Needlechart;
import me.zhengjie.modules.mskj.service.NeedlechartService;
import me.zhengjie.modules.mskj.service.dto.NeedlechartQueryCriteria;
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
@Api(tags = "NeedleChart管理")
@RequestMapping("/api/needlechart")
public class NeedlechartController {

    private final NeedlechartService needlechartService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('needlechart:list')")
    public void download(HttpServletResponse response, NeedlechartQueryCriteria criteria) throws IOException {
        needlechartService.download(needlechartService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询NeedleChart")
    @ApiOperation("查询NeedleChart")
    @PreAuthorize("@el.check('needlechart:list')")
    public ResponseEntity<Object> query(NeedlechartQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(needlechartService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增NeedleChart")
    @ApiOperation("新增NeedleChart")
    @PreAuthorize("@el.check('needlechart:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Needlechart resources){
        return new ResponseEntity<>(needlechartService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改NeedleChart")
    @ApiOperation("修改NeedleChart")
    @PreAuthorize("@el.check('needlechart:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Needlechart resources){
        needlechartService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除NeedleChart")
    @ApiOperation("删除NeedleChart")
    @PreAuthorize("@el.check('needlechart:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        needlechartService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}