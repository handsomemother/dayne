package cn.dayne.gz.platform.dto;


/**
 * 用户实体
 * @author yeqiuming
 */
public class UserInfoDTO extends AbstractDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String passWord;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 用户状态
	 */
	private Boolean status;
	
	/**
	 * 显示状态
	 */
	private String displayStatus;
	
	private String passWordVerify;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDisplayStatus() {
		if(this.getStatus()==true){
			displayStatus = "正常";
		}else{
			displayStatus = "禁用";
		}
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public String getPassWordVerify() {
		return passWordVerify;
	}

	public void setPassWordVerify(String passWordVerify) {
		this.passWordVerify = passWordVerify;
	}
}
