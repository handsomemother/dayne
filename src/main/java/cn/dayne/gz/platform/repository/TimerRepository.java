package cn.dayne.gz.platform.repository;

import cn.dayne.gz.platform.entity.Timer;

public interface TimerRepository extends GenericRepository<Timer, Integer> {

	public Timer getNewestTimer();
	
	public Timer getTimerByLevel(Integer level);
}
