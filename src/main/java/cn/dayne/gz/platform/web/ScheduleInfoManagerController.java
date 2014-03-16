package cn.dayne.gz.platform.web;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.dto.TimerDTO;
import cn.dayne.gz.platform.entity.Timer;
import cn.dayne.gz.platform.service.TimerService;
import cn.dayne.gz.platform.util.CommonConstant;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;

@Controller
@RequestMapping("/scheduler.do")
public class ScheduleInfoManagerController {
	
	private Logger log = Logger.getLogger(ScheduleInfoManagerController.class);

	@Resource(name="schedulerFactory")
	private StdScheduler scheduler;
	
	@Resource(name="timerService")
	private TimerService timerService;
	
	private final static String EDIT_PAGE = "jsp/monitor/trigger/trigger_edit";
	
	
	@RequestMapping( method = { RequestMethod.GET, RequestMethod.POST })
	public String initUpdate(ModelMap model){
		model.addAttribute("highTrigger", timerService.getTimerByLevel(CommonConstant.MONITORING_LEVEL_HIGHT));
		model.addAttribute("middleTrigger", timerService.getTimerByLevel(CommonConstant.MONITORING_LEVEL_MIDDLE));
		model.addAttribute("lowTrigger", timerService.getTimerByLevel(CommonConstant.MONITORING_LEVEL_LOW));
		return EDIT_PAGE;
	}
	
	/**
	 * 修改时间
	 * 
	 * @param timerDTO
	 * @throws ParseException 
	 * @throws SchedulerException 
	 * @throws IOException 
	 */
	@RequestMapping(params = "action=submit" , method = { RequestMethod.GET, RequestMethod.POST })
	public void submit(HttpServletResponse response,TimerDTO timerDTO) throws SchedulerException, ParseException, IOException{
		try {
			Timer timer = timerService.getTimerByLevel(timerDTO.getMonitoringLevel());
			timer.setTime(timerDTO.getTime());
			timerService.submit(timer);
			update(timerDTO.getMonitoringLevel());
			log.info("触发时间修改成功");
			HttpServletResponseUtil.writeObject2Response(response, "触发时间修改成功");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
			HttpServletResponseUtil.writeObject2Response(response, "触发时间修改失败");
		}
	}
	
	/**
	 * 动态修改
	 * 
	 * @param level
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	private void update(Integer level) throws SchedulerException, ParseException{
		// 运行时可通过动态注入的scheduler得到trigger
		CronTriggerBean trigger = null;
		String cronTriggerName = null;
		
		if(CommonConstant.MONITORING_LEVEL_HIGHT == level){
			cronTriggerName = CommonConstant.HIGH_CRONTRIGGER;
		}else if(CommonConstant.MONITORING_LEVEL_MIDDLE == level){
			cronTriggerName = CommonConstant.MIDDLE_CRONTRIGGER;
		}else if(CommonConstant.MONITORING_LEVEL_LOW == level){
			cronTriggerName = CommonConstant.LOW_CRONTRIGGER;
		}else{
			Assert.assertNotNull("不存在级别", cronTriggerName);
		}
		
		trigger = (CronTriggerBean) scheduler.getTrigger(cronTriggerName, Scheduler.DEFAULT_GROUP);
		
		String dbCronExpression = timerService.getTimerByLevel(level).getTime();
		String originConExpression = trigger.getCronExpression();
		// 判断从DB中取得的任务时间(dbCronExpression)和现在的quartz线程中的任务时间(originConExpression)是否相等
		// 如果相等，则表示用户并没有重新设定数据库中的任务时间，这种情况不需要重新rescheduleJob
		if (!originConExpression.equalsIgnoreCase(dbCronExpression)) {
			trigger.setCronExpression(dbCronExpression);
			scheduler.rescheduleJob(cronTriggerName, Scheduler.DEFAULT_GROUP,trigger);
		}
	}

	@RequestMapping(params="action=start", method = { RequestMethod.GET, RequestMethod.POST })
	public void start(HttpServletResponse response) throws Exception{
		try{
			scheduler.start();
//			CronTriggerBean trigger = (CronTriggerBean) AppContextUtil.getBean("cronTrigger");
//			scheduler.resumeJob("cronTrigger", Scheduler.DEFAULT_GROUP);
			HttpServletResponseUtil.writeObject2Response(response, "重启动所有定时触发器");
			log.info("重启动所有定时触发器");
		}catch (Exception e) {
			log.error(e);
			HttpServletResponseUtil.writeObject2Response(response, "重启触发器失败");
		}
	}
	
	@RequestMapping(params="action=stop", method = { RequestMethod.GET, RequestMethod.POST })
	public void stop(HttpServletResponse response) throws Exception{
		try {
			scheduler.standby();
	//		scheduler.unscheduleJob("cronTrigger", Scheduler.DEFAULT_GROUP);
			HttpServletResponseUtil.writeObject2Response(response, "暂停所有定时触发器");
			log.info("暂停所有定时触发器");
		} catch (Exception e) {
			log.error(e);
			HttpServletResponseUtil.writeObject2Response(response, "暂停失败");
		}
	}
	
	/**
	 * 校验表达式正确性
	 * 
	 * @param response
	 * @param cron
	 * @throws IOException
	 */
	@RequestMapping(params="action=validate", method = { RequestMethod.GET, RequestMethod.POST })
	public void validate(HttpServletResponse response,String cron) throws IOException{
		try {
			if(cron!=null){
				if(CronExpression.isValidExpression(cron)){
					HttpServletResponseUtil.writeObject2Response(response, true);
				}else{
					HttpServletResponseUtil.writeObject2Response(response, false);
					log.error("表达式：" + cron +" 格式错误");
				}
			}else{
				HttpServletResponseUtil.writeObject2Response(response, false);
			}
		} catch (IOException e) {
			HttpServletResponseUtil.writeObject2Response(response, false);
			log.error(e);
		}
	}
}
