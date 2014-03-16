package cn.dayne.gz.platform.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.dayne.gz.platform.repository.GenericRepository;
import cn.dayne.gz.platform.service.AbstractApplicationService;
import cn.dayne.gz.platform.util.AppContextUtil;
import cn.dayne.gz.platform.util.ReflectionUtils;

/**
 * @author yeqiuming
 * @date sept 1, 2012
 * @Description 领域对象的Service管理类基类.
 * 
 * 提供了领域对象的简单CRUD方法.
 *
 * @param <T> 领域对象类型
 * @param <PK> 领域对象的主键类型
 * public class ArticleApplicationServiceImpl extends AbstractApplicationServiceImpl<Article,Integer> implements ArticleApplicationService{
 *	//getGenericRepository()可以直接拿到该Service对应的Repository
 *	public List<Article> findArticleByTitle(String title){
 *		return getGenericRepository().find("from Article article where title", title);
 *	}
 * } 
 * 
 */
@SuppressWarnings("rawtypes")
@Transactional
public class AbstractApplicationServiceImpl<T, PK extends Serializable> implements AbstractApplicationService<T,PK>{

	protected GenericRepository<T, PK> genericRepository;//默认的泛型Repository成员变量.

	protected Log logger = LogFactory.getLog(getClass());
	
	protected Class<T> entityClass;
	
    private static final String ENTITY_REPOSITORY_INTERFACE = "cn.ac.iscas.gz.repository.GenericRepository";

	private Map<String, GenericRepository> repositoryMgrServiceMap;
	
	/**
	 * 获取默认Repository
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected GenericRepository<T, PK> getGenericRepository() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
		genericRepository = getEntitySpecificManagmentRepository(this.entityClass.getName());
		return genericRepository;
	}

	/**
     * 获取指定类型对应的Repository.
     * 
     */
	private GenericRepository getEntitySpecificManagmentRepository(String entityType) {
    	if(repositoryMgrServiceMap == null) {
    		initializeRepositoryMap();
    	}
    	GenericRepository repository = null;
    	if(repositoryMgrServiceMap.containsKey(entityType)) {
    		repository = repositoryMgrServiceMap.get(entityType);
    	}else {
    		logger.error("spring 容器中不存在："+entityType+"实体的repository类");
        	Assert.notNull(repository, "至少有default的repository类！");
    	}
    	return repository;
    }
    
    private void initializeRepositoryMap(){
		Class entityType = null;
		try {
			entityType = Class.forName(ENTITY_REPOSITORY_INTERFACE);
		} catch (ClassNotFoundException e) {
			logger.error("初始化实体服务类的时候，无法找到类型:" + ENTITY_REPOSITORY_INTERFACE, e);
		}
    	@SuppressWarnings("unchecked")
		Map<?, GenericRepository> repositoryMap = AppContextUtil.getBeansOfType(entityType);
    	Assert.notEmpty(repositoryMap, "至少有一个repository类！");
    	
		repositoryMgrServiceMap = new HashMap<String, GenericRepository>();
		for(GenericRepository repository : repositoryMap.values()) {
			repositoryMgrServiceMap.put(repository.getRepositoryKey(), repository);
		}
    }
	
	//CRUD函数 
    
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.sftsp.application.facade.AbstractApplicationService#get(java.io.Serializable)
	 */
	@Transactional(readOnly = true)
	@Override
	public T get(final PK id) {
		return getGenericRepository().get(id);
	}

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.sftsp.application.facade.AbstractApplicationService#findAll(java.lang.Class)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<T> findAll(Class<T> clazz) {
		return getGenericRepository().findAll(clazz);
	}
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.sftsp.application.facade.AbstractApplicationService#submit(java.lang.Object)
	 */
	@Override
	public void submit(final T entity) {
		getGenericRepository().addOrUpdate(entity);
	}
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.sftsp.application.facade.AbstractApplicationService#delete(java.io.Serializable)
	 */
	@Override
	public void delete(final PK id) {
		getGenericRepository().delete(id);
	}
	
	 /*
     * (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#add(java.lang.Object)
     */
	@Override
    public void add(T entity) {
    	getGenericRepository().add(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#update(java.lang.Object)
     */
    @Override
    public void update(T entity) {
    	getGenericRepository().update(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#delete(java.lang.Object)
     */
    @Override
    public void delete(T entity) {
        if (entity != null) {
        	getGenericRepository().delete(entity);
        }
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#get(java.lang.Class, java.lang.Long)
     */
    @Transactional(readOnly = true)
    @Override
    public <M> M get(Class<M> clazz, Long id) {
        return (M) getGenericRepository().get(clazz, id);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#merge(java.lang.Object)
     */
    @Override
    public void merge(Object entity) {
    	getGenericRepository().merge(entity);
    }

    /* (non-Javadoc)
     * @see cn.ac.iscas.gz.sftsp.application.facade.AbstractApplicationService#persist(java.lang.Object)
     */
    @Override
    public void persist(T entity) {
    	getGenericRepository().persist(entity);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#addAll(java.util.List)
     */
    @Override
    public void addAll(Collection<T> entities) {
    	getGenericRepository().addAll(entities);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#deleteAll(java.util.Collection)
     */
    @Override
    public void deleteAll(Collection<T> entities) {
    	getGenericRepository().deleteAll(entities);
    }

    /* (non-Javadoc)
     * @see com.nfschina.qo.infrastructure.GenericRepository#updateAll(java.util.Collection)
     */
    @Override
    public void updateAll(Collection<T> entities) {
    	getGenericRepository().updateAll(entities);
    }

	/* (non-Javadoc)
	 * @see com.nfschina.qo.infrastructure.GenericRepository#flush()
	 */
	@Override
	public void flush() {
		getGenericRepository().flush();
	}

	/* (non-Javadoc)
	 * @see com.nfschina.qo.infrastructure.compact.GenericRepository#evict(com.nfschina.qo.infrastructure.compact.domain.AbstractEntity)
	 */
	@Override
	public void evict(Object oldEntity) {
		getGenericRepository().evict(oldEntity);
	}
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.sftsp.application.facade.AbstractApplicationService#find(java.lang.String, java.lang.Object[])
	 */
	public List<T> find(String hql , Object ...values){
		return getGenericRepository().find(hql, values);
	}
}
