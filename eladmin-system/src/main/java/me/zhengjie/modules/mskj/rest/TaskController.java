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
import me.zhengjie.modules.mskj.domain.Task;
import me.zhengjie.modules.mskj.service.TaskService;
import me.zhengjie.modules.mskj.service.dto.TaskQueryCriteria;
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
@Api(tags = "任务管理")
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('task:list')")
    public void download(HttpServletResponse response, TaskQueryCriteria criteria) throws IOException {
        taskService.download(taskService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询任务")
    @ApiOperation("查询任务")
    @PreAuthorize("@el.check('task:list')")
    public ResponseEntity<Object> query(TaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(taskService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增任务")
    @ApiOperation("新增任务")
    @PreAuthorize("@el.check('task:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Task resources){
        return new ResponseEntity<>(taskService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改任务")
    @ApiOperation("修改任务")
    @PreAuthorize("@el.check('task:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Task resources){
        taskService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除任务")
    @ApiOperation("删除任务")
    @PreAuthorize("@el.check('task:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        taskService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}