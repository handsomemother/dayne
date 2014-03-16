package cn.dayne.gz.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户实体
 * @author yeqiuming
 */
@Entity
@Table(name="user_info")
public class UserInfo extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账号
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

	@Column(unique=true)
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
}
