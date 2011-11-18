package it.mondovich.data.dao;

import it.mondovich.data.PMFactory;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;

public class AbstractDAO<T> implements
		GenericDAO<T> {

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
	public T findByKey(Key key) {
		try {
			getPersistenceManager();
			return persistenceManager.getObjectById(entityClass, key);
		} finally {
			persistenceManager.close();
		}
	}

	@Override
	public List<T> findAll() {
		try {
			getPersistenceManager();
			List<T> list = (List<T>) persistenceManager.newQuery(entityClass).execute();
			return new ArrayList<T>(list);
		} finally {
			persistenceManager.close();
		}
	}

	@Override
	public void persist(T entity) {
		try {
			getPersistenceManager();
			persistenceManager.makePersistent(entity);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			persistenceManager.close();
		}
	}

	@Override
	public void remove(Key key) {
		try {
			getPersistenceManager();
			persistenceManager.deletePersistent(persistenceManager.getObjectById(entityClass, key));
		} finally {
			persistenceManager.close();
		}
	}

}
