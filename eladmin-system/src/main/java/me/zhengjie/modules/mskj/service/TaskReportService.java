package me.zhengjie.modules.mskj.service;

import me.zhengjie.modules.mskj.service.dto.TaskReportDto;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2020-10-30
 **/
public interface TaskReportService {
    /**
     * 查询任务报告
     *
     * @param robotTaskId 机器人ID
     * @return TaskReportDto
     */
    TaskReportDto getTaskReport(String robotTaskId);

}
