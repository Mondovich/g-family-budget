package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.BankAccount;

import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public class BankAccountDAOImpl extends AbstractDAO<BankAccount, Key> implements
		BankAccountDAO {

	@Override
	public List<BankAccount> findAllByAccount(Account account) {
		try {
			Query q = getPersistenceManager().newQuery(BankAccount.class);
			q.setFilter("account == accountParam");
			q.declareParameters("Account accountParam");
			
			Key accountKey = KeyFactory.createKey("Account", account.getGmail());
			return (List<BankAccount>) getPersistenceManager().detachCopyAll((List<BankAccount>) q.execute(accountKey));
			
		} finally {
			getPersistenceManager().close();
		}
	}

	
}
