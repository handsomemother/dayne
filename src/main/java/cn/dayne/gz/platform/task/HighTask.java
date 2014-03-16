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
 * @Description 定时任务类
 */
@Service("highTask")
public class HighTask {

	private Logger log = Logger.getLogger(HighTask.class);
	
	@Resource
	private ValidatorService validatorService;
	
	@Resource
	private SourceService sourceSerivce;
	
	/**
	 * 定时任务执行方法
	 */
	public void scheduleJob() {
		log.info("---------------高频任务开始-------------");
			for(Source source:sourceSerivce.queryByMonitoringLevel(CommonConstant.MONITORING_LEVEL_HIGHT)){
				try {
					log.info("高频数据源:["+source.getSourceName()+"]开始执行");
					validatorService.toCheckAndSendEmail(source);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
				}finally{
					log.info("高频数据源:["+source.getSourceName()+"]执行完成");
				}
			}
		log.info("---------------高频任务结束-------------");
	}
	
}