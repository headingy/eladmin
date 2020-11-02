package me.zhengjie.modules.mskj.common;

public enum DeviceInspectType {
//    0可见光，1可见光+热红外，2声音检测，3位置状态识别，4表计识别，5导航识别,6是否工作，7是否漏油

    LIGHT("可见光"),
    LIGHT_INFRARED("可见光+热红外"),
    SOUND("声音检测"),
    POSITION_STATUS("位置状态识别"),
    NEEDLE("表计识别"),
    PILOT("导航识别"),
    WORKING_JUDGE("是否工作"),
    LEAKING_JUDGE("是否漏油");

    private String name;

    private DeviceInspectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
