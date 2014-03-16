/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package cn.dayne.gz.platform.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.dayne.gz.platform.query.PageSearchCriteria;

/**
 * @author yeqiuming
 * @date sept 1, 2012
 * @Description 通用接口.
 * 
 * @param <T> 由继承类指定泛型类型.
 * @param <ID> id的类型.
 */
public interface GenericRepository<T, ID extends Serializable> {
	
	/**
	 * Repository key
	 * 若指定泛型类型一样，需要重写key返回值
	 * 
	 * @return
	 */
	public String getRepositoryKey();
	
    /**
     * 获取指定id的对象.
     * @param id
     * @return
     */
    public T get(ID id);
    
    /**
     * 获取指定类名和id的对象.
     * @param id
     * @return
     */
    public <M> M get(Class<M> clazz, Long id);
    
    /**
     * 获取指定类型M的所有对象.
     * @param <M>
     * @return
     */
    public <M> List<M> findAll(Class<M> clazz);
    
    /**
     * 添加指定的对象.
     * @param entity
     */
    public void add(T entity);
    
    /**
     * 添加对象的集合
     * @param entities
     */
    public void addAll(Collection<T> entities);
    
    /**
     * 修改对象的集合
     * @param entities
     */
    public void updateAll(Collection<T> entities);

    /**
     * 删除对象的集合
     * @param entities
     */
    public void deleteAll(Collection<T> entities);

    /**
     * 持久化指定的对象.
     * @param entity
     */
    public void persist(T entity);

    /**
     * 删除指定的对象.
     * @param entity
     */
    public void deleteAbstractEntity(Object entity);
    
    /**
     * 删除指定的对象.
     * @param entity
     */
    public void delete(T entity);
    
    /**
     * 删除指定id对象.
     * @param entity
     */
    public void delete(ID id);
    
    /**
     * 更新对象的所有信息.
     * @param entity
     */
    public void update(T entity);

    /**
     * 添加或者更新对象.
     * @param entity
     */
    public void addOrUpdate(T entity);

    /**
     * 合并
     * @param newEntity
     */
    public void merge(Object newEntity);

	/**
	 * flush
	 */
	public void flush();

	/**
	 * evict
	 * @param oldEntity
	 */
	public void evict(Object oldEntity);

	/**
	 * session信息.
	 */
	public void printSessionInfo();

	/**
	 * hql查询
	 * @param hql
	 * @param values
	 * @return
	 */
	public List<T> find(String hql , Object ...values);
	
	/**
	 * 分页查询
	 * @param hql 
	 * @param parms
	 * @param currentPageNum
	 * @param rp
	 * @return
	 */
	public List<?> queryPage(final String hql,final List<?> parms,final int currentPageNum,final int rp);
	
	/**
	 * 分页查询
	 * 
	 * @param criteria查询条件
	 * @return
	 */
	public List<?> queryPage(final PageSearchCriteria criteria);
		
}
