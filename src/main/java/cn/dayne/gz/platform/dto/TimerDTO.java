package cn.dayne.gz.platform.dto;


public class TimerDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String time;
	
	private Integer monitoringLevel;
	
	private String remark;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getMonitoringLevel() {
		return monitoringLevel;
	}

	public void setMonitoringLevel(Integer monitoringLevel) {
		this.monitoringLevel = monitoringLevel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
