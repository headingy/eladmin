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
import me.zhengjie.modules.mskj.service.AutomatonService;
import me.zhengjie.modules.mskj.service.dto.RobotQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @website https://el-admin.vip
* @author Fu Ding
* @date 2020-10-28
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "机器人本体管理")
@RequestMapping("/api/automaton")
public class AutomatonController {

    private final AutomatonService automatonService;

    @GetMapping(path = "/indexInfo")
    @Log("查询机器人")
    @ApiOperation("查询机器人")
    @PreAuthorize("@el.check('robot:list')")
    public ResponseEntity<Object> indexInfo(){
        return new ResponseEntity<>(automatonService.getIndexInfo(null),HttpStatus.OK);
    }

    @GetMapping(path = "/statusInfo")
    @Log("查询机器人")
    @ApiOperation("查询机器人")
    @PreAuthorize("@el.check('robot:list')")
    public ResponseEntity<Object> statusInfo(){
        return new ResponseEntity<>(automatonService.getStatusInfo(null),HttpStatus.OK);
    }

    @GetMapping(path = "/taskInfo")
    @Log("查询机器人")
    @ApiOperation("查询机器人")
    @PreAuthorize("@el.check('robot:list')")
    public ResponseEntity<Object> taskInfo(){
        return new ResponseEntity<>(automatonService.getTaskInfo(null),HttpStatus.OK);
    }
}