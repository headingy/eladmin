package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 巡检类型
 * @date 2019/5/3 14:32
 * @author liufengshuang
 */
public enum TaskType {

    //全部
    TASK_TYPE_QB(-1),
    //常规巡检
    TASK_TYPE_CGXJ(0),
    //例行巡检
    TASK_TYPE_LXXJ(1),
    //特行巡检
    TASK_TYPE_TXXJ(2),
    //专项巡检
    TASK_TYPE_ZXXJ(3);


    // 成员变量
    private int code;

    // 构造方法
    TaskType(int code){
        this.code = code;
    }

    /**
     * 获取所有巡检类型
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllTaskTypes(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<TaskType> lights = EnumSet.allOf(TaskType.class);
        for (TaskType status : lights) {
            JSONObject obj3 = new JSONObject();
            if(status.code==-1) {
                obj3.put("key",-1);
                obj3.put("value", "全部");
            }else if(status.code==0 ){
                obj3.put("key",0);
                obj3.put("value", "常规巡检");
            }else if(status.code ==1) {
                obj3.put("key",1);
                obj3.put("value", "例行巡检");
            }else if(status.code==2){
                obj3.put("key",2);
                obj3.put("value", "特巡巡检");
            }else if(status.code==3) {
                obj3.put("key",3);
                obj3.put("value", "专项巡检");
            }
            allStatus.add(obj3);
        }
        return allStatus;
    }
}
