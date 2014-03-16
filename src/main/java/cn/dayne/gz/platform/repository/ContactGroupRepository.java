package cn.dayne.gz.platform.repository;

import java.util.List;

import cn.dayne.gz.platform.entity.ContactGroup;
import cn.dayne.gz.platform.query.ContactGroupCriteria;

/**
 * 
 * @author yeqiuming
 * @date 2013-03-06
 *
 */
public interface ContactGroupRepository extends GenericRepository<ContactGroup, Integer> {
	
	/**
	 * 分页查询
	 * 
	 * @param criteria 查询条件
	 * @return
	 */
	List<ContactGroup> queryPageByCriteria(ContactGroupCriteria criteria);
	
}
