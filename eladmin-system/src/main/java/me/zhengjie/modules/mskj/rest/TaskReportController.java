package me.zhengjie.modules.mskj.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.service.TaskReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @date 2020-10-30
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "任务执行报告")
@RequestMapping("/api/task/report")
public class TaskReportController {

    private final TaskReportService taskReportService;

    @GetMapping()
    @ApiOperation("查询任务报告")
    @PreAuthorize("@el.check('robot:list')")
    public ResponseEntity<Object> getTaskReport(@RequestParam String robotTaskId) {
        return new ResponseEntity<>(taskReportService.getTaskReport(robotTaskId), HttpStatus.OK);
    }
}
