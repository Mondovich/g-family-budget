package it.mondovich.data.dao;

import it.mondovich.data.entities.Person;

import com.google.appengine.api.datastore.Key;

public interface PersonDAO extends GenericDAO<Person, Key> {
	
}
