package it.mondovich.data.dao;

import it.mondovich.data.PMFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;

public class AbstractDAO<T, K> implements
		GenericDAO<T, K> {

	private PersistenceManager persistenceManager;
	protected Class<T> entityClass;

	public AbstractDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}
	
	protected PersistenceManager getPersistenceManager() {
		if (persistenceManager == null || persistenceManager.isClosed()) {
			persistenceManager = PMFactory.get().getPersistenceManager();
		}
		return persistenceManager;
	}

	@Override
	public T findByKey(K key) {
		try {
			getPersistenceManager();
			T entity = persistenceManager.getObjectById(entityClass, key);
			return persistenceManager.detachCopy(entity);
		} finally {
			persistenceManager.close();
		}
	}

	@Override
	public List<T> findAll() {
		try {
			getPersistenceManager();
			List<T> list = (List<T>) persistenceManager.newQuery(entityClass).execute();
			return (List<T>) persistenceManager.detachCopyAll(list);
		} finally {
			persistenceManager.close();
		}
	}

	@Override
	public T save(T entity) {
		try {
			getPersistenceManager();
			entity = persistenceManager.makePersistent(entity);
		} catch (JDOUserException e) {
			throw e;
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			persistenceManager.close();
		}
		return entity;
	}

	@Override
	public void delete(K key) {
		try {
			getPersistenceManager();
			persistenceManager.deletePersistent(persistenceManager.getObjectById(entityClass, key));
		} finally {
			persistenceManager.close();
		}
	}

}
