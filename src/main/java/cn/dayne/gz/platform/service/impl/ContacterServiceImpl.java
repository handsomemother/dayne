package cn.dayne.gz.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.dto.ContacterDTO;
import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.query.ContacterCriteria;
import cn.dayne.gz.platform.repository.ContacterRepository;
import cn.dayne.gz.platform.service.ContacterService;
import cn.dayne.gz.platform.util.BeanCopyUtil;

/**
 * @author yeqiuming
 *
 */
@Service("contacterService")
public class ContacterServiceImpl extends AbstractApplicationServiceImpl<Contacter, Integer>
		implements ContacterService {

	@Resource
	private ContacterRepository contacterRepository;


	@Override
	public List<ContacterDTO> queryPageByCriteria(ContacterCriteria criteria) {
		
		return buildDTO(contacterRepository.queryPageByCriteria(criteria));
	}
	
	public List<ContacterDTO> buildDTO(List<Contacter> list){
		List<ContacterDTO> dtoList = new ArrayList<ContacterDTO>();
		for(Contacter contacter:list){
			dtoList.add(BeanCopyUtil.copy(contacter, ContacterDTO.class));
		}
		return dtoList;
	}
}
