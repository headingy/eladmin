package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *  任务状态
 * @date 2019/5/3 11:52
 * @author liufengshuang
 */
public enum TaskStatus {
    //全部
    TASK_STATUS_QB(-1),
    //待执行
    TASK_STATUS_DZX(0),
    //暂停
    TASK_STATUS_ZT(1),
    //已终止
    TASK_STATUS_YZZ(2),
    //已完成
    TASK_STATUS_YWC(3),
    //正在执行
    TASK_STATUS_ZZZX(4);


    // 成员变量
    private int code;

    // 构造方法
    TaskStatus(int code){
        this.code = code;
    }

    /**
     * 获取所有的任务状态
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllTaskStatus(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<TaskStatus> taskStatus = EnumSet.allOf(TaskStatus.class);
        for (TaskStatus status : taskStatus) {
            JSONObject obj2 = new JSONObject();
            if(status.code==-1) {
                obj2.put("key",-1);
                obj2.put("value", "全部");
            }else if(status.code==0) {
                obj2.put("key",0);
                obj2.put("value", "待执行");
            }else if(status.code==1) {
                obj2.put("key",1);
                obj2.put("value", "暂停");
            }else if(status.code==2) {
                obj2.put("key",2);
                obj2.put("value", "已终止");
            }else if(status.code ==3) {
                obj2.put("key",3);
                obj2.put("value", "已完成");
            }else if(status.code==4) {
                obj2.put("key",4);
                obj2.put("value", "正在执行");
            }
            allStatus.add(obj2);
        }
        return allStatus;
    }

}
