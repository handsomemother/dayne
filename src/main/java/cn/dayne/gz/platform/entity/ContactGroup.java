package cn.dayne.gz.platform.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contactGroup")
public class ContactGroup extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String groupName;

	private String remark;
	
	private String contacterIds;
	
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

	public String getContacterIds() {
		return contacterIds;
	}

	public void setContacterIds(String contacterIds) {
		this.contacterIds = contacterIds;
	}

}
