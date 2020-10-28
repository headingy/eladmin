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
import me.zhengjie.modules.mskj.domain.Robot;
import me.zhengjie.modules.mskj.service.RobotService;
import me.zhengjie.modules.mskj.service.dto.RobotQueryCriteria;
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
* @date 2020-10-27
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "机器人管理")
@RequestMapping("/api/robot")
public class RobotController {

    private final RobotService robotService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('robot:list')")
    public void download(HttpServletResponse response, RobotQueryCriteria criteria) throws IOException {
        robotService.download(robotService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询机器人")
    @ApiOperation("查询机器人")
    @PreAuthorize("@el.check('robot:list')")
    public ResponseEntity<Object> query(RobotQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(robotService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增机器人")
    @ApiOperation("新增机器人")
    @PreAuthorize("@el.check('robot:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Robot resources){
        return new ResponseEntity<>(robotService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改机器人")
    @ApiOperation("修改机器人")
    @PreAuthorize("@el.check('robot:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Robot resources){
        robotService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除机器人")
    @ApiOperation("删除机器人")
    @PreAuthorize("@el.check('robot:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        robotService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}