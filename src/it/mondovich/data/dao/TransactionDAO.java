package it.mondovich.data.dao;

import it.mondovich.data.entities.Transaction;

import com.google.appengine.api.datastore.Key;

public interface TransactionDAO extends GenericDAO<Transaction, Key> {

}
