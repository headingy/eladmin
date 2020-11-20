package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 告警急别
 * @date 2019/5/3 14:17
 * @author liufengshuang
 */
public enum WarnInfoType {

    //控制消息
    WARN_INFO_TYPE_KZXX(1),
    //全部
    WARN_INFO_TYPE_QB(-1),
    //告警消息
    WARN_INFO_TYPE_GJXX(2),
    //巡检消息
    WARN_INFO_TYPE_XJXX(3);

    // 成员变量
    private int code;

    // 构造方法
    WarnInfoType(int code){
        this.code = code;
    }

    /**
     * 获得所有消息类型 1.控制 2.告警。 3巡检
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllWarnInfoTypes(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<WarnInfoType> lights = EnumSet.allOf(WarnInfoType.class);
        for (WarnInfoType status : lights) {
            JSONObject obj4 = new JSONObject();
            if(status.code==-1) {
                obj4.put("key",-1);
                obj4.put("value", "全部");
            }else if(status.code ==1) {
                obj4.put("key",1);
                obj4.put("value", "控制消息");
            }else if(status.code==2){
                obj4.put("key",2);
                obj4.put("value", "告警消息");
            }else if(status.code==3) {
                obj4.put("key",3);
                obj4.put("value", "巡检消息");
            }
            allStatus.add(obj4);
        }
        return allStatus;
    }

}
