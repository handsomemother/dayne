package cn.dayne.gz.platform.dto;

import java.util.Date;
import java.util.Map;

import cn.dayne.gz.platform.entity.ContactGroup;
import cn.dayne.gz.platform.util.CommonConstant;

public class SourceDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	// 数据源名称
	private String sourceName;

	// 数据源地址
	private String urlAddress;

	// 所属系统
	private int sysCode;

	// 接口类型
	private int type;

	// 监控频率
	private int monitorLevel;

	// 正常响应时间，默认1s
	private float responseTime = 1f;

	// 备注
	private String remark;

	// 状态
	private int status;
	
	//参数多个参数用;分隔
	private String param;
	
	//最近响应速度
	private float recentlyResponseTime;

	// 前端显示的接口类型
	private String displayType;

	// 前端显示的监控频率
	private String displayMonitorLevel;

	// 前端显示的所属系统
	private String displaySys;
	
	//联系人组
	private ContactGroup contactGroup ;
	
	//前端联系人组
	private Map<Integer,String>contactGroupMap;
	
	//邮件发送间隔，单位h，默认0.5
	private float emailInterval = 0.5f;
	
	//最近发出错误邮件的时间
	private Date lastSendEmailTime;
	
	//请求类型,get||post
	private String requestType;
	
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

	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}

	public Map<Integer, String> getContactGroupMap() {
		return contactGroupMap;
	}

	public void setContactGroupMap(Map<Integer, String> contactGroupMap) {
		this.contactGroupMap = contactGroupMap;
	}
	
	public float getEmailInterval() {
		return emailInterval;
	}

	public void setEmailInterval(float emailInterval) {
		this.emailInterval = emailInterval;
	}

	public Date getLastSendEmailTime() {
		return lastSendEmailTime;
	}

	public void setLastSendEmailTime(Date lastSendEmailTime) {
		this.lastSendEmailTime = lastSendEmailTime;
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
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
