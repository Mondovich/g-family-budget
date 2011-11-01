package it.mondovich.data.dao;

import com.google.appengine.api.datastore.Key;

import it.mondovich.data.entities.Person;

public interface PersonDAO extends GenericDAO<Person, Key> {

}
