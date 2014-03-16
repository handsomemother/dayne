package cn.dayne.gz.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.dto.SourceDTO;
import cn.dayne.gz.platform.entity.ContactGroup;
import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.query.SourceCriteria;
import cn.dayne.gz.platform.repository.ContactGroupRepository;
import cn.dayne.gz.platform.repository.SourceRepository;
import cn.dayne.gz.platform.service.SourceService;
import cn.dayne.gz.platform.util.BeanCopyUtil;

@Service("sourceService")
public class SourceServiceImpl extends AbstractApplicationServiceImpl<Source, Integer>
		implements SourceService {
	
	@Resource
	private SourceRepository sourceRepository;
	
	/**
	 * 用户组仓存
	 */
	@Resource
	private ContactGroupRepository contactGroupRepository;

	
	@Override
	public List<SourceDTO> queryPageByCriteria(SourceCriteria criteria) {
		
		return buildSourceDTO(sourceRepository.queryPageByCriteria(criteria));
	}
	
	public List<SourceDTO> buildSourceDTO(List<Source> list){
		List<SourceDTO> dtoList = new ArrayList<SourceDTO>();
		for(Source source:list){
			dtoList.add(BeanCopyUtil.copy(source, SourceDTO.class));
		}
		return dtoList;
	}

	@Override
	public Map<Integer, String> buildGroupMap() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		for(ContactGroup group:contactGroupRepository.findAll(ContactGroup.class)){
			map.put(group.getId(), group.getGroupName());
		}
		return map;
	}

	@Override
	public List<Source> queryByMonitoringLevel(Integer level) {
		return sourceRepository.queryByMonitoringLevel(level);
	}
}
