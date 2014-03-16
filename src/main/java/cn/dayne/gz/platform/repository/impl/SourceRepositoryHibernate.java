package cn.dayne.gz.platform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.query.SourceCriteria;
import cn.dayne.gz.platform.repository.SourceRepository;

/**
 * 数据源信息仓存
 * 
 * @author yeqiuming
 * @date 2013-03-06
 */
@Repository
public class SourceRepositoryHibernate extends GenericRepositoryHibernate<Source, Integer>
		implements SourceRepository {

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.repository.SourceRepository#queryPageByCriteria(cn.ac.iscas.gz.query.SourceCriteria)
	 */
	@Override
	public List<Source> queryPageByCriteria(SourceCriteria criteria) {
		StringBuilder hql = new StringBuilder("from Source s where 1=1 ");
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
			if(criteria.getSysCode()>0){
				hql.append(" and s.sysCode = ? ");
				args.add(criteria.getSysCode());
			}
			if(criteria.getStatus()!=null){
				hql.append(" and s.status = ? ");
				args.add(criteria.getStatus());
			}
		}
		hql.append(" order by id desc ");
		criteria.setHql(hql.toString());
		criteria.setParms(args);
		@SuppressWarnings("unchecked")
		List<Source> list = (List<Source>) this.queryPage(criteria);
		return list;
	}

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.repository.SourceRepository#queryByMonitoringLevel(java.lang.Integer)
	 */
	@Override
	public List<Source> queryByMonitoringLevel(Integer level) {
		StringBuilder hql = new StringBuilder("from Source s where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(level!=null){
			hql.append(" and s.monitorLevel = ? ");
			args.add(level);
		}
		hql.append(" order by id desc ");
		List<Source> list = (List<Source>) this.find(hql.toString(), args.toArray());
		return list;
	}
	
}
