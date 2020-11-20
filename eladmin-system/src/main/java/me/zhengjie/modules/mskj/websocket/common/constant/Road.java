package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 巡检路径规划
 * @date 2019/5/3 14:05
 * @author liufengshuang
 */
public enum Road {

    //最优路径
    ROAD_ZYLJ(0),
    //先后顺序
    ROAD_XHSX(1);


    // 成员变量
    private int code;

    // 构造方法
    Road(int code){
        this.code = code;
    }

    /**
     * 巡检路径规划
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllRoads(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<Road> lights = EnumSet.allOf(Road.class);
        for (Road status : lights) {
            JSONObject obj2 = new JSONObject();
            if(status.code==0) {
                obj2.put("key",0);
                obj2.put("value", "最优路径");
            }else if(status.code==1) {
                obj2.put("key",1);
                obj2.put("value", "先后顺序");
            }
            allStatus.add(obj2);
        }
        return allStatus;
    }
}
