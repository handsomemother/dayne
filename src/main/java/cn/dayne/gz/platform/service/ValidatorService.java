package cn.dayne.gz.platform.service;

import cn.dayne.gz.platform.entity.Source;

public interface ValidatorService {
	
	/**
	 * 检查数据源若有异常并发出信息
	 * 
	 * @param source
	 */
	public void toCheckAndSendEmail(Source source);
	
	/**
	 * 单独检测某数据源
	 * 
	 * @param source
	 */
	public void ckeck(Source source);
}
