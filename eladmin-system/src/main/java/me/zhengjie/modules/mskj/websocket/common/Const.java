package me.zhengjie.modules.mskj.websocket.common;

public class Const {
    /**
     * 设备类型
     */
    public final static class DeviceType {

        public static final String DEVICE_YQSBYQ = "DV1000";      //    油浸式变压器(电抗器)
        public static final String DEVICE_DLQ = "DV1001";      //    断路器
        public static final String DEVICE_ZHDQ = "DV1002";      //   组合电器
        public static final String DEVICE_GLKG = "DV1003";      //   隔离开关
        public static final String DEVICE_KGG = "DV1004";      //    开关柜
        public static final String DEVICE_DLBYQ = "DV1005";      //  电流互感器
        public static final String DEVICE_DYHGQ = "DV1006";      //   电压互感器
        public static final String DEVICE_BLQ = "DV1007";      //   避雷器
        public static final String DEVICE_BLDYQ = "DV1008";      //   并联电容器
        public static final String DEVICE_GRDKQ = "DV1009";      //   干式电抗器
        public static final String DEVICE_MXJJYZ = "DV1010";      //   母线及绝缘子
        public static final String DEVICE_CQTG = "DV1011";      //   穿墙套管
        public static final String DEVICE_DLDL = "DV1012";      //   电力电缆
        public static final String DEVICE_XHXQ = "DV1013";      //  消弧线圈
        public static final String DEVICE_GPZBQ = "DV1014";      //  高频阻波器
        public static final String DEVICE_OHDRQ = "DV1015";      //  耦合电容器
        public static final String DEVICE_GYRDQ = "DV1016";      //  高压熔断器
        public static final String DEVICE_JDZZ = "DV1017";      //  接地装置
        public static final String DEVICE_DZXJJXDYX = "DV1018";      //  端子箱及检修电源箱
        public static final String DEVICE_ZYBYQ = "DV1019";      //  站用变压器
        public static final String DEVICE_ZYJLDYXT = "DV1020";      //  站用交流电源系统
        public static final String DEVICE_ZYZLDYXT = "DV1021";      //  站用直流电源系统
        public static final String DEVICE_SBJG = "DV1022";      //  设备构架
        public static final String DEVICE_FZSS = "DV1023";      //  辅助设施
        public static final String DEVICE_TJSS = "DV1024";      //  土建设施
        public static final String DEVICE_DLBLZ = "DV1025";      //  独立避雷针
        public static final String DEVICE_CLBCZZ = "DV1026";      //  串联补偿装置
        public static final String DEVICE_ZXDGZ = "DV1027";      //  中性点隔直（限直）
        public static final String DEVICE_CDZ = "DV1030";      //  充电桩
    }

    /**
     * 机器人控制指令类型
     */
    public final static class CMDType {
        public static final String CMD_ROBOT_START = "CMD_ROBOT_START";     //机器人启动指令
        public static final String CMD_ROBOT_STOP = "CMD_ROBOT_STOP";     //机器人停止指令
        public static final String CMD_ROBOT_COMEBACK = "CMD_ROBOT_COMEBACK"; //机器人返航待命指令
        public static final String CMD_ROBOT_REBACK = "CMD_ROBOT_REBACK";     //机器人一键返航
        public static final String CMD_ROBOT_COMEBACK_CHARGE = "CMD_ROBOT_COMEBACK_CHARGE"; //机器人返航充电指令
        public static final String CMD_ROBOT_COMEBACK_TASK_CHARGE = "CMD_ROBOT_COMEBACK_TASK_CHARGE";//机器人暂停任务返航充电指令

        public static final String CMD_ROBOT_OFF = "CMD_ROBOT_OFF";  //机器人关机指令
        public static final String CMD_ROBOT_RESTART = "CMD_ROBOT_RESTART";  //机器人重启指令

        public static final String CMD_ROBOT_GET = "CMD_ROBOT_GET";  //获取机器人控制权
        public static final String CMD_ROBOT_RELEASE = "CMD_ROBOT_RELEASE";  //释放机器人控制权

        public static final String CMD_ROBOT_MOVE = "CMD_ROBOT_MOVE";     //机器人前进指令
        public static final String CMD_ROBOT_BACK = "CMD_ROBOT_BACK";     //机器人后退指令
        public static final String CMD_ROBOT_TURN_LEFT = "CMD_ROBOT_TURN_LEFT";     //机器人左转动指令
        public static final String CMD_ROBOT_TURN = "CMD_ROBOT_TURN";     //机器人右转动指令
        public static final String CMD_ROBOT_MOVE_LEFT = "CMD_ROBOT_MOVE_LEFT";     //机器人左进指令
        public static final String CMD_ROBOT_MOVE_RIGHT = "CMD_ROBOT_MOVE_RIGHT";     //机器人右进指令

        public static final String CMD_ROBOT_NECK_ADD = "CMD_ROBOT_NECK_ADD";  //机器人颈部伸长
        public static final String CMD_ROBOT_NECK_REDUCE = "CMD_ROBOT_NECK_REDUCE"; //机器人颈部缩短
        public static final String CMD_ROBOT_BODY_ADD = "CMD_ROBOT_BODY_ADD"; //机器人身部伸长
        public static final String CMD_ROBOT_BODY_REDUCE = "CMD_ROBOT_BODY_REDUCE";  //机器人身部缩短
        public static final String CMD_ROBOT_RESET = "CMD_ROBOT_RESET"; //机器人升降杆复位
        public static final String CMD_ROBOT_LAMP_ON = "CMD_ROBOT_LAMP_ON";  //车体辅助灯光开
        public static final String CMD_ROBOT_LAMP_OFF = "CMD_ROBOT_LAMP_OFF";  //车体辅助灯光关

        public static final String CMD_YUNTAI_UP = "CMD_YUNTAI_UP";     //云台上指令
        public static final String CMD_YUNTAI_DOWN = "CMD_YUNTAI_DOWN";     //云台下指令
        public static final String CMD_YUNTAI_TURN_LEFT = "CMD_YUNTAI_TURN_LEFT";     //云台头部向左指令
        public static final String CMD_YUNTAI_TURN_RIGHT = "CMD_YUNTAI_TURN_RIGHT";     //云台头部向右指令
        public static final String CMD_YUNTAI_TURN_UP = "CMD_YUNTAI_TURN_UP";     //云台头部向上指令
        public static final String CMD_YUNTAI_TURN_DOWN = "CMD_YUNTAI_TURN_DOWN";     //云台头部向下指令
        public static final String CMD_YUNTAI_STOP = "CMD_YUNTAI_STOP";     //云台停止指令
        public static final String CMD_YUNTAI_RESETTING = "CMD_YUNTAI_RESETTING";     //云台复位指令

        public static final String CMD_ULTRASONIC_ON = "CMD_ULTRASONIC_ON";  //超声波开启
        public static final String CMD_ULTRASONIC_OFF = "CMD_ULTRASONIC_OFF"; //超声波关闭
        public static final String CMD_SMOKE_ON = "CMD_SMOKE_ON"; //烟雾传感器开
        public static final String CMD_SMOKE_OFF = "CMD_SMOKE_OFF";  //烟雾传感器关
        public static final String CMD_TEMPERATURE_OFF = "CMD_TEMPERATURE_OFF";  //温度传感器关闭
        public static final String CMD_TEMPERATURE_ON = "CMD_TEMPERATURE_ON";  //温度传感器开启
        public static final String CMD_HUMIDITY_OFF = "CMD_HUMIDITY_OFF";  //湿度传感器关闭
        public static final String CMD_HUMIDITY_ON = "CMD_HUMIDITY_ON"; //湿度传感器开启
        public static final String CMD_CHARGE_OFF = "CMD_CHARGE_OFF"; //充电机构关闭
        public static final String CMD_CHARGE_ON = "CMD_CHARGE_ON";  //充电机构开启
        public static final String CMD_PM25_OFF = "CMD_PM2.5_OFF";  //PM2.5传感器关闭
        public static final String CMD_PM25_ON = "CMD_PM2.5_ON";  //PM2.5传感器开启
        //可见光
        public static final String CMD_LIGHT_FOCUS_ADD = "CMD_LIGHT_FOCUS_ADD"; //焦距+
        public static final String CMD_LIGHT_FOCUS_REDUCE = "CMD_LIGHT_FOCUS_REDUCE";  //焦距-
        public static final String CMD_LIGHT_APERTURE_ADD = "CMD_LIGHT_APERTURE_ADD";  //光圈+
        public static final String CMD_LIGHT_APRETURE_REDUCE = "CMD_LIGHT_APERTURE_REDUCE";  //光圈-
        public static final String CMD_LIGHT_LENS_ADD = "CMD_LIGHT_LENS_ADD";  //镜头+
        public static final String CMD_LIGHT_LENS_REDUCE = "CMD_LIGHT_LENS_REDUCE"; //镜头-
        public static final String CMD_LIGHT_WIPER_ON = "CMD_LIGHT_WIPER_ON"; //雨刷开
        public static final String CMD_LIGHT_WIPER_OFF = "CMD_LIGHT_WIPER_OFF";  //雨刷关
        public static final String CMD_LIGHT_LAMP_ON = "CMD_LIGHT_LAMP_ON";  //辅助灯光开
        public static final String CMD_LIGHT_LAMP_OFF = "CMD_LIGHT_LAMP_OFF";  //辅助灯光关
        //热红外
        public static final String CMD_INFARED_FOCUS_ADD = "CMD_INFARED_FOCUS_ADD"; //焦距+
        public static final String CMD_INFARED_FOCUS_REDUCE = "CMD_INFARED_FOCUS_REDUCE";  //焦距-
        public static final String CMD_INFARED_APERTURE_ADD = "CMD_INFARED_APERTURE_ADD";  //光圈+
        public static final String CMD_INFARED_APERTURE_REDUCE = "CMD_INFARED_APERTURE_REDUCE";  //光圈-
        public static final String CMD_INFARED_LENS_ADD = "CMD_INFARED_LENS_ADD";  //镜头+
        public static final String CMD_INFARED_LENS_REDUCE = "CMD_INFARED_LENS_REDUCE"; //镜头-
        public static final String CMD_INFARED_WIPER_ON = "CMD_INFARED_WIPER_ON"; //雨刷开
        public static final String CMD_INFARED_WIPER_OFF = "CMD_INFARED_WIPER_OFF";  //雨刷关
        public static final String CMD_INFARED_LAMP_ON = "CMD_INFARED_LAMP_ON";  //辅助灯光开
        public static final String CMD_INFARED_LAMP_OFF = "CMD_INFARED_LAMP_OFF";  //辅助灯光关


        public static final String MEDIA_PUSH = "MEDIA_PUSH";  //开始推流
        public static final String MEDIA_STOP = "MEDIA_STOP";  //停止推流

        public static final String CMD_TASK_CONTINUE = "CMD_TASK_CONTINUE";  //不录像
        public static final String CMD_TASK_CONTINUE_RECORD = "CMD_TASK_CONTINUE_RECORD";  //录像

    }

    /**
     * 服务端收到的信息类型
     */
    public final static class MessageType {
        public static final String CMD_HEART = "CMD_HEART";//心跳类型
        public static final String CMD_AUTH = "CMD_AUTH";//认证消息
        public static final String CMD_REPORT = "CMD_REPORT";//上报的告警消息
        public static final String MEDIA = "MEDIA";//直播
        public static final String INDEX = "INDEX";//首页重要消息
        public static final String STATUS = "STATUS";//机器人状态
        public static final String ALERT = "ALERT";//首页警告消息
        public static final String TASK = "TASK";//任务
        public static final String IMAGE = "IMAGE";//图片消息
        public static final String CHARGE = "CHARGE";//充电完成后的消息
        public static final String FILE = "FILE";//文件
        public static final String FACE_RECOGNITION = "FACE_RECOGNITION";//人脸识别图片
        public static final String DEVICE_PIC_ID = "DEVICE_PIC_ID";
        public static final String REFERENCEINFO = "REFERENCEINFO";//位置信息
        public static final String CHARGE_REQUEST = "CHARGE_REQUEST";//请求充电
        public static final String CHARGE_APPOINTMENT = "CHARGE_APPOINTMENT";//充电桩预约
        public static final String CHARGE_APPOINTMENT_ERROR = "CHARGE_APPOINTMENT_ERROR";//机器人充电桩不可达
        public static final String CHARGE_GOING = "CHARGE_GOING";//开始充电
        public static final String CHARGE_FINISHED = "CHARGE_FINISHED";
        public static final String CHARGE_EXCEPTION = "CHARGE_EXCEPTION";
        public static final String MAP_UPLOAD_REQ = "MAP_UPLOAD_REQ";
        public static final String patrolResult = "patrolResult";
    }


    /**
     * 服务端向机器人端返回的信息类型
     */
    public final static class ResponseMessageType {
        public static final String CHARGE_APPOINTMENT_RESPONSE = "CHARGE_APPOINTMENT_RESPONSE";//充电桩预约响应
    }

    /**
     * 任务执行状态
     */
    public final static class TaskExeStatus {


    }
}
