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
import me.zhengjie.modules.mskj.domain.Device;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.service.dto.DeviceQueryCriteria;
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
* @date 2020-11-02
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "巡检点管理")
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('device:list')")
    public void download(HttpServletResponse response, DeviceQueryCriteria criteria) throws IOException {
        deviceService.download(deviceService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询巡检点")
    @ApiOperation("查询巡检点")
    @PreAuthorize("@el.check('device:list')")
    public ResponseEntity<Object> query(DeviceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(deviceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增巡检点")
    @ApiOperation("新增巡检点")
    @PreAuthorize("@el.check('device:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Device resources){
        return new ResponseEntity<>(deviceService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改巡检点")
    @ApiOperation("修改巡检点")
    @PreAuthorize("@el.check('device:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Device resources){
        deviceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除巡检点")
    @ApiOperation("删除巡检点")
    @PreAuthorize("@el.check('device:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        deviceService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}