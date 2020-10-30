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
import me.zhengjie.modules.mskj.domain.RobotTaskDevice;
import me.zhengjie.modules.mskj.service.RobotTaskDeviceService;
import me.zhengjie.modules.mskj.service.dto.RobotTaskDeviceQueryCriteria;
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
@Api(tags = "任务设备数据管理")
@RequestMapping("/api/robotTaskDevice")
public class RobotTaskDeviceController {

    private final RobotTaskDeviceService robotTaskDeviceService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('robotTaskDevice:list')")
    public void download(HttpServletResponse response, RobotTaskDeviceQueryCriteria criteria) throws IOException {
        robotTaskDeviceService.download(robotTaskDeviceService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询任务设备数据")
    @ApiOperation("查询任务设备数据")
    @PreAuthorize("@el.check('robotTaskDevice:list')")
    public ResponseEntity<Object> query(RobotTaskDeviceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(robotTaskDeviceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增任务设备数据")
    @ApiOperation("新增任务设备数据")
    @PreAuthorize("@el.check('robotTaskDevice:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody RobotTaskDevice resources){
        return new ResponseEntity<>(robotTaskDeviceService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改任务设备数据")
    @ApiOperation("修改任务设备数据")
    @PreAuthorize("@el.check('robotTaskDevice:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody RobotTaskDevice resources){
        robotTaskDeviceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除任务设备数据")
    @ApiOperation("删除任务设备数据")
    @PreAuthorize("@el.check('robotTaskDevice:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        robotTaskDeviceService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}