package cn.dayne.gz.platform.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.task.HighTask;
import cn.dayne.gz.platform.task.LowTask;
import cn.dayne.gz.platform.task.MiddleTask;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;

@Controller
@RequestMapping("/task.do")
public class TaskController {
	
	private final static String TASK_VIEW = "jsp/monitor/task/task";
	
	@Resource
	private HighTask highTask;
	
	@Resource
	private MiddleTask middleTask;
	
	@Resource
	private LowTask lowTask;
	
	@RequestMapping(method = RequestMethod.GET)
	public String toTask(ModelMap model) throws IOException{
		return TASK_VIEW;
	}
	
	@RequestMapping(params="action=high", method = RequestMethod.GET)
	public void doHighTask(HttpServletResponse response,ModelMap model) throws IOException{
		try{
			highTask.scheduleJob();
			HttpServletResponseUtil.writeObject2Response(response, "已发送高频任务执行命令");
		}catch (Exception e) {
			HttpServletResponseUtil.writeObject2Response(response, "高频任务执行命令发送失败");
		}
	}
	
	@RequestMapping(params="action=middle", method = RequestMethod.GET)
	public void doMiddleTask(HttpServletResponse response,ModelMap model) throws IOException{
		try{
			middleTask.scheduleJob();
			HttpServletResponseUtil.writeObject2Response(response, "已发送中频任务执行命令");
		}catch (Exception e) {
			HttpServletResponseUtil.writeObject2Response(response, "中频任务执行命令发送失败");
		}
	}
	
	@RequestMapping(params="action=low", method = RequestMethod.GET)
	public void doLowTask(HttpServletResponse response,ModelMap model) throws IOException{
		try{
			lowTask.scheduleJob();
			HttpServletResponseUtil.writeObject2Response(response, "已发送低频任务执行命令");
		}catch (Exception e) {
			HttpServletResponseUtil.writeObject2Response(response, "低频任务执行命令发送失败");
		}
	}
	
	
}
