package cn.dayne.gz.platform.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.dto.UserInfoDTO;
import cn.dayne.gz.platform.entity.UserInfo;
import cn.dayne.gz.platform.query.UserInfoCriteria;
import cn.dayne.gz.platform.service.UserInfoService;
import cn.dayne.gz.platform.util.BeanCopyUtil;
import cn.dayne.gz.platform.util.CommonConstant;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;
import cn.dayne.gz.platform.util.Md5Util;

@Controller
@RequestMapping("/userInfo.do")
public class UserInfoController {
	
	private final static String LIST_PAGE = "jsp/monitor/user/user_list";
	private final static String EDIT_PAGE = "jsp/monitor/user/user_edit";
	private final static String List_ACTION = "/userInfo.do?action=list";
	
	private Logger log = Logger.getLogger(UserInfoController.class);
	
	@Resource
	private UserInfoService userInfoService;
	
	/**
	 * 打开添加
	 * 
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping(params="action=create", method =  RequestMethod.GET)
	public String initCreate(ModelMap model){
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		model.addAttribute("command", userInfoDTO);
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
		UserInfo user = userInfoService.get(id);
		UserInfoDTO dto = BeanCopyUtil.copy(user, UserInfoDTO.class);
		dto.setPassWordVerify(user.getPassWord());
		model.addAttribute("command", dto);
		return EDIT_PAGE;
	}
	
	/**
	 * 添加用户
	 * 
	 * @param model
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(params="action=submit", method =  RequestMethod.POST)
	public String submit(HttpServletRequest request, ModelMap model,UserInfoDTO userInfoDTO){
		
		//判断用户名是否已经存在
		if(userInfoService.isExistUserName(userInfoDTO.getId(),userInfoDTO.getUserName())){
			throw new RuntimeException("账号已存在,请使用其他账号");
		}
		UserInfo userInfo = BeanCopyUtil.copy(userInfoDTO, UserInfo.class);
		if(userInfoDTO.getId()>0){
			UserInfo oldInfo = userInfoService.get(userInfoDTO.getId());
			//修改用户信息时如果密码与原来不一致时修改
			if(!userInfoDTO.getPassWord().equals(oldInfo.getPassWord())){
				userInfo.setPassWord(Md5Util.encrypting(userInfoDTO.getPassWord(), CommonConstant.SALT));
			}
		}else{
			userInfo.setPassWord(Md5Util.encrypting(userInfoDTO.getPassWord(), CommonConstant.SALT));
		}
		userInfoService.submit(userInfo);
			
		return "redirect:" + List_ACTION;
	}
	
	/**
	 * 用户列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params="action=list", method =  {RequestMethod.GET,RequestMethod.POST})
	public String list(UserInfoCriteria criteria,ModelMap model){
		if(criteria==null){
			criteria = new UserInfoCriteria();
		}
		List<UserInfoDTO> list = userInfoService.queryPageByCriteria(criteria);
		model.addAttribute("userInfoList", list);
		model.addAttribute("criteria", criteria);
		return LIST_PAGE;
	}
	
	/**
	 * 用户名校验
	 * 
	 * @param response
	 * @param id
	 * @param userName
	 * @throws IOException
	 */
	@RequestMapping(params="action=userNameValidate", method = {RequestMethod.GET,RequestMethod.POST})
	public void validateUserName(HttpServletResponse response,int id,String userName) throws IOException{
		//判断用户名是否已经存在
		if(userInfoService.isExistUserName(id,userName)){
			HttpServletResponseUtil.writeObject2Response(response,false);
		}else{
			HttpServletResponseUtil.writeObject2Response(response,true);
		}
	}
	
	/**
	 * 启用用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params="action=use", method = RequestMethod.GET)
	public String use(int id){
		UserInfo userInfo = userInfoService.get(id);
		userInfo.setStatus(true);
		userInfoService.update(userInfo);
		return "redirect:" + List_ACTION;
	}
	
	/**
	 * 禁用用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params="action=forbidden", method = RequestMethod.GET)
	public String forbidden(int id){
		UserInfo userInfo = userInfoService.get(id);
		userInfo.setStatus(false);
		userInfoService.update(userInfo);
		return "redirect:" + List_ACTION;
	}
	
	/**
	 * 批量禁用
	 * 
	 * @param ids
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params="action=forbiddenAll", method = {RequestMethod.POST,RequestMethod.GET})
	public void forbiddenAll(HttpServletResponse response,String ids) throws IOException{
		if(!StringUtils.isBlank(ids)){
			try {
				for(String id :ids.split(",")){
					UserInfo userInfo = userInfoService.get(Integer.parseInt(id));
					userInfo.setStatus(false);
					userInfoService.update(userInfo);
				}
				HttpServletResponseUtil.writeObject2Response(response, "success");
			} catch (IOException e) {
				log.error(e);
				HttpServletResponseUtil.writeObject2Response(response, "fail");
			}
		}
	}
}
