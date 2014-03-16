package cn.dayne.gz.platform.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.dto.SourceLogDTO;
import cn.dayne.gz.platform.query.SourceLogCriteria;
import cn.dayne.gz.platform.service.SourceLogService;
import cn.dayne.gz.platform.util.BeanCopyUtil;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;

@Controller
@RequestMapping("/sourceLog.do")
public class SourceLogController {
	
	private final static String LIST_VIEW = "jsp/monitor/sourcelog/sourcelog_view";
	private final static String LIST_PAGE = "jsp/monitor/sourcelog/sourcelog_list";
	
	private Logger log = Logger.getLogger(SourceLogController.class);
	
	@Resource
	private SourceLogService sourceLogService;
	
	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params="action=view", method =  {RequestMethod.GET,RequestMethod.POST})
	public String view(int id,ModelMap model){
		model.addAttribute("sourceLog", BeanCopyUtil.copy(sourceLogService.get(id), SourceLogDTO.class));
		return LIST_VIEW;
	}
	
	/**
	 * 数据源信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params="action=list", method =  {RequestMethod.GET,RequestMethod.POST})
	public String list(SourceLogCriteria criteria,ModelMap model){
		if(criteria==null){
			criteria = new SourceLogCriteria();
		}
//		criteria.setRp(5);
		List<SourceLogDTO> list =  sourceLogService.queryPageByCriteria(criteria);
		model.addAttribute("sourceLogList", list);
		model.addAttribute("criteria", criteria);
		return LIST_PAGE;
	}
	
	/**
	 * 批量删除数据源信息
	 * 
	 * @param ids
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params="action=deleteAll", method = {RequestMethod.POST,RequestMethod.GET})
	public void deleteAll(HttpServletResponse response,String ids) throws IOException{
		if(!StringUtils.isBlank(ids)){
			try {
				for(String id :ids.split(",")){
					sourceLogService.delete(Integer.parseInt(id));
				}
				HttpServletResponseUtil.writeObject2Response(response, "success");
			} catch (IOException e) {
				log.error(e);
				HttpServletResponseUtil.writeObject2Response(response, "fail");
			}
		}
	}
	
}
