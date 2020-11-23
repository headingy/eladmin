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
package me.zhengjie.modules.mskj.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @date 2020-10-30
 **/
// comment out this since it seems there's bug with the constructor with Accessors
// so that the controller won't get the parameters when it is present
//@Accessors(fluent = true, chain = true)
@Data
public class RobotTaskDeviceQueryCriteria implements Serializable {
    @Query(type = Query.Type.IN, joinName = "device", propName = "deviceName")
    private Set<String> deviceNames = new HashSet<>();

    @Query(type = Query.Type.IN, joinName = "device", propName = "deviceType")
    private Set<String> deviceTypes= new HashSet<>();

    @Query(type = Query.Type.EQUAL, joinName = "device", propName = "deviceId")
    private String deviceId;

    @Query(type = Query.Type.IN, joinName = "robotTask", propName = "robotTaskId")
    private Set<String> robotTasks = new HashSet<>();

    @Query(type = Query.Type.IN, joinName = "robotTask>task", propName = "name")
    private Set<String> taskNames = new HashSet<>();

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> execTime;
}