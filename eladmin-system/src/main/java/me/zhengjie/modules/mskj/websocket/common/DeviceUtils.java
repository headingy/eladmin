package me.zhengjie.modules.mskj.websocket.common;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.mskj.service.NeedlechartService;
import me.zhengjie.modules.mskj.service.PointerchartService;
import me.zhengjie.modules.mskj.service.dto.NeedlechartDto;
import me.zhengjie.modules.mskj.service.dto.NeedlechartQueryCriteria;
import me.zhengjie.modules.mskj.service.dto.PointerchartDto;
import me.zhengjie.modules.mskj.service.dto.PointerchartQueryCriteria;
import me.zhengjie.modules.mskj.websocket.common.constant.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class DeviceUtils {

    private final PointerchartService pointerchartService;
    private final NeedlechartService needlechartService;

    @Value("${mskj.applicationType}")
    private String applicationType;

    public Map<String, List> getDevice() {
        //设备类型
        List<JSONObject> devicetype = new ArrayList<JSONObject>();
        //所属区域
        List<Integer> direct = new ArrayList<Integer>();
        //缺陷类型
        List<JSONObject> defectType = new ArrayList<JSONObject>();
        //识别类型
        List<JSONObject> inspectType = new ArrayList<JSONObject>();
        //表计类型
        List<JSONObject> needleType = new ArrayList<JSONObject>();
        //表针类型
        List<JSONObject> pointerType = new ArrayList<JSONObject>();
        //单位
        List<String> unit = new ArrayList<String>();
        //角色
        List<JSONObject> role = new ArrayList<JSONObject>();
        //设备朝向
        List<JSONObject> oriention = new ArrayList<JSONObject>();
        //字号设置
        List<Integer> fontsize = new ArrayList<Integer>();
        //时间--大小设置
        List<Integer> timesize = new ArrayList<Integer>();
        //视频通道
        List<JSONObject> light = Light.getAllLights();
        //视频质量
        List<String> quality = new ArrayList<String>();
        //码流
        List<Integer> bit = new ArrayList<Integer>();
        //帧数
        List<Integer> frame = new ArrayList<Integer>();
        //告警声音
        List<Integer> voice = new ArrayList<Integer>();
        //告警级别
        List<JSONObject> level = WarnLevel.getAllWarnLevels();
        //消息类型
        List<JSONObject> infoType = WarnInfoType.getAllWarnInfoTypes();
        //巡检类型
        List<JSONObject> taskType = TaskType.getAllTaskTypes();
        //巡检路径规划
        List<JSONObject> road = Road.getAllRoads();
        //巡检结束状态
        List<JSONObject> endStatus = EndStatus.getAllEndStatus();
        //任务状态
        List<JSONObject> taskStatus = TaskStatus.getAllTaskStatus();
        //任务执行状态
        List<JSONObject> execStatus = ExecStatus.getAllExecStatus();
        Map<String, List> maps = new HashMap<>();

        voice.add(1);
        voice.add(2);


        fontsize.add(1);
        fontsize.add(2);
        fontsize.add(3);
        fontsize.add(4);
        fontsize.add(5);
        fontsize.add(6);
        fontsize.add(7);
        fontsize.add(8);
        fontsize.add(9);

        timesize.add(1);
        timesize.add(2);
        timesize.add(3);
        timesize.add(4);
        timesize.add(5);
        timesize.add(6);
        timesize.add(7);
        timesize.add(8);
        timesize.add(9);

        quality.add("720P");
        quality.add("1080P");

        bit.add(256);
        bit.add(512);
        bit.add(1024);

        frame.add(16);
        frame.add(32);
        frame.add(64);

        switch (applicationType) {
            case "oil":
                for (int i = 0; i < 5; i++) {
                    JSONObject obj = new JSONObject();
                    if (i == 0) {
                        obj.put("key", "-1");
                        obj.put("value", "全部");
                    } else if (i == 1) {
                        obj.put("key", "YJ1000");
                        obj.put("value", "磕头机");
                    } else if (i == 2) {
                        obj.put("key", "YJ1001");
                        obj.put("value", "油封");
                    } else if (i == 3) {
                        obj.put("key", "YJ1002");
                        obj.put("value", "压力表");
                    } else if (i == 4) {
                        obj.put("key", "DV1030");
                        obj.put("value", "充电桩");
                    }
                    devicetype.add(obj);
                }
                for (int i = 0; i < 4; i++) {
                    JSONObject obj = new JSONObject();
                    if (i == 0) {
                        obj.put("key", -1);
                        obj.put("value", "全部");
                    } else if (i == 1) {
                        obj.put("key", 6);
                        obj.put("value", "可见光视频");
                    } else if (i == 2) {
                        obj.put("key", 4);
                        obj.put("value", "表计识别");
                    } else if (i == 3) {
                        obj.put("key", 5);
                        obj.put("value", "无");
                    }
                    inspectType.add(obj);
                }
                break;
            case "power":
            default:
                for (int i = 0; i < 5; i++) {
                    JSONObject obj = new JSONObject();
                    if (i == 0) {
                        obj.put("key", "-1");
                        obj.put("value", "全部");
                    } else if (i == 1) {
                        obj.put("key", "DV1028");
                        obj.put("value", "仪表");
                    } else if (i == 2) {
                        obj.put("key", "DV1029");
                        obj.put("value", "导肮标");
                    } else if (i == 3) {
                        obj.put("key", "DV1030");
                        obj.put("value", "充电桩");
                    } else if (i == 4) {
                        obj.put("key", "DV1031");
                        obj.put("value", "燃气管道");
                    }
                    devicetype.add(obj);
                }
                for (int i = 0; i < 9; i++) {
                    JSONObject obj = new JSONObject();
                    if (i == 0) {
                        obj.put("key", "-1");
                        obj.put("value", "全部");
                    } else if (i == 1) {
                        obj.put("key", "0");
                        obj.put("value", "可见光");
                    } else if (i == 2) {
                        obj.put("key", "1");
                        obj.put("value", "可见光+红热外");
                    } else if (i == 3) {
                        obj.put("key", "2");
                        obj.put("value", "声音检测");
                    } else if (i == 4) {
                        obj.put("key", "3");
                        obj.put("value", "位置状态识别");
                    } else if (i == 5) {
                        obj.put("key", "4");
                        obj.put("value", "表计识别");
                    } else if (i == 6) {
                        obj.put("key", "5");
                        obj.put("value", "导航识别");
                    } else if (i == 7) {
                        obj.put("key", "8");
                        obj.put("value", "充电桩");
                    } else if (i == 8) {
                        obj.put("key", "10");
                        obj.put("value", "气体检测");
                    }
                    inspectType.add(obj);
                }
                break;
        }

        direct.add(0);
        direct.add(1);
        direct.add(2);
        direct.add(3);
        direct.add(4);
        direct.add(5);
        direct.add(6);
        direct.add(7);

        for (int i = 0; i < 4; i++) {
            JSONObject obj = new JSONObject();
            if (i == 0) {
                obj.put("key", i);
                obj.put("value", "北");
            } else if (i == 1) {
                obj.put("key", i);
                obj.put("value", "东");
            } else if (i == 2) {
                obj.put("key", i);
                obj.put("value", "南");
            } else if (i == 3) {
                obj.put("key", i);
                obj.put("value", "西");
            }
            oriention.add(obj);
        }

        for (int i = 0; i < 4; i++) {
            JSONObject obj = new JSONObject();
            if (i == 0) {
                obj.put("key", -1);
                obj.put("value", "全部");
            } else if (i == 1) {
                obj.put("key", 2);
                obj.put("value", "无");
            } else if (i == 2) {
                obj.put("key", 0);
                obj.put("value", "电流致热型");
            } else if (i == 3) {
                obj.put("key", 1);
                obj.put("value", "电压致热型");
            }
            defectType.add(obj);
        }


        List<PointerchartDto> pointerchartlist = pointerchartService.queryAll(new PointerchartQueryCriteria());
        for (int i = 0; i < pointerchartlist.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("key", pointerchartlist.get(i).getChartid());
            obj.put("value", pointerchartlist.get(i).getValue());
            obj.put("multichartAnnotation", pointerchartlist.get(i).getAnnotation());
            pointerType.add(obj);
        }

        List<NeedlechartDto> neddlechartlist = needlechartService.queryAll(new NeedlechartQueryCriteria());
        for (int i = 0; i < neddlechartlist.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("key", Integer.parseInt(neddlechartlist.get(i).getChartid()));
            obj.put("value", neddlechartlist.get(i).getValue());
            needleType.add(obj);
        }

//	   for(int i=0;i<12;i++) {
//		   JSONObject obj2 = new JSONObject();
//		   if(i==0) {
//			   obj2.put("key",-1);
//      		   obj2.put("value", "全部");
//		   }
//		   else if(i==1) {
//			   obj2.put("key",5);
//      		   obj2.put("value", "无");
//		   }
//		   else if(i==2) {
//			   obj2.put("key",0);
//      		   obj2.put("value", "油位表");
//		   }else if(i ==3) {
//			   obj2.put("key",1);
//      		   obj2.put("value", "温度表");
//		   }else if(i==4) {
//			   obj2.put("key",2);
//      		   obj2.put("value", "档位表");
//		   }else if(i==5) {
//			   obj2.put("key",3);
//      		   obj2.put("value", "SF6压力表");
//		   }else if(i==6) {
//			   obj2.put("key",4);
//      		   obj2.put("value", "泄漏电流表");
//		   }else if(i==7) {
//			   obj2.put("key",6);
//      		   obj2.put("value", "两态档位表");
//		   }else if(i ==8) {
//			   obj2.put("key",7);
//      		   obj2.put("value", "三态档位表");
//		   }else if(i==9) {
//			   obj2.put("key",8);
//      		   obj2.put("value", "LED彩色数字仪表");
//		   }else if(i==10) {
//			   obj2.put("key",9);
//      		   obj2.put("value", "LED灰色数字仪表");
//		   }else if(i==11) {
//			   obj2.put("key",10);
//      		   obj2.put("value", "电气指示灯仪表");
//		   }
//		   needleType.add(obj2);
//	   }

        unit.add("℃");
        unit.add("V");
        unit.add("%RH");

        for (int i = 0; i < 3; i++) {
            JSONObject obj3 = new JSONObject();
            if (i == 0) {
                obj3.put("roleId", i);
                obj3.put("roleName", "普通用户");
            } else if (i == 1) {
                obj3.put("roleId", i);
                obj3.put("roleName", "管理员用户");
            } else if (i == 2) {
                obj3.put("roleId", i);
                obj3.put("roleName", "超级管理员用户");
            }
            role.add(obj3);
        }

        maps.put("devicetype", devicetype);
        maps.put("direct", direct);
        maps.put("oriention", oriention);
        maps.put("defectType", defectType);
        maps.put("inspectType", inspectType);
        maps.put("needleType", needleType);
        maps.put("unit", unit);
        maps.put("role", role);
        maps.put("fontsize", fontsize);
        maps.put("timesize", timesize);
        maps.put("light", light);
        maps.put("quality", quality);
        maps.put("bit", bit);
        maps.put("frame", frame);
        maps.put("voice", voice);
        maps.put("level", level);
        maps.put("infoType", infoType);
        maps.put("taskType", taskType);
        maps.put("road", road);
        maps.put("endStatus", endStatus);
        maps.put("taskStatus", taskStatus);
        maps.put("execStatus", execStatus);
        maps.put("pointerType", pointerType);
        return maps;
    }

}
