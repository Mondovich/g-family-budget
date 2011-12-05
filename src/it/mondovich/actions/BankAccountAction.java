package it.mondovich.actions;

import it.mondovich.data.dao.AccountDAO;
import it.mondovich.data.dao.AccountDAOImpl;
import it.mondovich.data.dao.BankAccountDAO;
import it.mondovich.data.dao.BankAccountDAOImpl;
import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.BankAccount;
import it.mondovich.data.entities.Person;
import it.mondovich.util.ContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jdo.JDOObjectNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BankAccountAction extends ActionSupport implements ModelDriven<BankAccount>, Preparable  {
	
	private static Log log = LogFactory.getLog(FamilyAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BankAccount bankAccount = new BankAccount();
	private List<BankAccount> listOfBankAccount;
	private List<Person> listOfPersons;
	private List<Person> listOfBankAccountOwner;
	private BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private PersonDAO personDAO = new PersonDAOImpl();
	private User user = UserServiceFactory.getUserService().getCurrentUser();
	private List<Long> owners = new ArrayList<Long>();
	
	@Override
	public BankAccount getModel() {
		return bankAccount;
	}
	

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String newBankAccount() throws Exception {
		Account account = accountDAO.findByGmail(user.getEmail());
		if (ContextUtils.getParameter("id") != null) {
			Key keyBankAccount = KeyFactory.createKey("BankAccount", Long.parseLong(ContextUtils.getParameter("id")));
			bankAccount.setKey(keyBankAccount);
			bankAccountDAO.save(bankAccount);
		} else {
			for (Long ownersId : owners) {
				bankAccount.getPerson().add(KeyFactory.createKey("Person", ownersId));
			}
			bankAccount = bankAccountDAO.save(bankAccount);
//			account.getPersons().add(bankAccount.getKey());
//			accountDAO.save(account);
		}
		
		prepare();
		
		bankAccount = new BankAccount();
		
		return SUCCESS;
	}
	
	public String editBankAccount() throws Exception {
		return SUCCESS;
	}
	
	public String deleteBankAccount() throws Exception {
		String forward = SUCCESS;
		Long id = null;
		try {
			id = ContextUtils.getLongParameter("id");
		} catch (Exception e) {}
		
		if (id == null) {
			addActionError("Id is null!");
			forward = ERROR;
		} else {
			Key keyBankAccount = KeyFactory.createKey("BankAccount", id);
			try {
				bankAccountDAO.delete(keyBankAccount);
			} catch (JDOObjectNotFoundException e) {
				addActionError("Bank Account with id = 3 doesn't exists");
				forward = ERROR;
			}
		}
		
		prepare();
		
		return forward;
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
	
	public List<Person> getListOfPersons() {
		return listOfPersons;
	}


	public void setListOfPersons(List<Person> listOfPersons) {
		this.listOfPersons = listOfPersons;
	}


	public String getOwnersList(Set<Key> persons) {
		List<String> owners = new ArrayList<String>();
		for (Key key : persons) {
			Person person = personDAO.findByKey(key);
			owners.add(person.getFirstName() + " " + person.getLastName());
		}
		return StringUtils.join(owners, ", ");
	}
	
	public void addPerson(String id) {
		bankAccount.getPerson().add(KeyFactory.createKey("Person", Long.parseLong(id)));
	}
	
	public String addBankAccountOwner() throws Exception {
		String forward = SUCCESS;
		
		Long id = null;
		try {
			id = ContextUtils.getLongParameter("id");
		} catch (Exception e) {}
		if (id == null) {
			addActionError("Id is null!");
			forward = ERROR;
		} else {
			Key keyBankAccountOwner = KeyFactory.createKey("Person", id);
			listOfBankAccountOwner.add(personDAO.findByKey(keyBankAccountOwner));
		}
		
		prepare();
		
		return forward;
	}


	@Override
	public void prepare() throws Exception {
		listOfBankAccount = bankAccountDAO.findAll();
		listOfPersons = personDAO.findAll();
		listOfBankAccountOwner = new ArrayList<Person>();
	}


	public List<Long> getOwners() {
		return owners;
	}


	public void setOwners(List<Long> owners) {
		this.owners = owners;
	}
	
	
	
}
