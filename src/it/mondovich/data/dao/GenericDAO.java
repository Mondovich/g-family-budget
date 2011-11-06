package it.mondovich.data.dao;

import java.util.List;

public interface GenericDAO<T> {

	T findById(Long id);
	 
    List<T> findAll();
 
    void persist(T entity);
 
    void remove(Long id);
    
}
