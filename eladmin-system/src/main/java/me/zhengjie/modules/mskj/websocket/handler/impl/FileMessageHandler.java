package me.zhengjie.modules.mskj.websocket.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.mskj.websocket.common.Cache;
import me.zhengjie.modules.mskj.websocket.handler.MessageHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class FileMessageHandler implements MessageHandler {
    final Cache cache;

    @Override
    public Object handle(String robotId, String message) throws IOException{

        JSONObject json = JSONObject.fromObject(message);  /* message 可能不为json, 需要try */
        String fileId = json.getString("file_id");//文件id
        String fileName = json.getString("file_name");//文件名称
        String isEnd = json.getString("is_end");//是否传输结束
        String content = json.getString("content");//文件的16进制内容
        String deviceId = json.getString("device_id");//设备id
        String taskId = json.getString("task_id");//任务id
        String fileType = json.getString("file_type");//文件类型


        if (!isEnd.equals("true")) { // 如果传输还没结束，就把收到的分段内容添加至缓存
            //TODO file cache
//            String fileContent = valueOperations.get(fileId); // 缓存中获取到的文件内容
//            if (fileContent == null || fileContent.equals("")) {
//                valueOperations.set(fileId, content, 5, TimeUnit.MINUTES);
//            } else {
//                valueOperations.set(fileId, fileContent + content, 5, TimeUnit.MINUTES); // 把分段的文件放入缓存
//            }
        } else { // 如果传输已经结束，就把缓存中的文件内容保存文件
//            String file = valueOperations.get(fileId);// 获取缓存中的数据
//            if (file == null) {
//                file = content;
//            } else {
//                file = file + content;
//            }
//            //文件后缀
//            String str = fileName.substring(fileName.lastIndexOf(".") + 1);
//            int num = str.length();//得到后缀名长度
//            // 得到去掉了后缀的文件名。
//            String fileOtherName = fileName.substring(0, fileName.length() - num - 1);
//            // 新文件名
//            String newFileName = UUIDUtils.generateUUID();
//
//            byte[] bytes = hexStringToBytes(file);
//            String rootPath = "";
//
//            //保存的路径
//            String path = "/" + DateTimeUtils.getTodayDateFormat1();
//            MediaEntity mediaEntity = new MediaEntity();
//            if (fileType.equals("video")) {
//                // 设定视频文件保存的目录
//                rootPath = videoUtils.readValue("video_folder");
//                mediaEntity.setMediaType(1);
//                mediaEntity.setType(2);
//            } else if (fileType.equals("voice")) {
//                // 设定音频文件保存的目录
//                rootPath = videoUtils.readValue("voice_folder");
//                mediaEntity.setMediaType(3);
//                mediaEntity.setType(3);
//            } else if (fileType.equals("image")) {
//                // 设定图片文件保存的目录
//                rootPath = videoUtils.readValue("image_folder");
//                mediaEntity.setMediaType(0);
//                mediaEntity.setType(0);
//            }
//            //begin 刘文超 2018-11-10日新增
//            else if (fileType.equals("imageThermal")) {
//                // 设定图片文件保存的目录
//                rootPath = videoUtils.readValue("image_folder");
//                mediaEntity.setMediaType(0);
//                mediaEntity.setType(1);//热红外图片
//            }
//            //end
//            String realPath = WebToolUtils.getLocal() + rootPath;
//
//            //写文件
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                File folder = new File(realPath + path);
//                //文件夹路径不存在
//                if (!folder.exists() && !folder.isDirectory()) {
//                    System.out.println("文件夹路径不存在，创建路径:" + realPath + path);
//                    folder.mkdirs();
//                } else {
//                    System.out.println("文件夹路径存在:" + realPath + path);
//                }
//                byteArrayOutputStream.write(bytes);
//                FileOutputStream fileOutputStream = new FileOutputStream(new File(realPath + path + "/" + fileName));
//                byteArrayOutputStream.writeTo(fileOutputStream);
//                fileOutputStream.flush();
//
//                RobotTaskDeviceEntity rtde = robotTaskDeviceService.query(deviceId, taskId);
//                //数据库保存文件信息
//                //文件信息
//                String mediaId = UUIDUtils.generateUUID();
//                mediaEntity.setId(mediaId);
//                mediaEntity.setMediaName(newFileName);
//                mediaEntity.setMediaRealName(fileOtherName);
//                mediaEntity.setMediaExtensionName(str);
//                mediaEntity.setPath("/Tools" + rootPath + path + "/" + fileName);
//                mediaEntity.setCreateTime(DateTimeUtils.getTodayDateTime());
//                if (rtde != null) mediaEntity.setRobotTaskDeviceId(rtde.getRobotTaskDeviceId());
////                mediaEntityList.add(mediaEntity);
//                mediaService.create(mediaEntity);
//                //数据库保存告警信息中的文件id
//                WarnInfoEntity warnInfoEntity = new WarnInfoEntity();
//                //根据任务id和设备id查询告警信息
//                warnInfoEntity.setDeviceId(deviceId);
//                warnInfoEntity.setRobotTaskId(taskId);
//                List query = warnInfoDAO.query(warnInfoEntity);
//                if (query != null && query.size() > 0) {
//                    warnInfoEntity = (WarnInfoEntity) query.get(0);
//                    if (mediaEntity.getMediaType() == 0) {
//                        warnInfoEntity.setPictureId(mediaEntity.getId());
//                    } else if (mediaEntity.getMediaType() == 1) {
//                        warnInfoEntity.setVideoId(mediaEntity.getId());
//                    } else if (mediaEntity.getMediaType() == 3) {
//                        warnInfoEntity.setVoiceId(mediaEntity.getId());
//                    }
//                    warnInfoDAO.update(warnInfoEntity);
//                }
//
//                //根据机器人传来的文件id去机器人任务设备表中查询消息
//                if (rtde != null) {
//                    //刘文超 2018-11-12
//                    if (rtde.getMediaIds() == null || rtde.getMediaIds().equals("")) {
//                        rtde.setMediaIds(mediaEntity.getId());
//                    } else {
//                        String myValue = rtde.getMediaIds() + "," + mediaEntity.getId();
//                        rtde.setMediaIds(myValue);
//                    }
//                    robotTaskDeviceService.update(rtde);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    byteArrayOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return null;
    }
}
