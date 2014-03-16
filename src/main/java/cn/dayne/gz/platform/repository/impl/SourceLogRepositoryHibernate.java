package cn.dayne.gz.platform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.dayne.gz.platform.entity.SourceLog;
import cn.dayne.gz.platform.query.SourceLogCriteria;
import cn.dayne.gz.platform.repository.SourceLogRepository;

/**
 * 数据源信息仓存
 * 
 * @author yeqiuming
 * @date 2013-03-06
 */
@Repository
public class SourceLogRepositoryHibernate extends GenericRepositoryHibernate<SourceLog, Integer>
		implements SourceLogRepository {

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.repository.SourceRepository#queryPageByCriteria(cn.ac.iscas.gz.query.SourceCriteria)
	 */
	@Override
	public List<SourceLog> queryPageByCriteria(SourceLogCriteria criteria) {
		StringBuilder hql = new StringBuilder("from SourceLog s where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(criteria!=null){
			if(!StringUtils.isBlank(criteria.getSourceName())){
				hql.append(" and s.sourceName Like ? ");
				args.add('%'+criteria.getSourceName()+'%');
			}
			if(criteria.getMonitorLevel()>0){
				hql.append(" and s.monitorLevel = ? ");
				args.add(criteria.getMonitorLevel());
			}
			if(criteria.getType()>0){
				hql.append(" and s.type = ? ");
				args.add(criteria.getType());
			}
			if(criteria.getStatus()!=null){
				hql.append(" and s.status = ? ");
				args.add(criteria.getStatus());
			}
			if(criteria.getSysCode()>0){
				hql.append(" and s.sysCode = ? ");
				args.add(criteria.getSysCode());
			}
			if(criteria.getStartTime()!=null){
				hql.append(" and s.logTime > ? ");
				args.add(criteria.getStartTime());
			}
			if(criteria.getEndTime()!=null){
				hql.append(" and s.logTime < ? ");
				args.add(criteria.getEndTime());
			}
		}
		hql.append(" order by id desc ");
		criteria.setHql(hql.toString());
		criteria.setParms(args);
		
		//使用hql语句查询总数
		criteria.setTotalNumHql("select count(*)"+hql);
		
		@SuppressWarnings("unchecked")
		List<SourceLog> list = (List<SourceLog>) this.queryPage(criteria);
		
		return list;
	}

	
}
