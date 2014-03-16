package cn.dayne.gz.platform.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * @author yeqiuming
 * @date sept 1, 2012
 * @Description 领域对象的Service管理类接口.
 * 
 * 提供了领域对象的简单CRUD方法.
 *
 * @param <T> 领域对象类型
 * @param <PK> 领域对象的主键类型
 * 
 */
public interface AbstractApplicationService<T, PK extends Serializable> {
	// CRUD函数 //
	
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	public T get(final PK id) ;

	/**
	 * 查找所有实体
	 * 
	 * @param clazz
	 * @return
	 */
	public List<T> findAll(Class<T> clazz);

	/**
	 * 保存、更新
	 * 
	 * @param entity
	 */
	public void submit(final T entity);
	
	/**
	 * 按Id删除
	 * @param id
	 */
	public void delete(final PK id) ;
	
    /**
     * 添加
     * @param entity
     */
    public void add(T entity) ;

    /**
     * 更新
     * @param entity
     */
    public void update(T entity) ;

    /**
     * 实体删除
     * 
     * @param entity
     */
    public void delete(T entity);

    /**
     * 查询
     * @param <M>
     * @param clazz
     * @param id
     * @return
     */
    public <M> M get(Class<M> clazz, Long id);

    /**
     * 合并
     * @param entity
     */
    public void merge(Object entity) ;

    /**
     * 持久化指定的对象.
     * @param entity
     */
    public void persist(T entity) ;

    /**
     * 添加对象集合
     * @param entities
     */
    public void addAll(Collection<T> entities);

    /**
     * 删除对象集合
     * @param entities
     */
    public void deleteAll(Collection<T> entities) ;

    /**
     * 更新对象集合
     * @param entities
     */
    public void updateAll(Collection<T> entities);

	/**
	 * flush
	 */
	public void flush() ;

	/**
	 * evict
	 * @param oldEntity
	 */
	public void evict(Object oldEntity);
	
	/**
	 * hql查询
	 * @param hql
	 * @param values
	 * @return
	 */
	public List<T> find(String hql , Object ...values);
}
