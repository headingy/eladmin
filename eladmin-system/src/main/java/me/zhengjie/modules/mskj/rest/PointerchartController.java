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
import me.zhengjie.modules.mskj.domain.Pointerchart;
import me.zhengjie.modules.mskj.service.PointerchartService;
import me.zhengjie.modules.mskj.service.dto.PointerchartQueryCriteria;
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
@Api(tags = "PointerChart管理")
@RequestMapping("/api/pointerchart")
public class PointerchartController {

    private final PointerchartService pointerchartService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('pointerchart:list')")
    public void download(HttpServletResponse response, PointerchartQueryCriteria criteria) throws IOException {
        pointerchartService.download(pointerchartService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询PointerChart")
    @ApiOperation("查询PointerChart")
    @PreAuthorize("@el.check('pointerchart:list')")
    public ResponseEntity<Object> query(PointerchartQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(pointerchartService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增PointerChart")
    @ApiOperation("新增PointerChart")
    @PreAuthorize("@el.check('pointerchart:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Pointerchart resources){
        return new ResponseEntity<>(pointerchartService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改PointerChart")
    @ApiOperation("修改PointerChart")
    @PreAuthorize("@el.check('pointerchart:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Pointerchart resources){
        pointerchartService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除PointerChart")
    @ApiOperation("删除PointerChart")
    @PreAuthorize("@el.check('pointerchart:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        pointerchartService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}