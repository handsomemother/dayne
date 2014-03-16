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

import cn.dayne.gz.platform.dto.ContactGroupDTO;
import cn.dayne.gz.platform.entity.ContactGroup;
import cn.dayne.gz.platform.query.ContactGroupCriteria;
import cn.dayne.gz.platform.service.ContactGroupService;
import cn.dayne.gz.platform.util.BeanCopyUtil;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;

@Controller
@RequestMapping("/contactGroup.do")
public class ContactGroupController {
	
	private final static String LIST_PAGE = "jsp/monitor/contact/group_list";
	private final static String EDIT_PAGE = "jsp/monitor/contact/group_edit";
	private final static String List_ACTION = "/contactGroup.do?action=list";
	
	private Logger log = Logger.getLogger(ContactGroupController.class);
	
	@Resource
	private ContactGroupService contactGroupService;
	
	/**
	 * 打开添加
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=create", method =  RequestMethod.GET)
	public String initCreate(ModelMap model){
		ContactGroupDTO contactGroupDTO = new ContactGroupDTO();
		contactGroupDTO.setAllContacters(contactGroupService.getAllContacters());
		model.addAttribute("command", contactGroupDTO);
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
		model.addAttribute("command", contactGroupService.getDto(id));
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
	public String submit(ModelMap model,ContactGroupDTO contactGroupDTO){
		ContactGroup contacterGroup = BeanCopyUtil.copy(contactGroupDTO, ContactGroup.class);
		contactGroupService.submit(contacterGroup);
		return "redirect:" + List_ACTION;
	}
	
	/**
	 * 联系人列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params="action=list", method =  {RequestMethod.GET,RequestMethod.POST})
	public String list(ContactGroupCriteria criteria,ModelMap model){
		if(criteria==null){
			criteria = new ContactGroupCriteria();
		}
		List<ContactGroupDTO> list = contactGroupService.queryPageByCriteria(criteria);
		model.addAttribute("contactGroupList", list);
		model.addAttribute("criteria", criteria);
		return LIST_PAGE;
	}
	

	/**
	 * 删除出数据源信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="action=delete", method = RequestMethod.GET)
	public String delete(int id) throws Exception{
		try{
		contactGroupService.delete(id);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			throw new Exception("不能删除，相关数据源正在使用该联系人组");
		}
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
					contactGroupService.delete(Integer.parseInt(id));
				}
				HttpServletResponseUtil.writeObject2Response(response, "success");
			} catch (IOException e) {
				log.error(e);
				HttpServletResponseUtil.writeObject2Response(response, "fail");
			}
		}
	}
}
