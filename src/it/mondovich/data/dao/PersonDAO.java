package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Person;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface PersonDAO extends GenericDAO<Person, Key> {

	List<Person> findAllByAccount(Account account);
	
}
