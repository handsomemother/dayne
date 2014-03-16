package cn.dayne.gz.platform.repository;

import java.util.List;

import cn.dayne.gz.platform.entity.UserInfo;
import cn.dayne.gz.platform.query.UserInfoCriteria;

/**
 * 用户信息仓存
 * 
 * @author yeqiuming
 *
 */
public interface UserInfoRepository extends GenericRepository<UserInfo, Integer> {
	
	public UserInfo queryByCriteria(UserInfoCriteria criteria);
	
	public List<UserInfo> queryPageByCriteria(UserInfoCriteria criteria);
	
}
