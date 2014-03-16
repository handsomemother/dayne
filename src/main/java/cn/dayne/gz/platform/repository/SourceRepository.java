package cn.dayne.gz.platform.repository;

import java.util.List;

import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.query.SourceCriteria;

/**
 * 数据源信息仓存接口
 * 
 * @author yeqiuming
 * @date 2013-03-06
 *
 */
public interface SourceRepository extends GenericRepository<Source, Integer> {
	
	/**
	 * 分页查询
	 * 
	 * @param criteria 查询条件
	 * @return
	 */
	List<Source> queryPageByCriteria(SourceCriteria criteria);

	/**
	 * 根据级别查询
	 * 
	 * @param level 监控级别
	 * @return
	 */
	List<Source> queryByMonitoringLevel(Integer level);
}
