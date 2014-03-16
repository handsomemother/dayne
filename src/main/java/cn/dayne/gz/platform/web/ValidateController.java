package cn.dayne.gz.platform.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.dto.SourceDTO;
import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.service.SourceService;
import cn.dayne.gz.platform.service.ValidatorService;
import cn.dayne.gz.platform.util.BeanCopyUtil;

@Controller
@RequestMapping("/validate.do")
public class ValidateController {
	
	private final static String RECHECK_PAGE = "jsp/monitor/recheck/source_recheck";
	
	private final static String MONITORING_PAGE = "/monitoring.do";
	
//	private Logger log = Logger.getLogger(ValidateController.class);
	
	@Resource
	private SourceService sourceService;
	
	@Resource
	private ValidatorService validatorService;
	
	@RequestMapping(params="action=recheck", method =  RequestMethod.GET)
	public String recheck(int id,int page,ModelMap model){
	
		Source source = sourceService.get(id);
		//validatorService.ckeck(source);
		
		validatorService.toCheckAndSendEmail(source);
		SourceDTO sourceDTO = BeanCopyUtil.copy(source, SourceDTO.class);
		model.addAttribute("sourceDTO", sourceDTO);
		model.addAttribute("page", page);
		return RECHECK_PAGE;
	}
	
	
	@RequestMapping(params="action=start", method =  RequestMethod.GET)
	public String start(int id,int page,ModelMap model){
		Source source = sourceService.get(id);
		validatorService.toCheckAndSendEmail(source);
		SourceDTO sourceDTO = BeanCopyUtil.copy(source, SourceDTO.class);
		model.addAttribute("sourceDTO", sourceDTO);
		model.addAttribute("page", page);
		
		return "redirect:" + MONITORING_PAGE;
	}
}
