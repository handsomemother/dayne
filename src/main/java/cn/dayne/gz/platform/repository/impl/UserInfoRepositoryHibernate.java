package cn.dayne.gz.platform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.dayne.gz.platform.entity.UserInfo;
import cn.dayne.gz.platform.query.UserInfoCriteria;
import cn.dayne.gz.platform.repository.UserInfoRepository;

/**
 * 用户仓存
 * 
 * @author yeqiuming
 *
 */
@Repository
public class UserInfoRepositoryHibernate extends GenericRepositoryHibernate<UserInfo, Integer>
		implements UserInfoRepository {

	@Override
	public UserInfo queryByCriteria(UserInfoCriteria criteria) {
		StringBuilder hql = new StringBuilder("from UserInfo u where 1=1 ");
		
		List<Object> args = new ArrayList<Object>();
		
		if(criteria!=null){
			if(!StringUtils.isBlank(criteria.getUserName())){
				hql.append(" and u.userName = ? ");
				args.add(criteria.getUserName());
			}
			if(criteria.getStatus()!=null){
				hql.append(" and u.status = ? ");
				args.add(criteria.getStatus());
			}
		}
		
		@SuppressWarnings("unchecked")
		List<UserInfo> list = this.hibernateTemp.find(hql.toString(), args.toArray());
		if(list.size()>0){
			UserInfo userInfo = list.get(0);
			return userInfo;
		}
		return null;
	}

	@Override
	public List<UserInfo> queryPageByCriteria(UserInfoCriteria criteria) {
		StringBuilder hql = new StringBuilder("from UserInfo u where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(criteria!=null){
			if(!StringUtils.isBlank(criteria.getUserName())){
				hql.append(" and u.userName like ? ");
				args.add('%'+criteria.getUserName()+'%');
			}
			if(!StringUtils.isBlank(criteria.getName())){
				hql.append(" and u.name like ? ");
				args.add('%'+criteria.getName()+'%');
			}
			if(criteria.getStatus()!=null){
				hql.append(" and u.status = ? ");
				args.add(criteria.getStatus());
			}
		}
		hql.append(" order by id desc ");
		criteria.setHql(hql.toString());
		criteria.setParms(args);
		
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) this.queryPage(criteria);
		return list;
	}


}
