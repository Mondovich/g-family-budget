package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Payee;

import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PayeeDAOImpl extends AbstractDAO<Payee, Key> implements PayeeDAO {

	@Override
	public List<Payee> findAllByAccount(Account account) {
		try {
			Query q = getPersistenceManager().newQuery(Payee.class);
			q.setFilter("account == accountParam");
			q.declareParameters("Account accountParam");
			
			Key accountKey = KeyFactory.createKey("Account", account.getGmail());
			return (List<Payee>) getPersistenceManager().detachCopyAll((List<Payee>) q.execute(accountKey));
			
		} finally {
			getPersistenceManager().close();
		}
	}

}
