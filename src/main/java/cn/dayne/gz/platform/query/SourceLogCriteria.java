package cn.dayne.gz.platform.query;

import java.util.Date;

public class SourceLogCriteria extends PageSearchCriteria{
	
	/**
	 * 监控源名称
	 */
	private String sourceName;
	
	/**
	 * 监控频率,1:高2:中3:低
	 */
	private int monitorLevel;
	
	/**
	 * 接口类型,1:REST接口2:WS接口3:数据库4网页
	 */
	private int type;
	
	/**
	 * 所属系统
	 */
	private int sysCode;
	
	/**
	 * 日志时间开始范围
	 */
	private Date startTime;
	
	/**
	 * 日志时间结束范围
	 */
	private Date endTime;
	
	private Integer status;
	
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public int getMonitorLevel() {
		return monitorLevel;
	}

	public void setMonitorLevel(int monitorLevel) {
		this.monitorLevel = monitorLevel;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSysCode() {
		return sysCode;
	}

	public void setSysCode(int sysCode) {
		this.sysCode = sysCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
