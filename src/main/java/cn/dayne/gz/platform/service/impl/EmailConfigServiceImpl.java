package cn.dayne.gz.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.entity.EmailConfig;
import cn.dayne.gz.platform.repository.EmailConfigRepository;
import cn.dayne.gz.platform.service.EmailConfigService;

/**
 * @author yeqiuming
 *
 */
@Service("emailConfigService")
public class EmailConfigServiceImpl extends AbstractApplicationServiceImpl<EmailConfig, Integer>
		implements EmailConfigService {

	@Resource
	private EmailConfigRepository emailConfigRepository;

	@Override
	public EmailConfig getEmailConfig() {
		List<EmailConfig>list = emailConfigRepository.findAll(EmailConfig.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
