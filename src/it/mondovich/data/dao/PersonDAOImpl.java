package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Person;

import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PersonDAOImpl extends AbstractDAO<Person, Key> implements
		PersonDAO {

	@Override
	public List<Person> findAllByAccount(Account account) {
		try {
			Query q = getPersistenceManager().newQuery(Person.class);
			q.setFilter("account == accountParam");
			q.declareParameters("Account accountParam");
			
			Key accountKey = KeyFactory.createKey("Account", account.getGmail());
			return (List<Person>) getPersistenceManager().detachCopyAll((List<Person>) q.execute(accountKey));
			
		} finally {
			getPersistenceManager().close();
		}
	}

	
}
