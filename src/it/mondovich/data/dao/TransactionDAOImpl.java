package it.mondovich.data.dao;

import it.mondovich.data.entities.BankAccount;
import it.mondovich.data.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;


public class TransactionDAOImpl extends AbstractDAO<Transaction, Key> implements
		TransactionDAO {

	@Override
	public List<Transaction> findAllByBankAccount(BankAccount bankAccount) {
		try {
			if (bankAccount == null) return new ArrayList<Transaction>();
			
			Query q = getPersistenceManager().newQuery(Transaction.class);
			q.setFilter("bankAccount == bankAccountParam");
			q.declareParameters("Key bankAccountParam");
			
			return (List<Transaction>) getPersistenceManager().detachCopyAll((List<Transaction>) q.execute(bankAccount.getKey()));
			
		} finally {
			getPersistenceManager().close();
		}
	}
	
}
