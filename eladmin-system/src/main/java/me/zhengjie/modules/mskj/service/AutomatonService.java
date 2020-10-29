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
package me.zhengjie.modules.mskj.service;

import net.sf.json.JSONObject;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2020-10-28
 **/
public interface AutomatonService {

    /**
     * 查询机器人首页所需信息
     *
     * @param robotId 机器人ID
     * @return String
     */
    JSONObject getIndexInfo(String robotId);

    /**
     * 查询机器人状态信息
     *
     * @param robotId 机器人ID
     * @return String
     */
    JSONObject getStatusInfo(String robotId);

    /**
     * 查询机器人任务信息
     *
     * @param robotId 机器人ID
     * @return String
     */
    JSONObject getTaskInfo(String robotId);

}