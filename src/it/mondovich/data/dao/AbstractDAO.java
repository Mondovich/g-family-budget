package it.mondovich.data.dao;

import it.mondovich.EMFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class AbstractDAO<T> implements
		GenericDAO<T> {

	private EntityManager entityManager;
	protected Class<T> entityClass;

	public AbstractDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	@Override
	public T findById(Long id) {
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
			return new ArrayList<T>(list);
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
	public void remove(Long id) {
		try {
			entityManager = EMFactory.get().createEntityManager();
			entityManager.remove(entityManager.find(entityClass, id));
		} finally {
			entityManager.close();
		}
	}

}
