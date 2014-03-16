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

import cn.dayne.gz.platform.dto.SourceDTO;
import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.query.SourceCriteria;
import cn.dayne.gz.platform.service.SourceService;
import cn.dayne.gz.platform.util.BeanCopyUtil;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;

@Controller
@RequestMapping("/source.do")
public class SourceController {
	
	private final static String LIST_PAGE = "jsp/monitor/source/source_list";
	private final static String EDIT_PAGE = "jsp/monitor/source/source_edit";
	private final static String VIEW_PAGE = "jsp/monitor/source/source_view";
	private final static String List_ACTION = "/source.do?action=list";
	
	private Logger log = Logger.getLogger(SourceController.class);
	
	@Resource
	private SourceService sourceService;
	
	/**
	 * 打开添加
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=create", method =  RequestMethod.GET)
	public String initCreate(ModelMap model){
		SourceDTO sourceDTO = new SourceDTO();
		//新建时默认状态为正常
		sourceDTO.setStatus(1);
		sourceDTO.setContactGroupMap(sourceService.buildGroupMap());
		model.addAttribute("command", sourceDTO);
		return EDIT_PAGE;
	}
	
	/**
	 * 打开修改
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=update", method =  RequestMethod.GET)
	public String initUpldate(ModelMap model,Integer id){
		SourceDTO sourceDTO = BeanCopyUtil.copy(sourceService.get(id),SourceDTO.class);
		sourceDTO.setContactGroupMap(sourceService.buildGroupMap());
		model.addAttribute("command", sourceDTO);
		return EDIT_PAGE;
	}
	
	/**
	 * 查看信息
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=view", method =  RequestMethod.GET)
	public String view(ModelMap model,Integer id){
		SourceDTO sourceDTO = BeanCopyUtil.copy(sourceService.get(id),SourceDTO.class);
		sourceDTO.setContactGroupMap(sourceService.buildGroupMap());
		model.addAttribute("source", sourceDTO);
		return VIEW_PAGE;
	}
	
	/**
	 * 添加数据源
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=submit", method =  RequestMethod.POST)
	public String submit(ModelMap model,SourceDTO sourceDTO){
		Source source = BeanCopyUtil.copy(sourceDTO, Source.class);
		sourceService.submit(source);
		return "redirect:" + List_ACTION;
	}
	
	/**
	 * 数据源信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params="action=list", method =  {RequestMethod.GET,RequestMethod.POST})
	public String list(SourceCriteria criteria,ModelMap model){
		if(criteria==null){
			criteria = new SourceCriteria();
		}
//		criteria.setRp(5);
		List<SourceDTO> list =  sourceService.queryPageByCriteria(criteria);
		model.addAttribute("sourceList", list);
		model.addAttribute("criteria", criteria);
		return LIST_PAGE;
	}
	
	/**
	 * 删除出数据源信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params="action=delete", method = RequestMethod.GET)
	public String delete(int id){
		sourceService.delete(id);
		return "redirect:" + List_ACTION;
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
					sourceService.delete(Integer.parseInt(id));
				}
				HttpServletResponseUtil.writeObject2Response(response, "success");
			} catch (IOException e) {
				log.error(e);
				HttpServletResponseUtil.writeObject2Response(response, "fail");
			}
		}
	}
	
}
