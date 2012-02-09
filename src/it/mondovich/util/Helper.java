package it.mondovich.util;

import it.mondovich.data.dao.AccountDAO;
import it.mondovich.data.dao.AccountDAOImpl;
import it.mondovich.data.entities.Account;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

public class Helper {
	
	private static User user = UserServiceFactory.getUserService().getCurrentUser();
	private static AccountDAO accountDAO = new AccountDAOImpl();
	private static Account account;
	
	static {
		account = accountDAO.findByGmail(user.getEmail());		
	}
	
	public static Account getAccount() {
		return account;
	}

	public static Key getAccountKey() {
		return KeyFactory.createKey("Account", account.getGmail());
	}
}
