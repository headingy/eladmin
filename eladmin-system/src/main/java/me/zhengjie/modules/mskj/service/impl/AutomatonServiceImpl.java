package me.zhengjie.modules.mskj.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.common.CodeConst.WebResult;
import me.zhengjie.modules.mskj.service.AutomatonService;
import me.zhengjie.modules.mskj.service.RobotService;
import me.zhengjie.modules.mskj.service.dto.RobotDto;
import me.zhengjie.utils.RedisOldUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Fu Ding
 * @website https://el-admin.vip
 * @description 服务实现
 * @date 2020-10-28
 **/
@Service
@RequiredArgsConstructor
public class AutomatonServiceImpl implements AutomatonService {

    private final RedisOldUtils redisOldUtils;
    private final RobotService robotService;
    private static final Logger log = LoggerFactory.getLogger(AutomatonServiceImpl.class);

    /**
     * 查询机器人首页所需信息
     *
     * @param robotId 机器人ID
     * @return String
     */
    @Override
    public JSONObject getIndexInfo(String robotId) {
        JSONObject jsonObject = new JSONObject();
        try {
            Object indexMessage = redisOldUtils.get("DeviceStatus");
            jsonObject.put("lists", indexMessage == null ? "" : indexMessage.toString());
            Object location = redisOldUtils.get("secondLocation");
            jsonObject.put("location", location == null ? "" : location.toString());
            Object statusMessage = redisOldUtils.get("statusMessage");
            jsonObject.put("alertMessage", statusMessage == null ? "" : statusMessage.toString());
            RobotDto robot = robotService.findById(robotId);
            jsonObject.put("mapName", robot.getMap().getMapName());
            jsonObject.put("mapId", robot.getMap().getMapId());
        } catch (Exception e) {
            log.error("getIndexInfo", e);
        }
        jsonObject.put("err_code", WebResult.SUCCESS);
        jsonObject.put("errMsg", "success");
        return jsonObject;
    }

    /**
     * 查询机器人状态信息
     *
     * @param robotId 机器人ID
     * @return String
     */
    @Override
    public JSONObject getStatusInfo(String robotId) {
        JSONObject jsonObject = new JSONObject();
        try {
            Object statusMessage = redisOldUtils.get("statusMessage");
            jsonObject.put("statusMessage", statusMessage == null ? "" : statusMessage.toString());
        } catch (Exception e) {
            log.error("getStatusInfo", e);
        }
        jsonObject.put("err_code", WebResult.SUCCESS);
        jsonObject.put("errMsg", "success");
        return jsonObject;
    }

    /**
     * 查询机器人任务信息
     *
     * @param robotId 机器人ID
     * @return String
     */
    @Override
    public JSONObject getTaskInfo(String robotId) {
        JSONObject jsonObject = new JSONObject();
        try {
            Object taskMessage = redisOldUtils.get("taskMessage");
            if (taskMessage != null) {
                jsonObject.put("err_code", WebResult.SUCCESS);
                jsonObject.put("errMsg", "success");
                jsonObject.put("lists", taskMessage.toString());
            } else {
                jsonObject.put("err_code", WebResult.FAIL);
                jsonObject.put("errMsg", "查询失败");
            }
        } catch (Exception e) {
            log.error("getTaskInfo", e);
        }
        return jsonObject;
    }
}
