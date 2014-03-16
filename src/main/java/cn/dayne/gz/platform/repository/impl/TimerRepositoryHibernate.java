package cn.dayne.gz.platform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.dayne.gz.platform.entity.Timer;
import cn.dayne.gz.platform.repository.TimerRepository;

@Repository
public class TimerRepositoryHibernate extends GenericRepositoryHibernate<Timer, Integer>
		implements TimerRepository {

	@Override
	public Timer getNewestTimer() {
		@SuppressWarnings("unchecked")
		List<Timer> list = this.getHibernateTemp().find("from Timer t order by id desc");
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Timer getTimerByLevel(Integer level) {
		StringBuilder hql = new StringBuilder("from Timer t where 1=1 ");
		
		List<Object> args = new ArrayList<Object>();
		
		if(level!=null){
			hql.append(" and t.monitoringLevel = ? ");
			args.add(level);
		}
		
		@SuppressWarnings("unchecked")
		List<Timer> list = this.hibernateTemp.find(hql.toString(), args.toArray());
		if(list.size()>0){
			Timer timer = list.get(0);
			return timer;
		}
		return null;
	}
	
}
