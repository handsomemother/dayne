package cn.dayne.gz.platform.dto;

import java.util.Map;

public class ContactGroupDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String groupName;

	private String remark;
	
	private String contacterIds;
	
	private Map<Integer,String>allContacters;
	
	private Map<Integer,String>selectedContacters;
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Map<Integer, String> getAllContacters() {
		return allContacters;
	}

	public void setAllContacters(Map<Integer, String> allContacters) {
		this.allContacters = allContacters;
	}

	public Map<Integer, String> getSelectedContacters() {
		return selectedContacters;
	}

	public void setSelectedContacters(Map<Integer, String> selectedContacters) {
		this.selectedContacters = selectedContacters;
	}

	public String getContacterIds() {
		return contacterIds;
	}

	public void setContacterIds(String contacterIds) {
		this.contacterIds = contacterIds;
	}

}
