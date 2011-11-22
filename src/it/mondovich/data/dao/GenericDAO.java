package it.mondovich.data.dao;

import java.util.List;

public interface GenericDAO<T, K> {

	T findByKey(K key);
	 
    List<T> findAll();
 
    T save(T entity);
 
    void delete(K key);
    
}
