package cn.dayne.gz.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.dto.SourceLogDTO;
import cn.dayne.gz.platform.entity.SourceLog;
import cn.dayne.gz.platform.query.SourceLogCriteria;
import cn.dayne.gz.platform.repository.SourceLogRepository;
import cn.dayne.gz.platform.service.SourceLogService;
import cn.dayne.gz.platform.util.BeanCopyUtil;

/**
 * @author yeqiuming
 *
 */
@Service
public class SourceLogServiceImpl extends AbstractApplicationServiceImpl<SourceLog, Integer>
		implements SourceLogService {
	
	@Resource
	private SourceLogRepository sourceLogRepository;
	
	@Override
	public List<SourceLogDTO> queryPageByCriteria(SourceLogCriteria criteria) {
		
		return buildSourceLogDTO(sourceLogRepository.queryPageByCriteria(criteria));
	}
	
	public List<SourceLogDTO> buildSourceLogDTO(List<SourceLog> list){
		List<SourceLogDTO> dtoList = new ArrayList<SourceLogDTO>();
		for(SourceLog sourceLog:list){
			dtoList.add(BeanCopyUtil.copy(sourceLog, SourceLogDTO.class));
		}
		return dtoList;
	}

}
