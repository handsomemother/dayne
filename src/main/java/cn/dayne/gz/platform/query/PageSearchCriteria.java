package cn.dayne.gz.platform.query;

import java.util.List;

/**
 * 基础分页查询类
 * 
 * @author yeqiuming
 * @date
 * 
 */
public class PageSearchCriteria {

	// 总记录数
	private Long totalNum;

	// 当前页数
	private Integer page = 1;

	// 总共页数
	private Long totalPage;

	// 每页大小. rp为results per page的缩写
	private Integer rp = 15;

	// 查询语句
	private String hql;

	// hql中的参数
	private List<?> parms;
	
	// 计算totalNum的hql[优化项,选用]
	private String totalNumHql;

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getPage() {
		if(page == null){
			page = 1;
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public Integer getRp() {
		return rp;
	}

	public void setRp(Integer rp) {
		this.rp = rp;
	}

	public List<?> getParms() {
		return parms;
	}

	public void setParms(List<?> parms) {
		this.parms = parms;
	}
	
	public String getTotalNumHql() {
		return totalNumHql;
	}

	public void setTotalNumHql(String totalNumHql) {
		this.totalNumHql = totalNumHql;
	}

	public Long getTotalPage() {
		if (getTotalNum() % getRp() == 0) {
			totalPage = getTotalNum() / getRp();
		} else {
			totalPage = getTotalNum() / getRp() + 1;
		}
		return totalPage;
	}

}