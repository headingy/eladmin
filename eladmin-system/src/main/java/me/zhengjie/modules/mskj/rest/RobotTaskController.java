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
import me.zhengjie.modules.mskj.domain.RobotTask;
import me.zhengjie.modules.mskj.service.RobotTaskService;
import me.zhengjie.modules.mskj.service.dto.RobotTaskQueryCriteria;
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
@Api(tags = "机器人任务管理")
@RequestMapping("/api/robotTask")
public class RobotTaskController {

    private final RobotTaskService robotTaskService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('robotTask:list')")
    public void download(HttpServletResponse response, RobotTaskQueryCriteria criteria) throws IOException {
        robotTaskService.download(robotTaskService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询机器人任务")
    @ApiOperation("查询机器人任务")
    @PreAuthorize("@el.check('robotTask:list')")
    public ResponseEntity<Object> query(RobotTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(robotTaskService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增机器人任务")
    @ApiOperation("新增机器人任务")
    @PreAuthorize("@el.check('robotTask:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody RobotTask resources){
        return new ResponseEntity<>(robotTaskService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改机器人任务")
    @ApiOperation("修改机器人任务")
    @PreAuthorize("@el.check('robotTask:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody RobotTask resources){
        robotTaskService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除机器人任务")
    @ApiOperation("删除机器人任务")
    @PreAuthorize("@el.check('robotTask:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        robotTaskService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}