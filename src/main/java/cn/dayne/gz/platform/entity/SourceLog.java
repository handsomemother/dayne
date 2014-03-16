package cn.dayne.gz.platform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sourcelog")
public class SourceLog extends AbstractEntity {

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
	
	//正常响应速度
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

	@Column(length = 500)
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

	@Column(length=2000)
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

	public int getSysCode() {
		return sysCode;
	}

	public void setSysCode(int sysCode) {
		this.sysCode = sysCode;
	}

	public int getMonitorLevel() {
		return monitorLevel;
	}

	public void setMonitorLevel(int monitorLevel) {
		this.monitorLevel = monitorLevel;
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
	
	@Column(length=1000)
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}
