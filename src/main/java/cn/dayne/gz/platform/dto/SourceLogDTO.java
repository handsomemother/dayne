package cn.dayne.gz.platform.dto;

import java.util.Date;

import cn.dayne.gz.platform.util.CommonConstant;


public class SourceLogDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//数据源名称
	private String sourceName;
	
	//url地址
	private String urlAddress;
	
	//执行开始时间
	private Date startTime;
	
	//执行结束时间
	private Date endTime;
	
	//执行结果
	private String result;
	
	//接口类型
	private int type;
	
	//正常使用时间
	private float responseTime;
	
	//最近响应速度
	private float recentlyResponseTime;
	
	//接口状态
	private int status;
	
	// 所属系统
	private int sysCode;
	
	// 监控频率
	private int monitorLevel;

	// 记录时间
	private Date logTime;
	
	// 前端显示的接口类型
	private String displayType;

	// 前端显示的监控频率
	private String displayMonitorLevel;

	// 前端显示的所属系统
	private String displaySys;
	
	// 日志所对应的接口ID
	private int sourceId;
	
	//请求类型,get||post
	private String requestType;
	
	//参数多个参数用;分隔
	private String param;
	
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getUrlAddress() {
		return urlAddress;
	}

	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public float getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(float responseTime) {
		this.responseTime = responseTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getMonitorLevel() {
		return monitorLevel;
	}

	public void setMonitorLevel(int monitorLevel) {
		this.monitorLevel = monitorLevel;
	}

	public int getSysCode() {
		return sysCode;
	}

	public void setSysCode(int sysCode) {
		this.sysCode = sysCode;
	}
	
	public float getRecentlyResponseTime() {
		return recentlyResponseTime;
	}

	public void setRecentlyResponseTime(float recentlyResponseTime) {
		this.recentlyResponseTime = recentlyResponseTime;
	}
	
	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	//因为时间问题，暂时写死
	public String getDisplayType() {
		if (this.getType() == CommonConstant.TYPE_TELECOM) {
			displayType = "电信接口";
		}else if (this.getType() == CommonConstant.TYPE_REST) {
			displayType = "REST接口";
		} else if (this.getType() == CommonConstant.TYPE_WS) {
			displayType = "WS接口";
		} else if (this.getType() == CommonConstant.TYPE_DATABASE) {
			displayType = "数据库";
		} else if (this.getType() == CommonConstant.TYPE_WEB) {
			displayType = "网页";
		}
		return displayType;
	}

	//因为时间问题，暂时写死
	public String getDisplayMonitorLevel() {
		if (this.getMonitorLevel() == CommonConstant.MONITORING_LEVEL_HIGHT) {
			displayMonitorLevel = "高";
		} else if (this.getMonitorLevel() == CommonConstant.MONITORING_LEVEL_MIDDLE) {
			displayMonitorLevel = "中";
		} else if (this.getMonitorLevel() == CommonConstant.MONITORING_LEVEL_LOW) {
			displayMonitorLevel = "低";
		}
		return displayMonitorLevel;
	}

	//因为时间问题，暂时写死
	public String getDisplaySys() {
		if (this.getSysCode() == CommonConstant.SYS_PLATFORM) {
			displaySys = "智慧家庭服务平台";
		} else if (this.getSysCode() == CommonConstant.SYS_TERMINAL) {
			displaySys = "智慧家居控制平台";
		} 
		return displaySys;
	}
	
}
