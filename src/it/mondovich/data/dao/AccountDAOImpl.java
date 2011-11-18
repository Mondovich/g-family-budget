package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Person;

import javax.jdo.Query;
import javax.persistence.NoResultException;

public class AccountDAOImpl extends AbstractDAO<Account> implements AccountDAO {

	public Account findByGmail(String gmail) {
		try {
			Query q = getPersistenceManager().newQuery(Account.class);
			q.setFilter("gmail == gmailParam");
			q.declareParameters("String gmailParam");
			q.setUnique(true);
			
			return (Account) getPersistenceManager().detachCopy(q.execute(gmail));
			
		} catch (NoResultException e) {
			return null;
		} finally {
			getPersistenceManager().close();
		}
	}
	
	public void addPerson(Account account, Person person) {
		try {
			account = getPersistenceManager().getObjectById(Account.class, account.getId());
			
			account.getPersons().add(person);

			getPersistenceManager().makePersistent(account);
			
		} catch (NoResultException e) {
			return;
		} finally {
			getPersistenceManager().close();
		}
	}
}
