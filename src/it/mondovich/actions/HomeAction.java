package it.mondovich.actions;

import it.mondovich.data.dao.AccountDAO;
import it.mondovich.data.dao.AccountDAOImpl;
import it.mondovich.data.dao.BankAccountDAO;
import it.mondovich.data.dao.BankAccountDAOImpl;
import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.dao.TransactionDAO;
import it.mondovich.data.dao.TransactionDAOImpl;
import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.BankAccount;
import it.mondovich.data.entities.Person;
import it.mondovich.data.entities.Transaction;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class HomeAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = 514118340757332026L;
	private static Log log = LogFactory.getLog(FamilyAction.class);
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	private User user = UserServiceFactory.getUserService().getCurrentUser();
	private Account account = accountDAO.findByGmail(user.getEmail());
	private BankAccount bankAccount;
	
	private List<Person> listOfPerson;
	private List<BankAccount> listOfBankAccount;
	private List<Transaction> listOfTransactions;
	private PersonDAO personDAO = new PersonDAOImpl();
	private BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	private TransactionDAO transactionDAO = new TransactionDAOImpl();

	@Override
	public void prepare() throws Exception {
		if (account == null) {
			accountDAO.save(new Account(user.getEmail()));
			account = accountDAO.findByGmail(user.getEmail());
		}
		listOfPerson = personDAO.findAllByAccount(account);
		listOfBankAccount = bankAccountDAO.findAllByAccount(account);
		listOfTransactions = transactionDAO.findAllByBankAccount(bankAccount);
	}
	
	public List<Person> getListOfPerson() {
		return listOfPerson;
	}

	public void setListOfPerson(List<Person> listOfPerson) {
		this.listOfPerson = listOfPerson;
	}
	
	public List<BankAccount> getListOfBankAccount() {
		return listOfBankAccount;
	}

	public void setListOfBankAccount(List<BankAccount> listOfBankAccount) {
		this.listOfBankAccount = listOfBankAccount;
	}

}
