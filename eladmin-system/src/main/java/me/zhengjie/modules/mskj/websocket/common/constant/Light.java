package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 视频通道
 * @date 2019/5/3 13:54
 * @author liufengshuang
 */
public enum Light {


    //红热外
    LIGTH_HRW(0),
    //可见光
    LIGHT_KJG(1);


    // 成员变量
    private int code;

    // 构造方法
    Light(int code){
        this.code = code;
    }

    /**
     * 视频通道
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllLights(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<Light> lights = EnumSet.allOf(Light.class);
        for (Light status : lights) {
            JSONObject obj2 = new JSONObject();
            if(status.code==0) {
                obj2.put("key",0);
                obj2.put("value", "可见光");
            }else if(status.code ==1) {
                obj2.put("key",1);
                obj2.put("value", "热红外");
            }
            allStatus.add(obj2);
        }
        return allStatus;
    }

}
