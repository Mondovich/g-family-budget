package it.mondovich.data.dao;

import javax.persistence.EntityManager;

import it.mondovich.data.entities.Person;

import com.google.appengine.api.datastore.Key;

public class PersonDAOImpl extends AbstractDAO<Person, Key> implements
		PersonDAO {

}
