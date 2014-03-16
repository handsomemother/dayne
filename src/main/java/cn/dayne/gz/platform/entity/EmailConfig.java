package cn.dayne.gz.platform.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="emailConfig")
public class EmailConfig extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//邮件服务器
	private String mailServerHost;
	
	//邮件服务器端口
	private String mailServerPort;
	
	//用于发用系统邮件的邮件地址
	private String sysEmailAddress;
	
	//系统邮件地址密码
	private String emailPassWord;
	
	//备注
	private String remark;

	public String getSysEmailAddress() {
		return sysEmailAddress;
	}

	public void setSysEmailAddress(String sysEmailAddress) {
		this.sysEmailAddress = sysEmailAddress;
	}

	public String getEmailPassWord() {
		return emailPassWord;
	}

	public void setEmailPassWord(String emailPassWord) {
		this.emailPassWord = emailPassWord;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	
}
