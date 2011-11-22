package it.mondovich.actions;

import it.mondovich.data.dao.AccountDAO;
import it.mondovich.data.dao.AccountDAOImpl;
import it.mondovich.data.dao.BankAccountDAO;
import it.mondovich.data.dao.BankAccountDAOImpl;
import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.BankAccount;
import it.mondovich.data.entities.Person;
import it.mondovich.util.ContextUtils;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BankAccountAction extends ActionSupport implements ModelDriven<BankAccount>  {
	
	private static Log log = LogFactory.getLog(FamilyAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BankAccount bankAccount = new BankAccount();
	private List<BankAccount> listOfBankAccount;
	private BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private User user = UserServiceFactory.getUserService().getCurrentUser();
	
	@Override
	public BankAccount getModel() {
		return bankAccount;
	}
	

	@Override
	public String execute() throws Exception {
		fillListOfBankAccount();
		
		return SUCCESS;
	}
	
	public String newBankAccount() throws Exception {
		Account account = accountDAO.findByGmail(user.getEmail());
		if (ContextUtils.getParameter("id") != null) {
			Key keyBankAccount = KeyFactory.createKey("BankAccount", Long.parseLong(ContextUtils.getParameter("id")));
			bankAccount.setKey(keyBankAccount);
			bankAccountDAO.save(bankAccount);
		} else {
			bankAccount.setPerson(KeyFactory.createKey("Person", 1l));
			bankAccount = bankAccountDAO.save(bankAccount);
//			account.getPersons().add(bankAccount.getKey());
//			accountDAO.save(account);
		}
		
		fillListOfBankAccount();
		
		bankAccount = new BankAccount();
		
		return SUCCESS;
	}
	
	public String editBankAccount() throws Exception {
		return SUCCESS;
	}
	
	public String deleteBankAccount() throws Exception {
		return SUCCESS;
	}
	

	public BankAccount getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}


	public List<BankAccount> getListOfBankAccount() {
		return listOfBankAccount;
	}


	public void setListOfBankAccount(List<BankAccount> listOfBankAccount) {
		this.listOfBankAccount = listOfBankAccount;
	}
	
	private void fillListOfBankAccount(){
		listOfBankAccount = bankAccountDAO.findAll();
	}

}
