package cn.dayne.gz.platform.query;

/**
 * 用户查询条件
 * 
 * @author yeqiuming
 *
 */
public class UserInfoCriteria extends PageSearchCriteria{

	/**
	 * 账号
	 */
	private String userName;

	/**
	 * 启用状态
	 */
	private Boolean status;
	
	/**
	 * 用户名称
	 */
	private String name;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
