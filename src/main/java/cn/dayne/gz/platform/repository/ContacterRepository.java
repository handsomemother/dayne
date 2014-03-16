package cn.dayne.gz.platform.repository;

import java.util.List;

import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.query.ContacterCriteria;

/**
 * 
 * 
 * @author yeqiuming
 * @date 2013-03-06
 *
 */
public interface ContacterRepository extends GenericRepository<Contacter, Integer> {
	
	/**
	 * 分页查询
	 * 
	 * @param criteria 查询条件
	 * @return
	 */
	List<Contacter> queryPageByCriteria(ContacterCriteria criteria);
	
	List<Contacter> queryByIds(Integer[]ids);
}
