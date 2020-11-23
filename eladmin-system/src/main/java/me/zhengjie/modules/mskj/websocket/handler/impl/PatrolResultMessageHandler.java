package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.common.WebToolUtils;
import me.zhengjie.modules.mskj.common.util.DateTimeUtils;
import me.zhengjie.modules.mskj.common.util.UUIDUtils;
import me.zhengjie.modules.mskj.domain.*;
import me.zhengjie.modules.mskj.service.MediaService;
import me.zhengjie.modules.mskj.service.RobotTaskDeviceService;
import me.zhengjie.modules.mskj.service.WarnInfoService;
import me.zhengjie.modules.mskj.service.dto.RobotTaskDeviceDto;
import me.zhengjie.modules.mskj.service.dto.RobotTaskDeviceQueryCriteria;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class PatrolResultMessageHandler implements MessageHandler {
    final Cache cache;
    final MediaService mediaService;
    final WarnInfoService warnInfoService;
    final RobotTaskDeviceService robotTaskDeviceService;

    @Value("${mskj.patrolResult.video_folder}")
    String videoFolder;
    @Value("${mskj.patrolResult.image_folder}")
    String imageFolder;
    @Value("${mskj.patrolResult.voice_folder}")
    String voiceFolder;
    @Value("${mskj.patrolResult.map_folder}")
    String mapFolder;

    @Override
    public Object handle(String robotId, String message) throws IOException {
        JSONObject json = JSONObject.fromObject(message);
        String zRobotId = json.getString("robot_id");
        String fileName = json.getString("file_name");
        String deviceId = json.getString("device_id");
        String taskId = json.getString("task_id");
        String fileType = json.getString("file_type");
        String robotIp = json.getString("robot_ip");
        String zFileType = fileType;
        String realPath = WebToolUtils.getLocal();
        String rootPath = "";
        //文件后缀
        String str = fileName.substring(fileName.lastIndexOf(".") + 1);
        int num = str.length();//得到后缀名长度
        // 得到去掉了后缀的文件名。
        String fileOtherName = fileName.substring(0, fileName.length() - num - 1);
        String zMapId = fileOtherName;
        // 新文件名
        String newFileName = UUIDUtils.generateUUID();

        //保存的路径
        String path = "/" + DateTimeUtils.getTodayDateFormat1();
        Media mediaEntity = new Media();
        switch (fileType) {
            case "patrol_video":
                rootPath = videoFolder;
                mediaEntity.setMediaType("1");
                break;
            case "patrol_audio":
                rootPath = voiceFolder;
                mediaEntity.setMediaType("3");
                break;
            case "patrol_pictures":
                rootPath = imageFolder;
                mediaEntity.setMediaType("0");
                mediaEntity.setType(0);
                break;
            case "patrol_map":
                rootPath = mapFolder;
                break;
        }
        if (fileType.equals("patrol_map")) {
            realPath = realPath + rootPath;
        } else {
            realPath = realPath + rootPath + path;
        }

        if (!fileType.equals("patrol_map")) {
            //@TODO 这里如果是Windows的话可以写C盘，如果是Linux系统需要写Linux的目录
            //数据库保存文件信息
            String mediaId = UUIDUtils.generateUUID();
            mediaEntity.setMediaId(mediaId);
            mediaEntity.setMediaName(newFileName);
            mediaEntity.setMediaRealName(fileOtherName);
            mediaEntity.setMediaExtensionName(str);
            mediaEntity.setPath("/Tools" + rootPath + path + "/" + fileName);
            mediaEntity.setCreateTime(DateTimeUtils.getTodayDateTime());
//                mediaEntityList.add(mediaEntity);
            mediaService.create(mediaEntity);
            //数据库保存告警信息中的文件id
            WarnInfo warnInfoEntity = new WarnInfo();
            //根据任务id和设备id查询告警信息
            warnInfoEntity.setDeviceId(deviceId);
            warnInfoEntity.setRobotTaskId(taskId);
            switch (mediaEntity.getMediaType()) {
                case "0":
                    warnInfoEntity.setPictureId(mediaEntity.getMediaId());
                    break;
                case "1":
                    warnInfoEntity.setVideoId(mediaEntity.getMediaId());
                    break;
                case "3":
                    warnInfoEntity.setVoiceId(mediaEntity.getMediaId());
                    break;
            }
            warnInfoService.update(warnInfoEntity);

            //根据机器人传来的文件id去机器人任务设备表中查询消息
            RobotTask robotTask = new RobotTask();
            robotTask.setRobotTaskId(taskId);//机器人任务ID
            RobotTaskDeviceQueryCriteria criteria = new RobotTaskDeviceQueryCriteria();
            criteria.setRobotTasks(new HashSet<>(Arrays.asList(taskId)));
            List query1 = robotTaskDeviceService.queryAll(criteria);
            if (query1 != null && query1.size() > 0) {
                RobotTaskDeviceDto robotTaskDeviceDto = (RobotTaskDeviceDto) query1.get(0);
                RobotTaskDevice robotTaskDeviceEntity = new RobotTaskDevice();
                if (robotTaskDeviceDto.getMediaIds() == null || robotTaskDeviceDto.getMediaIds() == "") {
                    robotTaskDeviceDto.setMediaIds(mediaEntity.getMediaId());
                } else {
                    String myValue = robotTaskDeviceDto.getMediaIds() + "," + mediaEntity.getMediaId();
                    robotTaskDeviceDto.setMediaIds(myValue);
                }
                robotTaskDeviceEntity.setRobotTaskDeviceId(robotTaskDeviceDto.getRobotTaskDeviceId());
                robotTaskDeviceEntity.setMediaIds(robotTaskDeviceDto.getMediaIds());
                robotTaskDeviceService.update(robotTaskDeviceEntity);
            }
        }
        String zFilePath = realPath;
        String zFileName = fileName;
        //TODO replace zmq
//        new Test().start();
        return null;
    }
}
