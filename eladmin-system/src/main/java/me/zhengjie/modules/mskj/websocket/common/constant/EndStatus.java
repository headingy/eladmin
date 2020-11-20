package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 巡检结束状态
 * @date 2019/5/3 12:58
 * @author liufengshuang
 */
public enum EndStatus {

    //返回充电
    END_STATUS_FHCD(0),
    //原地待命
    END_STATUS_YDDM(1),
    //返回原点
    END_STATUS_FHYD(2);


    // 成员变量
    private int code;

    // 构造方法
    EndStatus(int code){
        this.code = code;
    }

    /**
     * 获取所有的结束状态
     * @date 2019/5/3 12:47
     * @return java.util.List<net.sf.json.JSONObject>
     * @author liufengshuang
     */
    public static List<JSONObject> getAllEndStatus(){
        List<JSONObject> allStatus = new ArrayList<>();
        EnumSet<EndStatus> execStatus = EnumSet.allOf(EndStatus.class);
        for (EndStatus status : execStatus) {
            JSONObject obj2 = new JSONObject();
            if(status.code==0) {
                obj2.put("key",0);
                obj2.put("value", "返回充电");
            }else if(status.code ==1) {
                obj2.put("key",1);
                obj2.put("value", "原地待命");
            }else if(status.code ==2) {
                obj2.put("key",2);
                obj2.put("value", "返回原点");
            }
            allStatus.add(obj2);
        }
        return allStatus;
    }

}
