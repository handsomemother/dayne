package cn.dayne.gz.platform.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.dto.ContacterDTO;
import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.query.ContacterCriteria;
import cn.dayne.gz.platform.service.ContacterService;
import cn.dayne.gz.platform.util.BeanCopyUtil;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;

@Controller
@RequestMapping("/contacter.do")
public class ContacterController {
	
	private final static String LIST_PAGE = "jsp/monitor/contact/contacter_list";
	private final static String EDIT_PAGE = "jsp/monitor/contact/contacter_edit";
	private final static String List_ACTION = "/contacter.do?action=list";
	
	private Logger log = Logger.getLogger(ContacterController.class);
	
	@Resource
	private ContacterService contacterService;
	
	/**
	 * 打开添加
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=create", method =  RequestMethod.GET)
	public String initCreate(ModelMap model){
		ContacterDTO contacterDTO = new ContacterDTO();
		model.addAttribute("command", contacterDTO);
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
		model.addAttribute("command", contacterService.get(id));
		return EDIT_PAGE;
	}
	
	/**
	 * 添加联系人
	 * 
	 * @param model
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(params="action=submit", method =  RequestMethod.POST)
	public String submit(ModelMap model,ContacterDTO contacterDTO){
		Contacter contacter = BeanCopyUtil.copy(contacterDTO, Contacter.class);
		if(contacterDTO.getId()==0){
			contacter.setCreateDate(new Date());
		}
		contacterService.submit(contacter);
		return "redirect:" + List_ACTION;
	}
	
	/**
	 * 联系人列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params="action=list", method =  {RequestMethod.GET,RequestMethod.POST})
	public String list(ContacterCriteria criteria,ModelMap model){
		if(criteria==null){
			criteria = new ContacterCriteria();
		}
		List<ContacterDTO> list = contacterService.queryPageByCriteria(criteria);
		model.addAttribute("contacterList", list);
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
		contacterService.delete(id);
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
					contacterService.delete(Integer.parseInt(id));
				}
				HttpServletResponseUtil.writeObject2Response(response, "success");
			} catch (IOException e) {
				log.error(e);
				HttpServletResponseUtil.writeObject2Response(response, "fail");
			}
		}
	}
}
