package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.BankAccount;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface BankAccountDAO extends GenericDAO<BankAccount, Key> {

	List<BankAccount> findAllByAccount(Account account);
	
}
