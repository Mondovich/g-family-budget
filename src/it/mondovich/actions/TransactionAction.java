package it.mondovich.actions;

import it.mondovich.data.dao.BankAccountDAO;
import it.mondovich.data.dao.BankAccountDAOImpl;
import it.mondovich.data.entities.BankAccount;
import it.mondovich.util.ContextUtils;

import com.google.appengine.api.datastore.KeyFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class TransactionAction extends ActionSupport implements Preparable {
	
	private Long id;
	private BankAccount bankAccount = new BankAccount();
	private BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();

	public String listTransactions() {
		return SUCCESS;
	}
	
	@Override
	public void prepare() throws Exception {
		id = ContextUtils.getLongParameter("id");
		if (id != null) {
			setBankAccount(bankAccountDAO.findByKey(KeyFactory.createKey("BankAccount", id)));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	
}
