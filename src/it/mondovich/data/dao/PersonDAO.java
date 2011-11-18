package it.mondovich.data.dao;

import it.mondovich.data.entities.Person;

public interface PersonDAO extends GenericDAO<Person> {
	
	void persist(Person entity);

}
