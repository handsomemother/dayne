package cn.dayne.gz.platform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.dayne.gz.platform.entity.ContactGroup;
import cn.dayne.gz.platform.query.ContactGroupCriteria;
import cn.dayne.gz.platform.repository.ContactGroupRepository;

/**
 * 
 * 
 * @author yeqiuming
 * @date 2013-03-06
 */
@Repository
public class ContactGroupRepositoryHibernate extends GenericRepositoryHibernate<ContactGroup, Integer>
		implements ContactGroupRepository {

	@Override
	public List<ContactGroup> queryPageByCriteria(ContactGroupCriteria criteria) {
		// TODO Auto-generated method stub
		
		StringBuilder hql = new StringBuilder("from ContactGroup c where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(criteria!=null){
			if(!StringUtils.isBlank(criteria.getName())){
				hql.append(" and c.groupName like ? ");
				args.add('%'+criteria.getName()+'%');
			}
		}
		hql.append(" order by id desc ");
		criteria.setHql(hql.toString());
		criteria.setParms(args);
		
		@SuppressWarnings("unchecked")
		List<ContactGroup> list = (List<ContactGroup>) this.queryPage(criteria);
		return list;
		
	}
	
}
