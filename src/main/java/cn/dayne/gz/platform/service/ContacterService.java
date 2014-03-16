package cn.dayne.gz.platform.service;

import java.util.List;

import cn.dayne.gz.platform.dto.ContacterDTO;
import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.query.ContacterCriteria;


/**
 * 
 * 
 * @author yeqiuming
 *
 */
public interface ContacterService extends
		AbstractApplicationService<Contacter, Integer> {
	
	public List<ContacterDTO> queryPageByCriteria(ContacterCriteria criteria);
}
