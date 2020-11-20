package me.zhengjie.modules.mskj.websocket.common.constant;
/**
 * 油井设备类型
 * @date 2019/5/3 11:53
 * @author liufengshuang
 */
public enum DeviceType_YJ{
    //   磕头机
    YJ_DEVICE_KTJ("YJ1000"),
    //油封
    YJ_DEVICE_YF("YJ1001"),
    //压力表
    YJ_DEVICE_YLB("YJ1002"),
    //充电桩
    YJ_DEVICE_CDZ("DV1030");


    // 成员变量
    private String code;

    // 构造方法
    DeviceType_YJ(String code){
        this.code = code;
    }
    public String getValue() {
        return code;
    }

}


