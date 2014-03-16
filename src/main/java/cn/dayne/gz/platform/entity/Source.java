package cn.dayne.gz.platform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="source")
public class Source extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//数据源名称
	private String sourceName;
	
	//数据源地址
	private String urlAddress;
	
	//所属系统
	private int sysCode;
	
	//接口类型
	private int type;
	
	//监控级别
	private int monitorLevel;
	
	//响应时间
	private float responseTime;
	
	//备注
	private String remark;
	
	//状态,1正常 0异常
	private int status;
	
	//最近响应速度
	private float recentlyResponseTime;
	
	//邮件发送组群
	private ContactGroup contactGroup;
	
	//最近发出错误邮件的时间
	private Date lastSendEmailTime;
	
	//邮件发送间隔，单位h
	private float emailInterval;
	
	//参数多个参数用;分隔
	private String param;
	
	//请求类型,get||post
	private String requestType;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	@Column(length=500)
	public String getUrlAddress() {
		return urlAddress;
	}

	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	public int getSysCode() {
		return sysCode;
	}

	public void setSysCode(int sysCode) {
		this.sysCode = sysCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMonitorLevel() {
		return monitorLevel;
	}

	public void setMonitorLevel(int monitorLevel) {
		this.monitorLevel = monitorLevel;
	}

	@Column(length=500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(float responseTime) {
		this.responseTime = responseTime;
	}

	public float getRecentlyResponseTime() {
		return recentlyResponseTime;
	}

	public void setRecentlyResponseTime(float recentlyResponseTime) {
		this.recentlyResponseTime = recentlyResponseTime;
	}

	public Date getLastSendEmailTime() {
		return lastSendEmailTime;
	}

	public void setLastSendEmailTime(Date lastSendEmailTime) {
		this.lastSendEmailTime = lastSendEmailTime;
	}

	public float getEmailInterval() {
		return emailInterval;
	}

	public void setEmailInterval(float emailInterval) {
		this.emailInterval = emailInterval;
	}

	@Column(length=1000)
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@OneToOne
	@JoinColumn(name="fk_conteactGroupId")
	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
}
