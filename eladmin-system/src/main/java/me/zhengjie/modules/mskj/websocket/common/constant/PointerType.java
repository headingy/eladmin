package me.zhengjie.modules.mskj.websocket.common.constant;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 表计类型
 * @date 2019/5/3 14:43
 * @author liufengshuang
 */
public enum PointerType {

    //指针
    POINTER_TYPE_ZZ(0),
    //LED数字
    POINTER_TYPE_LEDSZ(1),
    //一态挡位
    POINTER_TYPE_YTDW(2),
    //二态挡位
    POINTER_TYPE_ETDW(3),
    //三态挡位
    POINTER_TYPE_STDW(4),
    //指示针
    POINTER_TYPE_ZSZ(5),
    //先后顺序
    ROAD_XHSX(1);


    // 成员变量
    private int code;

    // 构造方法
    PointerType(int code){
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
        EnumSet<PointerType> lights = EnumSet.allOf(PointerType.class);
        for (PointerType status : lights) {
            JSONObject obj = new JSONObject();
            if(status.code==0) {
                obj.put("key",0);
                obj.put("value", "指针");
            }else if(status.code==1) {
                obj.put("key",1);
                obj.put("value", "LED数字");
            }else if(status.code ==2) {
                obj.put("key",2);
                obj.put("value", "一态档位");
            }else if(status.code==3) {
                obj.put("key",3);
                obj.put("value", "两态档位");
            }else if(status.code==4) {
                obj.put("key",4);
                obj.put("value", "三态档位");
            }else if(status.code==5) {
                obj.put("key",5);
                obj.put("value", "指示针");
            }else if(status.code==10001000) {
                obj.put("key",10001000);
                obj.put("value", "圆形单指针");
            }else if(status.code==10001001) {
                obj.put("key",10001001);
                obj.put("value", "圆形单指针(带限条)");
            }else if(status.code==10002001) {
                obj.put("key",10002001);
                obj.put("value", "圆形双指针(一红一黑)");
            }else if(status.code==10002002) {
                obj.put("key",10002002);
                obj.put("value", "圆形双指针(双黑)");
            }else if(status.code==10101001) {
                obj.put("key",10101001);
                obj.put("value", "圆形扇形单指针");
            }else if(status.code==10101002) {
                obj.put("key",10101002);
                obj.put("value", "方形扇形单指针");
            }else if(status.code==10201000) {
                obj.put("key",10201000);
                obj.put("value", "方形单指针");
            }else if(status.code==10202000) {
                obj.put("key",10202000);
                obj.put("value", "方形双指针");
            }else if(status.code==20001000) {
                obj.put("key",20001000);
                obj.put("value", "圆形液晶(黑色数字)");
            }else if(status.code==20002000) {
                obj.put("key",20002000);
                obj.put("value", "圆形液晶(红色数字)");
            }else if(status.code==20201000) {
                obj.put("key",20201000);
                obj.put("value", "方形液晶(黑色数字)");
            }else if(status.code==20202000) {
                obj.put("key",20202000);
                obj.put("value", "方形液晶(红色数字)");
            }
            allStatus.add(obj);
        }
        return allStatus;
    }
}
