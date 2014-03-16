package cn.dayne.gz.platform.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ContacterDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String email;
	
	private String tel;

	private Date createDate;
	
	private String displayDate;
	
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDisplayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		displayDate = sdf.format(this.getCreateDate());
		return displayDate;
	}
	
}
