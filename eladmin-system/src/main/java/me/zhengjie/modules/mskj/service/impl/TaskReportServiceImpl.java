package me.zhengjie.modules.mskj.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.common.DeviceInspectType;
import me.zhengjie.modules.mskj.service.*;
import me.zhengjie.modules.mskj.service.dto.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @description 服务实现
 * @date 2020-10-30
 **/
@Service
@RequiredArgsConstructor
public class TaskReportServiceImpl implements TaskReportService {

    private final RobotTaskDeviceService robotTaskDeviceService;
    private final RobotTaskService robotTaskService;
    private final TaskService taskService;
    private final DeviceService deviceService;
    private final EnvironmentService environmentService;
    private final MediaService mediaService;

    /**
     * 查询任务报告
     *
     * @param robotTaskId 机器人ID
     * @return TaskReportDto
     */
    @Override
    public TaskReportDto getTaskReport(String robotTaskId) {
        TaskReportDto report = new TaskReportDto();
        RobotTaskDto robotTask = robotTaskService.findById(robotTaskId);
//        TaskDto task = taskService.findById(robotTask.getTask().getTaskId());
        report.setTaskId(robotTask.getTask().getTaskId())
                .setName(robotTask.getTask().getName())
                .setCreator(robotTask.getTask().getCreater())
                .setExecTime(robotTask.getExecTime())
                .setOverTime(robotTask.getEndTime())
                .setRobotName(robotTask.getRobot().getName());

        int numDevices = 0;
        int numAbnormals = 0;
        //告警设备
        List<String> warnDevices = new ArrayList<>();
        List<DevicePic> pics = new ArrayList<>();
        List<DevicePic> infraReds = new ArrayList<>();
        List<DevicePic> needles = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00");
        List<RobotTaskDeviceDto> deviceDtos = robotTaskDeviceService.queryAll(
                new RobotTaskDeviceQueryCriteria().robotTaskId(robotTaskId));
        for (RobotTaskDeviceDto deviceDto : deviceDtos) {
            DeviceDto device = deviceService.findById(deviceDto.getDeviceId());
            if (device == null) continue;
            if (report.getInspectType() == null) {
                report.setInspectType(DeviceInspectType.values()[device.getInspectType()].getName());
            }
            if (device.getThreePhase() == 0) {
                numDevices += 3;
            } else if (device.getThreePhase() == 1) {
                numDevices += 1;
            }
            String value = String.valueOf(deviceDto.getResult()).trim();
            for (int j = 0; j < value.length(); j++) {
                if (value.charAt(j) == '1') {
                    numAbnormals += 1;
                }
            }
            if (value.equals("1")) {
                warnDevices.add(device.getDeviceName());
            }
            DevicePic pic = new DevicePic();
            pic.setDeviceId(device.getDeviceId());
            pic.setDeviceName(device.getDeviceName());
            String[] mids = deviceDto.getMediaIds().split(",");
            for (String mid : mids) {
                MediaDto media = mediaService.findById(mid);
                if (media == null) continue;
                if (media.getType().equals(0) && media.getMediaType().equals("0")) {
                    pic.setLightPic(media.getPath());
                    if (device.getInspectType() == DeviceInspectType.LIGHT_INFRARED.ordinal()
                            && media.getType().equals(1) && media.getMediaType().equals("0")) {
                        pic.setInfraredPic(media.getPath());
                    }
                }
            }
            pics.add(pic);
            if (device.getInspectType() == DeviceInspectType.LIGHT_INFRARED.ordinal()) {
                //热红外数据
                DevicePic infra = new DevicePic();
                infra.setDeviceName(device.getDeviceName());
                infra.setMaxTemp(deviceDto.getMaxTemp());
                Float max = Float.parseFloat(deviceDto.getMaxTemp());
                Float temp = Float.parseFloat(deviceDto.getTemperature());
                infra.setTempUp(df.format(max - temp));
                if (deviceDto.getResult().equals("0")) {
                    infra.setResult("正常");
                } else if (deviceDto.getResult().equals("1")) {
                    infra.setResult("异常");
                }
                infraReds.add(infra);
            } else if (device.getInspectType() == DeviceInspectType.POSITION_STATUS.ordinal()
                    || device.getInspectType() == DeviceInspectType.NEEDLE.ordinal()) {
                //仪表数据
                DevicePic needle = new DevicePic();
                needle.setDeviceName(device.getDeviceName());
                needle.setValue(deviceDto.getValue());
                if (deviceDto.getResult().equals("0")) {
                    needle.setResult("正常");
                } else if (deviceDto.getResult().equals("1")) {
                    needle.setResult("异常");
                }
                needles.add(needle);
            }
        }
        if (numDevices <= 0) return report;
        //设备数
        report.setNumDevices(numDevices);
        //异常设备数
        report.setAbnormals(numAbnormals);

        if (warnDevices.size() > 0) report.setWarnDevices(warnDevices);
        if (pics.size() > 0) report.setDevicePics(pics);
        if (infraReds.size() > 0) report.setInfraReds(infraReds);
        if (needles.size() > 0) report.setNeedles(needles);

        //异常率
        if (numAbnormals > 0) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(0);
            String rate = numberFormat.format((float) (report.getAbnormals()) / (float) report.getNumDevices() * 100);
            report.setAbnormalRate(rate + "%");
        }

        //环境数据
        List<EnvironmentDto> envs = environmentService.queryAll(new EnvironmentQueryCriteria().robotTaskId(robotTaskId));
        if (envs != null && envs.size() > 0) {
            //氧气
            float oxygen = 0;
            //可燃气体
            float combustibleGas = 0;
            //硫化氢
            float hydrothion = 0;
            //一氧化碳
            float carbonicOxide = 0;
            //氨气
            float ammoniaGas = 0;
            for (EnvironmentDto env : envs) {
                oxygen += Float.parseFloat(env.getOxygen());
                combustibleGas += Float.parseFloat(env.getCombustibleGas());
                hydrothion += Float.parseFloat(env.getHydrothion());
                carbonicOxide += Float.parseFloat(env.getCarbonicOxide());
                ammoniaGas += Float.parseFloat(env.getAmmoniaGas());
            }
            report.setOxygen(df.format(oxygen / envs.size()));
            report.setCombustibleGas(df.format(combustibleGas / envs.size()));
            report.setHydrothion(df.format(hydrothion / envs.size()));
            report.setCarbonicOxide(df.format(carbonicOxide / envs.size()));
            report.setAmmoniaGas(df.format(ammoniaGas / envs.size()));
        }
        return report;
    }
}
