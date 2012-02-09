package it.mondovich.actions;

import it.mondovich.data.dao.BankAccountDAO;
import it.mondovich.data.dao.BankAccountDAOImpl;
import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.entities.BankAccount;
import it.mondovich.data.entities.Person;
import it.mondovich.exceptions.UniqueConstraintException;
import it.mondovich.util.ContextUtils;
import it.mondovich.util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jdo.JDOObjectNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
	private PersonDAO personDAO = new PersonDAOImpl();
	
	@Override
	public BankAccount getModel() {
		return bankAccount;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String save() throws Exception {
		bankAccount.setAccount(Helper.getAccountKey());
		if (bankAccount.getPerson().isEmpty()) {
			addFieldError("owners", getText("bankaccount.validation.owners"));
			return INPUT;
		}
		if (ContextUtils.getParameter("id") != null) {
			bankAccountDAO.save(bankAccount);
		} else {
			try {
				bankAccount = bankAccountDAO.save(bankAccount);
			} catch (UniqueConstraintException e) {
				for (String field : e.getFields()) {
					addFieldError(field, getText("bankaccount.validation.unique", new String[] {field}));
				}
				return INPUT;
			}
		}
		
		prepare();
		
		bankAccount = new BankAccount();
		
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		Long id = ContextUtils.getLongParameter("id");
		if (id != null) {
			bankAccount = bankAccountDAO.findByKey(KeyFactory.createKey("BankAccount", id));
		}
		
		prepare();
		
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		String forward = SUCCESS;
		Long id = null;
		try {
			id = ContextUtils.getLongParameter("id");
		} catch (Exception e) {}
		
		if (id == null) {
			addActionError(getText("bankaccount.validation.id.null"));
			forward = INPUT;
		} else {
			Key keyBankAccount = KeyFactory.createKey("BankAccount", id);
			try {
				bankAccountDAO.delete(keyBankAccount);
			} catch (JDOObjectNotFoundException e) {
				addActionError(getText("bankaccount.validation.id", id.toString()));
				forward = INPUT;
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
		listOfBankAccount = bankAccountDAO.findAllByAccount(Helper.getAccount());
		listOfPersons = personDAO.findAllByAccount(Helper.getAccount());
		listOfBankAccountOwner = new ArrayList<Person>();
	}
	
}
