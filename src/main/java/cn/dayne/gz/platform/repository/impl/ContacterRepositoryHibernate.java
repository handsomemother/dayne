package cn.dayne.gz.platform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.query.ContacterCriteria;
import cn.dayne.gz.platform.repository.ContacterRepository;

/**
 * 
 * 
 * @author yeqiuming
 * @date 2013-03-06
 */
@Repository
public class ContacterRepositoryHibernate extends GenericRepositoryHibernate<Contacter, Integer>
		implements ContacterRepository {

	@Override
	public List<Contacter> queryPageByCriteria(ContacterCriteria criteria) {
		// TODO Auto-generated method stub
		
		StringBuilder hql = new StringBuilder("from Contacter c where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(criteria!=null){
			if(!StringUtils.isBlank(criteria.getName())){
				hql.append(" and c.name like ? ");
				args.add('%'+criteria.getName()+'%');
			}
			if(!StringUtils.isBlank(criteria.getEmail())){
				hql.append(" and c.email like ? ");
				args.add('%'+criteria.getEmail()+'%');
			}
			if(!StringUtils.isBlank(criteria.getTel())){
				hql.append(" and c.tel like ? ");
				args.add('%'+criteria.getTel()+'%');
			}
		}
		hql.append(" order by id desc ");
		criteria.setHql(hql.toString());
		criteria.setParms(args);
		
		@SuppressWarnings("unchecked")
		List<Contacter> list = (List<Contacter>) this.queryPage(criteria);
		return list;
		
	}

	@Override
	public List<Contacter> queryByIds(Integer[] ids) {
		StringBuilder hql = new StringBuilder("from Contacter c where ");
		List<Object> args = new ArrayList<Object>();
		if(ids.length > 0){
			boolean firstFlag = true;
			for(Integer id : ids){
				if(firstFlag){
					hql.append("c.id = ? ");
					firstFlag = false;
				}else{
					hql.append(" or c.id = ? ");
				}
				
				args.add(id);
			}
		}
		List<Contacter> list = (List<Contacter>) this.find(hql.toString(), args.toArray());
		return list;
	}

}
