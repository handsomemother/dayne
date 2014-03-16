package cn.dayne.gz.platform.service;

import java.util.List;

import cn.dayne.gz.platform.dto.UserInfoDTO;
import cn.dayne.gz.platform.entity.UserInfo;
import cn.dayne.gz.platform.query.UserInfoCriteria;


/**
 * 用户信息服务接口
 * 
 * @author yeqiuming
 *
 */
public interface UserInfoService extends
		AbstractApplicationService<UserInfo, Integer> {
	
	public UserInfo queryByCriteria(UserInfoCriteria criteria);
	
	public List<UserInfoDTO> queryPageByCriteria(UserInfoCriteria criteria);
	
	public boolean isExistUserName(int id,String userName);
}
