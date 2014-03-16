/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package cn.dayne.gz.platform.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.dayne.gz.platform.query.PageSearchCriteria;
import cn.dayne.gz.platform.repository.GenericRepository;
import cn.dayne.gz.platform.util.ReflectionUtils;

/**
 * @author yeqiuming
 * @date sept 1, 2012
 * @Description 通用DB接口的实现.
 * 
 * 提供了领域对象的简单CRUD方法.
 *
 * @param <T> 领域对象类型
 * @param <PK> 领域对象的主键类型
 * 
 * public class ArticleRepositoryHibernate extends GenericRepositoryHibernate<Article, Integer> {
 * 
 * }
 * 
 */
public class GenericRepositoryHibernate<T, ID extends Serializable> implements GenericRepository<T, ID> {
	
    protected Log log;

    @Resource
    HibernateTemplate hibernateTemp;

    /**
     * @return the hibernateTemp
     */
    public HibernateTemplate getHibernateTemp() {
        return hibernateTemp;
    }

    public void setHibernateTemp(HibernateTemplate hibernateTemp) {
        this.hibernateTemp = hibernateTemp;
    }

    private Class<T> persistentClass;

    /**
     * 返回类型.
     * @author liyin 2010-11-22
     * @return
     */
    private Class<T> getPersistentClass() {
        return persistentClass;
    }
    
    @SuppressWarnings("unchecked")
    public GenericRepositoryHibernate() {
        boolean parameterized = false;
        try {
            this.persistentClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            parameterized = true;
        } catch (ClassCastException ex) {
        	ex.printStackTrace();
        }

        if (parameterized) {
            Class<T> repoClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            log = LogFactory.getLog(repoClass.getClass());
            log.info("GenericRepositoryHibernate's ParameterizedType is " + repoClass);
        } else {
            log = LogFactory.getLog(this.getClass());
            log.info("GenericRepositoryHibernate is used alone!");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#add(java.lang.Object)
     */
    public void add(T entity) {
        hibernateTemp.save(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#update(java.lang.Object)
     */
    public void update(T entity) {
        hibernateTemp.update(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#delete(java.lang.Object)
     */
    public void delete(T entity) {
        if (entity != null) {
            hibernateTemp.delete(entity);
        }
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#delete(java.io.Serializable)
     */
    public void delete(ID id) {
        T object = this.get(id);
        delete(object);
    }

    /*
     * (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#get(java.io.Serializable)
     */
    public T get(ID id) {
        return (T) hibernateTemp.get(getPersistentClass(), id);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#get(java.lang.Class, java.lang.Long)
     */
	@Override
    public <M> M get(Class<M> clazz, Long id) {
        return (M) hibernateTemp.get(clazz, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.nfschina.qo.infrastructure.GenericRepository#
     * addOrUpdate(java.lang.Object)
     */
    public void addOrUpdate(T entity) {
        hibernateTemp.saveOrUpdate(entity);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#findAll(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
	public <M> List<M> findAll(Class<M> clazz) {
        return hibernateTemp.find("from " + clazz.getName());
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#deleteAbstractEntity(com.nfschina.qo.infrastructure.domain.AbstractEntity)
     */
    public void deleteAbstractEntity(Object entity) {
        hibernateTemp.delete(entity);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#merge(java.lang.Object)
     */
    @Override
    public void merge(Object entity) {
        hibernateTemp.merge(entity);
    }

    @Override
    public void persist(T entity) {
        hibernateTemp.persist(entity);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#addAll(java.util.List)
     */
    @Override
    public void addAll(Collection<T> entities) {
        hibernateTemp.saveOrUpdateAll(entities);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#deleteAll(java.util.Collection)
     */
    @Override
    public void deleteAll(Collection<T> entities) {
        hibernateTemp.deleteAll(entities);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#updateAll(java.util.Collection)
     */
    @Override
    public void updateAll(Collection<T> entities) {
        hibernateTemp.saveOrUpdateAll(entities);
    }

	/* (non-Javadoc)
	 * @see com.nfschina.qo.infrastructure.GenericRepository#flush()
	 */
	@Override
	public void flush() {
		hibernateTemp.flush();
	}

	/* (non-Javadoc)
	 * @see com.nfschina.qo.infrastructure.compact.GenericRepository#evict(com.nfschina.qo.infrastructure.compact.domain.AbstractEntity)
	 */
	@Override
	public void evict(Object oldEntity) {
		hibernateTemp.evict(oldEntity);
	}

	/* (non-Javadoc)
	 * @see com.nfschina.qo.infrastructure.compact.GenericRepository#printSessionInfo()
	 */
	@Override
	public void printSessionInfo() {
		log.info("当前的Session信息为：" + hibernateTemp.getSessionFactory().getCurrentSession().toString());		
	}
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.cn.ac.iscas.gz.sftsp.db.GenericRepository#find(java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql , Object ...values){
		return hibernateTemp.find(hql, values);
	}

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.sftsp.domain.repository.GenericRepository#getRepositoryKey()
	 */
	@Override
	public String getRepositoryKey() {
		return ReflectionUtils.getSuperClassGenricType(this.getClass()).getName();
	}
	
	
	public List<?> queryPage(final String hql,final List<?> parms,final int currentPageNum,final int rp){
		@SuppressWarnings("rawtypes")
		List<?> list = hibernateTemp.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < parms.size(); i++) {
					query.setParameter(i,parms.get(i));
				}
				query.setFirstResult((currentPageNum-1)*rp);
				query.setMaxResults(rp);
				return query.list();
			}
		});
		return list;
	}
	

	public List<?> queryPage(final PageSearchCriteria criteria){
		@SuppressWarnings("rawtypes")
		List<?> list = hibernateTemp.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				Query query = session.createQuery(criteria.getHql());
				for (int i = 0; i < criteria.getParms().size(); i++) {
					query.setParameter(i,criteria.getParms().get(i));
				}
				//计算总页数
				if(!StringUtils.isBlank(criteria.getTotalNumHql())){
					Query totalNumQuery = session.createQuery(criteria.getTotalNumHql());
					for (int i = 0; i < criteria.getParms().size(); i++) {
						totalNumQuery.setParameter(i,criteria.getParms().get(i));
					}
					criteria.setTotalNum((Long) totalNumQuery.uniqueResult());
				}else{
					criteria.setTotalNum((long) query.list().size());
				}
				query.setFirstResult((criteria.getPage()-1)*criteria.getRp());
				query.setMaxResults(criteria.getRp());
				return query.list();
			}
		});
		return list;
	}

}
