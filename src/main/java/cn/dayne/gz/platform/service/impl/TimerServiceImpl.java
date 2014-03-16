package cn.dayne.gz.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.entity.Timer;
import cn.dayne.gz.platform.repository.TimerRepository;
import cn.dayne.gz.platform.service.TimerService;

@Service("timerService")
public class TimerServiceImpl extends AbstractApplicationServiceImpl<Timer, Integer>
		implements TimerService {

	@Resource
	private TimerRepository timerRepository;
	
	@Override
	public Timer getNewestTimer() {
		
		return timerRepository.getNewestTimer();
	}

	@Override
	public Timer getTimerByLevel(Integer level) {

		return timerRepository.getTimerByLevel(level);
	}
	
}
