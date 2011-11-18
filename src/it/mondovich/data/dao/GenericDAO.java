package it.mondovich.data.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface GenericDAO<T> {

	T findByKey(Key key);
	 
    List<T> findAll();
 
    void persist(T entity);
 
    void remove(Key key);
    
}
