package cn.dayne.gz.platform.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.service.SourceService;
import cn.dayne.gz.platform.service.ValidatorService;
import cn.dayne.gz.platform.util.CommonConstant;

/**
 * @author yeqiuming
 * @date sept 1, 2012
 * @Description 定时任务
 */
@Service("lowTask")
public class LowTask {
	private Logger log = Logger.getLogger(LowTask.class);
	
	@Resource
	private ValidatorService validatorService;
	
	@Resource
	private SourceService sourceSerivce;
	
	/**
	 * 定时任务执行方法
	 */
	public void scheduleJob() {
		log.info("---------------低频任务-------------");
			for(Source source:sourceSerivce.queryByMonitoringLevel(CommonConstant.MONITORING_LEVEL_LOW)){
				try {
					log.info("低频数据"+source.getSourceName()+"执行");
					validatorService.toCheckAndSendEmail(source);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
				}finally{
					log.info("低频数据["+source.getSourceName()+"]执行完成");
				}
			}
		log.info("---------------低频任务结束-------------");
	}
	
}