package it.mondovich.data.dao;

import it.mondovich.data.entities.Account;

public interface AccountDAO extends GenericDAO<Account, String> {

	public Account findByGmail(String gmail);
	
//	public void addPerson(Account account, Person person);
}
