package it.mondovich.data.dao;

import it.mondovich.EMFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AbstractDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	protected Class<T> entityClass;
	
	protected EntityManager entityManager;
	
	public AbstractDAO() {
		this.entityManager = EMFactory.get().createEntityManager();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T findById(ID id) { return entityManager.find(entityClass, id); }

	@Override
	public List<T> findAll() {return entityManager.createQuery("select e from " + entityClass.getCanonicalName() + " e").getResultList();}

	@Override
	public void persist(T entity) { entityManager.persist(entity); }

	@Override
	public void remove(T entity) { entityManager.remove(entity); }

	@Override
	public void close() { entityManager.close(); }
	
	

}
