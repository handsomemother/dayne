package cn.dayne.gz.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.dto.UserInfoDTO;
import cn.dayne.gz.platform.entity.UserInfo;
import cn.dayne.gz.platform.query.UserInfoCriteria;
import cn.dayne.gz.platform.repository.UserInfoRepository;
import cn.dayne.gz.platform.service.UserInfoService;
import cn.dayne.gz.platform.util.BeanCopyUtil;

/**
 * @author yeqiuming
 *
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends AbstractApplicationServiceImpl<UserInfo, Integer>
		implements UserInfoService {

	@Resource
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo queryByCriteria(UserInfoCriteria criteria) {

		return userInfoRepository.queryByCriteria(criteria);
	}

	@Override
	public List<UserInfoDTO> queryPageByCriteria(UserInfoCriteria criteria) {
		
		return buildDTO(userInfoRepository.queryPageByCriteria(criteria));
	}
	
	public List<UserInfoDTO> buildDTO(List<UserInfo> list){
		List<UserInfoDTO> dtoList = new ArrayList<UserInfoDTO>();
		for(UserInfo user:list){
			dtoList.add(BeanCopyUtil.copy(user, UserInfoDTO.class));
		}
		return dtoList;
	}

	@Override
	public boolean isExistUserName(int id, String userName) {
		if(id >0){//修改
			UserInfo oldUserInfo =  this.get(id);
			if(!oldUserInfo.getUserName().equals(userName)){
				UserInfo userInfo = this.getUserInfoByUserName(userName);
				if(userInfo != null){
					return true;
				}
			}
		}else{//新增
			UserInfo userInfo = this.getUserInfoByUserName(userName);
			if(userInfo != null){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据账号查找用户信息
	 * 
	 * @param userName 账号
	 * @return
	 */
	private UserInfo getUserInfoByUserName(String userName){
		UserInfoCriteria criteria = new UserInfoCriteria();
		criteria.setUserName(userName);
		UserInfo userInfo = this.queryByCriteria(criteria);
		return userInfo;
	}
}
