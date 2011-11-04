package it.mondovich.data.dao;

import it.mondovich.EMFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class AbstractDAO<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	private EntityManager entityManager;
	protected Class<T> entityClass;

	public AbstractDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	@Override
	public T findById(ID id) {
		try {
			entityManager = EMFactory.get().createEntityManager();
			return entityManager.find(entityClass, id);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<T> findAll() {
		try {
			entityManager = EMFactory.get().createEntityManager();
			List<T> list = entityManager.createQuery("select e from " + entityClass.getSimpleName() + " e").getResultList();
			List<T> result = new ArrayList<T>();
			for (T t : list) {
				result.add(t);
			}
			return result;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void persist(T entity) {
		try {
			entityManager = EMFactory.get().createEntityManager();
			entityManager.persist(entity);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void remove(T entity) {
		try {
			entityManager = EMFactory.get().createEntityManager();
			entityManager.remove(entity);
		} finally {
			entityManager.close();
		}
	}

}
