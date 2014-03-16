package cn.dayne.gz.platform.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author yeqiuming
 * @date sept 28, 2012
 * @Description 实体基类.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;//主键
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
