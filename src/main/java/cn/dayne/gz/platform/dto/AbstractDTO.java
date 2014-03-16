package cn.dayne.gz.platform.dto;

import java.io.Serializable;

/**
 * @author yeqiuming
 * @date sept 28, 2012
 * @Description 基础DTO.
 */
public class AbstractDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;//主键

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
