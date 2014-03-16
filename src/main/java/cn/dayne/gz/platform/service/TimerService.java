package cn.dayne.gz.platform.service;

import cn.dayne.gz.platform.entity.Timer;

public interface TimerService extends
		AbstractApplicationService<Timer, Integer> {

	public Timer getNewestTimer();
	
	public Timer getTimerByLevel(Integer level);
}
