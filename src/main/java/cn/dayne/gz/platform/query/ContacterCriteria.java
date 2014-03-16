package cn.dayne.gz.platform.query;

/**
 * 
 * @author yeqiuming
 *
 */
public class ContacterCriteria extends PageSearchCriteria{
	
	/**
	 * 联系人名称
	 */
	private String name;
	
	/**
	 * email
	 */
	private String email;
	
	/**
	 * 电话号码
	 */
	private String tel;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
