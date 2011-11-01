package it.mondovich.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id);
	 
    List<T> findAll();
 
    void persist(T entity);
 
    void remove(T entity);
    
    void close();
    
}
