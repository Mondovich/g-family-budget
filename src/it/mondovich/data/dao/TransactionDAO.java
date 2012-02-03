package it.mondovich.data.dao;

import it.mondovich.data.entities.BankAccount;
import it.mondovich.data.entities.Transaction;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface TransactionDAO extends GenericDAO<Transaction, Key> {

	List<Transaction> findAllByBankAccount(BankAccount bankAccount);

}
