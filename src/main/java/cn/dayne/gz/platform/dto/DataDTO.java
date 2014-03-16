package cn.dayne.gz.platform.dto;

import java.util.List;

/**
 * 校验电信接口时的DTO
 * 
 * @author yeqiuming
 *
 */
public class DataDTO {
	//总条数
	private int total;
	//接口返回的数据列表
	private List<Object> data;
	//返回码，200为正常
	private String code;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
