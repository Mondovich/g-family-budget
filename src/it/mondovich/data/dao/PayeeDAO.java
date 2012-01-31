package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Payee;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface PayeeDAO extends GenericDAO<Payee, Key> {
	
	List<Payee> findAllByAccount(Account account);

}
