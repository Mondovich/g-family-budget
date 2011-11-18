package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Person;

public interface AccountDAO extends GenericDAO<Account> {

	public Account findByGmail(String gmail);
	
	public void addPerson(Account account, Person person);
}
