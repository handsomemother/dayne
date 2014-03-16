package cn.dayne.gz.platform.repository.impl;

import org.springframework.stereotype.Repository;

import cn.dayne.gz.platform.entity.EmailConfig;
import cn.dayne.gz.platform.repository.EmailConfigRepository;

/**
 * 
 * 
 * @author yeqiuming
 * @date 2013-03-06
 */
@Repository
public class EmailConfigRepositoryHibernate extends GenericRepositoryHibernate<EmailConfig, Integer>
		implements EmailConfigRepository {

	
}
