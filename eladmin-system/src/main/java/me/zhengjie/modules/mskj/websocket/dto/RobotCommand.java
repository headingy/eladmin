package me.zhengjie.modules.mskj.websocket.dto;

public class RobotCommand {

	private String event_id;
	private String createTime;
	private String timeout;
	//指令类型，详见文档机器人控制指令类型常量
	private String type;
	//速度
	private String speed;
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	
	
	
	
	
	
}
