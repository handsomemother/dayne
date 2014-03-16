package cn.dayne.gz.platform.service;

import java.util.List;

import cn.dayne.gz.platform.dto.SourceLogDTO;
import cn.dayne.gz.platform.entity.SourceLog;
import cn.dayne.gz.platform.query.SourceLogCriteria;


/**
 * 日志信息服务接口
 * 
 * @author yeqiuming
 *
 */
public interface SourceLogService extends
		AbstractApplicationService<SourceLog, Integer> {
	
	List<SourceLogDTO> queryPageByCriteria(SourceLogCriteria criteria);
	
}
