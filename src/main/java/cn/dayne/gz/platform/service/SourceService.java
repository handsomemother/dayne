package cn.dayne.gz.platform.service;

import java.util.List;
import java.util.Map;

import cn.dayne.gz.platform.dto.SourceDTO;
import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.query.SourceCriteria;


/**
 * 数据源信息服务接口
 * 
 * @author yeqiuming
 *
 */
public interface SourceService extends
		AbstractApplicationService<Source, Integer> {
	
	List<SourceDTO> queryPageByCriteria(SourceCriteria criteria);
	
	List<Source> queryByMonitoringLevel(Integer level);
	
	Map<Integer,String> buildGroupMap();
}
