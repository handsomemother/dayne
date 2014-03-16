package cn.dayne.gz.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.dto.ContactGroupDTO;
import cn.dayne.gz.platform.entity.ContactGroup;
import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.query.ContactGroupCriteria;
import cn.dayne.gz.platform.repository.ContactGroupRepository;
import cn.dayne.gz.platform.repository.ContacterRepository;
import cn.dayne.gz.platform.service.ContactGroupService;
import cn.dayne.gz.platform.util.BeanCopyUtil;
import cn.dayne.gz.platform.util.MStringUtil;

/**
 * 用户组服务
 * 
 * @author yeqiuming
 *
 */
@Service
public class ContactGroupServiceImpl extends AbstractApplicationServiceImpl<ContactGroup, Integer>
		implements ContactGroupService {

	/**
	 * 用户组仓存
	 */
	@Resource
	private ContactGroupRepository contactGroupRepository;

	/**
	 * 用户仓存
	 */
	@Resource
	private ContacterRepository contacterRepository;
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.service.ContactGroupService#queryPageByCriteria(cn.ac.iscas.gz.query.ContactGroupCriteria)
	 */
	@Override
	public List<ContactGroupDTO> queryPageByCriteria(ContactGroupCriteria criteria) {
		
		return buildDTO(contactGroupRepository.queryPageByCriteria(criteria));
	}
	
	/**
	 * 构造DTO
	 * 
	 * @param list
	 * @return
	 */
	private List<ContactGroupDTO> buildDTO(List<ContactGroup> list){
		List<ContactGroupDTO> dtoList = new ArrayList<ContactGroupDTO>();
		for(ContactGroup contactGroup:list){
			ContactGroupDTO groupDto = BeanCopyUtil.copy(contactGroup, ContactGroupDTO.class);
			groupDto.setAllContacters(getAllContacters());
			groupDto.setSelectedContacters(getSelectedContacters(contactGroup.getContacterIds()));
			dtoList.add(groupDto);
		}
		return dtoList;
	}
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.service.ContactGroupService#getAllContacters()
	 */
	public Map<Integer,String> getAllContacters(){
		Map<Integer,String> map = new HashMap<Integer,String>();
		List<Contacter> list = contacterRepository.findAll(Contacter.class);
		for(Contacter c:list){
			map.put(c.getId(), c.getName());
		}
		return map;
	}
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.service.ContactGroupService#getSelectedContacters(java.lang.String)
	 */
	public  Map<Integer,String> getSelectedContacters(String ids){
		Map<Integer,String> map = new HashMap<Integer,String>();
		if(!"".equals(ids)){
			/**
			 * 使用hql自动维护组跟人员的关系
			 * 当人员被删除了，新购造的MAP就不会再存在
			 * 当下次更新组时contacterIds重新updatge
			 */
			List<Contacter> list = contacterRepository.queryByIds(MStringUtil.toIntArray(ids, ","));
			for(Contacter c:list){
				map.put(c.getId(), c.getName());
			}
		}
		
		return map;
	}

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.service.ContactGroupService#get(int)
	 */
	@Override
	public ContactGroupDTO getDto(int id) {
		ContactGroupDTO groupDto = BeanCopyUtil.copy(contactGroupRepository.get(id), ContactGroupDTO.class);
		groupDto.setAllContacters(getAllContacters());
		groupDto.setSelectedContacters(getSelectedContacters(groupDto.getContacterIds()));
		return groupDto;
	}

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.service.ContactGroupService#getSelectedContacter(java.lang.Integer)
	 */
	@Override
	public List<Contacter> getSelectedContacters(Integer groudId) {
		List<Contacter> list = new ArrayList<Contacter>();
		String ids = contactGroupRepository.get(groudId).getContacterIds();
		if(!"".equals(ids)){
			list = contacterRepository.queryByIds(MStringUtil.toIntArray(ids, ","));
		}
		return list;
	}

}
