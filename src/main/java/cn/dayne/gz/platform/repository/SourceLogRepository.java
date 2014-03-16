package cn.dayne.gz.platform.repository;

import java.util.List;

import cn.dayne.gz.platform.entity.SourceLog;
import cn.dayne.gz.platform.query.SourceLogCriteria;

/**
 * 日志信息仓存接口
 * 
 * @author yeqiuming
 * @date 2013-03-06
 *
 */
public interface SourceLogRepository extends GenericRepository<SourceLog, Integer> {
	
	/**
	 * 分页查询
	 * 
	 * @param criteria 查询条件
	 * @return
	 */
	List<SourceLog> queryPageByCriteria(SourceLogCriteria criteria);


}
