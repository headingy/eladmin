package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.common.util.DateTimeUtils;
import me.zhengjie.modules.mskj.common.util.UUIDUtils;
import me.zhengjie.modules.mskj.domain.Device;
import me.zhengjie.modules.mskj.domain.RobotTask;
import me.zhengjie.modules.mskj.domain.RobotTaskDevice;
import me.zhengjie.modules.mskj.domain.WarnInfo;
import me.zhengjie.modules.mskj.service.DeviceService;
import me.zhengjie.modules.mskj.service.NeedlechartService;
import me.zhengjie.modules.mskj.service.PointerchartService;
import me.zhengjie.modules.mskj.service.RobotTaskDeviceService;
import me.zhengjie.modules.mskj.service.dto.DeviceDto;
import me.zhengjie.modules.mskj.service.dto.PointerchartDto;
import me.zhengjie.modules.mskj.websocket.common.Const;
import me.zhengjie.modules.mskj.websocket.common.DeviceUtils;
import me.zhengjie.modules.mskj.websocket.dto.RobotCommand;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class InspectionInfoMessageHandler implements MessageHandler {
    private final DeviceService deviceService;
    private final RobotTaskDeviceService robotTaskDeviceService;
    private final PointerchartService pointerchartService;
    private final NeedlechartService needlechartService;
    private final DeviceUtils deviceUtils;

    //    @Autowired
//    public void setDeviceService(DeviceService deviceService){
//        this.deviceService=deviceService;
//    }
    @Override
    public Object handle(String robotId, String msg) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        InspectionInfoMessage message = objectMapper.readValue(msg, InspectionInfoMessage.class);
        //TODO write to warninfo db
        JSONObject obj = JSONObject.fromObject(msg);
        WarnInfo reportInfo = new WarnInfo();
        //任务id 用来确定同一次巡检
        String robotTaskId = obj.getString("task_id");
        String isPatrolPoint = obj.getString("inspectType");
        if (isPatrolPoint.equals("5")) {
            RobotCommand robotCommand = new RobotCommand();
            robotCommand.setType(Const.CMDType.CMD_TASK_CONTINUE);
            JSONObject orderMessage = JSONObject.fromObject(robotCommand);
            return orderMessage.toString();
        }
        //设备id
        String deviceId = obj.getString("device_id");
        //告警值 小数点
        String thresholdValue = obj.getString("threshold_value");
        //设备信息
        DeviceDto device = deviceService.findById(deviceId);
        //识别类型
        Integer inspectTypeDevice = device.getInspectType();
        // 是否泄露{-1:不漏; 1:漏}
        String leakage = obj.getString("leakage");
        // 平均浓度（单位:ppm）
        String concentration = obj.getString("concentration");
        // 最大浓度（单位:ppm）
        String maxConcentration = obj.getString("maxConcentration");
        String resultValue = "正常";
        // 记录是否需要录制视频
        Boolean video = false;

        //表针类型
        String needleType = "";
        //识别类型
        String inspectType = "";
        List<JSONObject> inspectTypeLists = deviceUtils.getDevice().get("inspectType");
        List<JSONObject> neddleTypeLists = deviceUtils.getDevice().get("needleType");
        for (int i = 0; i < inspectTypeLists.size(); i++) {
            JSONObject jsonObj = JSONObject.fromObject(inspectTypeLists.get(i));
            String res = jsonObj.getString("value");
            String key = jsonObj.getString("key");
            if (key.equals(inspectTypeDevice)) {
                inspectType = res;
            }
        }
        for (int i = 0; i < neddleTypeLists.size(); i++) {
            JSONObject jsonObj = JSONObject.fromObject(neddleTypeLists.get(i));
            String res = jsonObj.getString("value");
            String key = jsonObj.getString("key");
            if (key.equals(device.getNeedleType())) {
                needleType = res;
            }
        }

        reportInfo.setVerifyType(inspectTypeDevice);
        RobotTaskDevice robotTaskDeviceEntity = new RobotTaskDevice();
        //判断巡检点识别类型是否为气体泄漏
        if (10 == inspectTypeDevice) {
            if ("-1".equals(leakage)) {
                resultValue = "正常";
                reportInfo.setResultStatus(0);
                robotTaskDeviceEntity.setResult("0");
            } else {
                resultValue = "异常";
                reportInfo.setResultStatus(1);
                robotTaskDeviceEntity.setResult("1");
            }
            float aa = Float.parseFloat(maxConcentration);
            BigDecimal b = new BigDecimal(aa);
            float f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

            reportInfo.setThresholdValue(f1 + "ppm");
            robotTaskDeviceEntity.setValue(f1 + "ppm");

            //设备A位置气体检测正常
            reportInfo.setInfoValue(device.getDeviceName() + inspectType + resultValue);
        } else {
            // 如果是液晶表则需要去数据库找到对应的标准信息
            String[] valueArray = thresholdValue.split(",");
            boolean singleOrMultiple = false;
            if (device.getMultichartAnnotation() == null || device.getMultichartAnnotation().equals("")) {
                singleOrMultiple = true;
            }
            //非液晶表
            if (singleOrMultiple) {
                System.out.println("非液晶表");
                //阈值max
                String max = device.getThresholdUp();
                //阈值min
                String min = device.getThresholdDown();
                if (min != null && !min.equals("") && Float.parseFloat(thresholdValue) < Float.parseFloat(min)) {
                    resultValue = "读数过低";
                    reportInfo.setResultStatus(1);
                    robotTaskDeviceEntity.setResult("1");
                    video = true;
                } else if (max != null && !max.equals("") && Float.parseFloat(thresholdValue) > Float.parseFloat(max)) {
                    resultValue = "读数过高";
                    reportInfo.setResultStatus(1);
                    video = true;
                    robotTaskDeviceEntity.setResult("1");
                } else if (Float.parseFloat(thresholdValue) == -300) { // -300识别失败
                    resultValue = "未读到指针信息";
                    reportInfo.setResultStatus(1);
                    video = true;
                } else { // -200不需要识别
                    resultValue = "读数正常";
                    reportInfo.setResultStatus(0);
                    robotTaskDeviceEntity.setResult("0");
                }
                float aa = Float.parseFloat(thresholdValue);
                BigDecimal b = new BigDecimal(aa);
                float f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

                reportInfo.setThresholdValue(f1 + "");
                robotTaskDeviceEntity.setValue(f1 + "");
            } else {
                //液晶表
                System.out.println("液晶表");
                String annotation = device.getMultichartAnnotation();

                String[] valueNum = annotation.split(";");
                StringBuilder sbResult = new StringBuilder();
                reportInfo.setResultStatus(0);
                robotTaskDeviceEntity.setResult("0");
                PointerchartDto charForName = pointerchartService.findById(device.getPointerType() + "");
                String[] nameTotoAl = charForName.getAnnotation().split(";");

                for (int i = 0; i < valueArray.length; i++) {
                    String[] value = valueNum[i + 1].split(",");
                    if (Float.parseFloat(valueArray[i]) == -200) {
                        continue;
                    } else if (Float.parseFloat(valueArray[i]) == -300) {
                        resultValue = "未读到指针信息";
                        reportInfo.setResultStatus(1);
                        video = true;
                    } else if (!value[5].equals("") && Float.parseFloat(value[5]) < Float.parseFloat(valueArray[i])) {
                        resultValue = "读数过高";
                        reportInfo.setResultStatus(1);
                        video = true;
                        robotTaskDeviceEntity.setResult("1");
                    } else if (!value[6].equals("") && Float.parseFloat(value[6]) > Float.parseFloat(valueArray[i])) {
                        resultValue = "读数过低";
                        reportInfo.setResultStatus(1);
                        video = true;
                        robotTaskDeviceEntity.setResult("1");
                    }
                    String[] name = nameTotoAl[i + 1].split(":");
                    BigDecimal b = new BigDecimal(Float.parseFloat(valueArray[i]));
                    Float result = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
                    sbResult.append(name[0] + ":" + result + " ");
                }
                reportInfo.setThresholdValue(sbResult.toString());
                robotTaskDeviceEntity.setValue(sbResult.toString());
            }
            //压力表A位置状态识别表计识别读数正常
            reportInfo.setInfoValue(device.getDeviceName() + needleType + inspectType + resultValue);
        }
        // 命令机器人继续执行任务,如果需要拍摄视频则发送拍摄指令
        RobotCommand robotCommand = new RobotCommand();
        if (video) {
            robotCommand.setType(Const.CMDType.CMD_TASK_CONTINUE_RECORD);
        } else {
            robotCommand.setType(Const.CMDType.CMD_TASK_CONTINUE);
        }
        JSONObject fromObject = JSONObject.fromObject(robotCommand);
        String orderMessage = fromObject.toString();

        reportInfo.setInfoKey("");
        //reportInfo.setLevel(warnLevel);
        reportInfo.setLevel(0);
        reportInfo.setDeviceId(deviceId);
        reportInfo.setStatus(0);
        //机器人任务设备表
        //最高温度
        String highTemperature = obj.getString("highTemperature");
        //环境温度
        String enviTemperature = obj.getString("enviTemperature");
        robotTaskDeviceEntity.setMaxTemp(highTemperature);
        robotTaskDeviceEntity.setTemperature(enviTemperature);
        robotTaskDeviceEntity.setRobotTaskDeviceId(UUIDUtils.generateUUID());
        robotTaskDeviceEntity.setCreateTime(DateTimeUtils.getTodayDateTime());
        Device d = new Device();
        d.setDeviceId(deviceId);
        robotTaskDeviceEntity.setDevice(d);
        robotTaskDeviceEntity.setRobotId(robotId);
        RobotTask rt = new RobotTask();
        rt.setRobotTaskId(robotTaskId);
        robotTaskDeviceEntity.setRobotTask(rt);
        robotTaskDeviceService.create(robotTaskDeviceEntity);
        //TODO trigger outgoing RESTful call
        return orderMessage;
    }
}
