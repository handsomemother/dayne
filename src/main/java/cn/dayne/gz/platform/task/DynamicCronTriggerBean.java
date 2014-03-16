package cn.dayne.gz.platform.task;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.scheduling.quartz.CronTriggerBean;

import cn.dayne.gz.platform.entity.Timer;
import cn.dayne.gz.platform.service.TimerService;

/**
 * 自定义触发器
 * 
 * @author yeqiuming
 *
 */
public class DynamicCronTriggerBean extends CronTriggerBean implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(DynamicCronTriggerBean.class);
	
	/**
	 * 触发时间服务
	 */
	private TimerService timerService;
	
	/**
	 * 级别
	 */
	private Integer monitoringLevel;

	public TimerService getTimerService() {
		return timerService;
	}

	public void setTimerService(TimerService timerService) {
		this.timerService = timerService;
		
		//根据级别取出触发时间
		Timer timer = timerService.getTimerByLevel(this.getMonitoringLevel());
		String cronExpression = timer.getTime(); 
		try {
			//向触发器注入触发时间
			setCronExpression(cronExpression);
		} catch (ParseException e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	public Integer getMonitoringLevel() {
		return monitoringLevel;
	}

	public void setMonitoringLevel(Integer monitoringLevel) {
		this.monitoringLevel = monitoringLevel;
	}

}
