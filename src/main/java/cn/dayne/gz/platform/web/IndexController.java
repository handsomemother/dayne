package cn.dayne.gz.platform.web;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index.do")
public class IndexController {
	
	private final static String INDEX_PAGE = "jsp/monitor/monitor_main";
	
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, ParseException {

		 return INDEX_PAGE;
	}

}
