package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 告警级别
 * @date 2019/5/3 14:09
 * @author liufengshuang
 */
public enum WarnLevel {


    //全部
    WARN_LEVEL_QB(-1),
    //严重
    WARN_LEVEL_YZ(1),
    //紧急
    WARN_LEVEL_JJ(2),
    //一般
    WARN_LEVEL_YB(0);


    // 成员变量
    private int code;

    // 构造方法
    WarnLevel(int code){
        this.code = code;
    }

    /**
     * 获取告警急别
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllWarnLevels(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<WarnLevel> lights = EnumSet.allOf(WarnLevel.class);
        for (WarnLevel status : lights) {
            JSONObject obj3 = new JSONObject();
            if(status.code==-1) {
                obj3.put("key",-1);
                obj3.put("value", "全部");
            }else if(status.code ==1) {
                obj3.put("key",1);
                obj3.put("value", "严重");
            }else if(status.code==2){
                obj3.put("key",2);
                obj3.put("value", "紧急");
            }else if(status.code==0) {
                obj3.put("key",0);
                obj3.put("value", "一般");
            }
            allStatus.add(obj3);
        }
        return allStatus;
    }
}
