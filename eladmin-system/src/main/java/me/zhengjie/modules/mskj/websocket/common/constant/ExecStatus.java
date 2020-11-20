package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum ExecStatus {

    //待执行
    EXEC_STATUS_DZX(0),
    //正在执行
    EXEC_STATUS_ZZZX(1),
    //已完成
    EXEC_STATUS_YWC(2),
    //已执行
    EXEC_STATUS_YZZ(3),
    //不执行
    EXEC_STATUS_BZX(4),
    //暂停
    EXEC_STATUS_ZT(5);

    // 成员变量
    private int code;

    // 构造方法
    ExecStatus(int code){
        this.code = code;
    }

    /**
     * 获取所有的执行状态
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllExecStatus(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<ExecStatus> execStatus = EnumSet.allOf(ExecStatus.class);
        for (ExecStatus status : execStatus) {
            JSONObject obj2 = new JSONObject();
            if(status.code==0) {
                obj2.put("key",0);
                obj2.put("value", "待执行");
            }else if(status.code==1) {
                obj2.put("key",1);
                obj2.put("value", "正在执行");
            }else if(status.code ==2) {
                obj2.put("key",2);
                obj2.put("value", "已完成");
            }else if(status.code ==3) {
                obj2.put("key",3);
                obj2.put("value", "已终止");
            }else if(status.code==4) {
                obj2.put("key",4);
                obj2.put("value", "不执行");
            }else if(status.code==5) {
                obj2.put("key",5);
                obj2.put("value", "暂停");
            }
            allStatus.add(obj2);
        }
        return allStatus;
    }

}
