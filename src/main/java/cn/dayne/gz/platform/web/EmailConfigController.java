package cn.dayne.gz.platform.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.entity.EmailConfig;
import cn.dayne.gz.platform.service.EmailConfigService;

@Controller
@RequestMapping("/emailConfig.do")
public class EmailConfigController {
	
	private final static String EDIT_PAGE = "jsp/monitor/email/emailconfig_edit";
	
//	private Logger log = Logger.getLogger(EmailConfigController.class);
	
	@Resource
	private EmailConfigService emailConfigService;
	
	/**
	 * 打开修改
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=update", method =  RequestMethod.GET)
	public String initUpldate(ModelMap model,Integer id){
		model.addAttribute("command", emailConfigService.getEmailConfig());
		return EDIT_PAGE;
	}
	
	/**
	 * 修改配置
	 * 
	 * @param model
	 * @param userInfo
	 * @return
	 * @throws IOException 
	 */
//	@RequestMapping(params="action=submit", method =  RequestMethod.POST)
//	public void submit(HttpServletResponse response,EmailConfig emailConfig) throws IOException{
//		try {
//			emailConfigService.submit(emailConfig);
//			HttpServletResponseUtil.writeObject2Response(response, "修改成功");
//		} catch (IOException e) {
//			e.printStackTrace();
//			HttpServletResponseUtil.writeObject2Response(response, "修改失败");
//			log.error(e);
//		}
//	}
	
	
	/**
	 * 修改配置
	 * 
	 * @param model
	 * @param userInfo
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params="action=submit", method =  RequestMethod.POST)
	public String submit(HttpServletResponse response,EmailConfig emailConfig,ModelMap model){
		emailConfigService.submit(emailConfig);
		model.addAttribute("command", emailConfigService.getEmailConfig());
		model.addAttribute("result", "success");
		return EDIT_PAGE;
	}
	
}
