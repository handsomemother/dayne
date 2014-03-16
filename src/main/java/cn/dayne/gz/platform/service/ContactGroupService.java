package cn.dayne.gz.platform.service;

import java.util.List;
import java.util.Map;

import cn.dayne.gz.platform.dto.ContactGroupDTO;
import cn.dayne.gz.platform.entity.ContactGroup;
import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.query.ContactGroupCriteria;


/**
 * 
 * 
 * @author yeqiuming
 *
 */
public interface ContactGroupService extends
		AbstractApplicationService<ContactGroup, Integer> {
	
	/**
	 * 根据条件查询用户组
	 * 
	 * @param criteria
	 * @return
	 */
	public List<ContactGroupDTO> queryPageByCriteria(ContactGroupCriteria criteria);
	
	/**
	 * 获取所有联系人并以构造MAP
	 * MAP的ID为联系人ID
	 * MAP的NAME为联系人名称
	 * 
	 * @return
	 */
	public Map<Integer,String> getAllContacters();
	
	/**
	 * 获取已选择联系人并以构造MAP
	 * MAP的ID为联系人ID
	 * MAP的NAME为联系人名称
	 * 
	 * @param ids
	 * @return
	 */
	public Map<Integer,String> getSelectedContacters(String ids);
	
	/**
	 * 查询并构造DTO
	 * 
	 * @param id 组ID
	 * @return
	 */
	public ContactGroupDTO getDto(int id);
	
	/**
	 * 获取用户组用户
	 * @param groudId
	 * @return
	 */
	public List<Contacter> getSelectedContacters(Integer groudId);
}
