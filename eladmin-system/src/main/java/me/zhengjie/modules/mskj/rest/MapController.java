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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.mskj.domain.Map;
import me.zhengjie.modules.mskj.service.MapService;
import me.zhengjie.modules.mskj.service.dto.MapQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @website https://el-admin.vip
* @author Fu Ding
* @date 2020-11-19
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "地图管理")
@RequestMapping("/api/map")
public class MapController {

    private final MapService mapService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('map:list')")
    public void download(HttpServletResponse response, MapQueryCriteria criteria) throws IOException {
        mapService.download(mapService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询地图")
    @ApiOperation("查询地图")
    @PreAuthorize("@el.check('map:list')")
    public ResponseEntity<Object> query(MapQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(mapService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增地图")
    @ApiOperation("新增地图")
    @PreAuthorize("@el.check('map:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Map resources){
        return new ResponseEntity<>(mapService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改地图")
    @ApiOperation("修改地图")
    @PreAuthorize("@el.check('map:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Map resources){
        mapService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除地图")
    @ApiOperation("删除地图")
    @PreAuthorize("@el.check('map:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        mapService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}