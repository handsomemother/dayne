package cn.dayne.gz.platform.service;

import cn.dayne.gz.platform.entity.EmailConfig;


/**
 * 
 * 
 * @author yeqiuming
 *
 */
public interface EmailConfigService extends
		AbstractApplicationService<EmailConfig , Integer> {
	
	public EmailConfig getEmailConfig();
}
