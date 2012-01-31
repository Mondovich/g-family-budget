package it.mondovich.actions;

import it.mondovich.data.dao.AccountDAO;
import it.mondovich.data.dao.AccountDAOImpl;
import it.mondovich.data.dao.PayeeDAO;
import it.mondovich.data.dao.PayeeDAOImpl;
import it.mondovich.data.dao.TransactionDAO;
import it.mondovich.data.dao.TransactionDAOImpl;
import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Payee;
import it.mondovich.data.entities.Transaction;
import it.mondovich.data.pojo.TransactionType;
import it.mondovich.util.ContextUtils;

import java.util.Arrays;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TransactionAction extends ActionSupport implements ModelDriven<Transaction>, Preparable {
	
	private static final long serialVersionUID = 2854348442439885643L;
	private Account account = null;
	private Transaction transaction = new Transaction();
	private TransactionDAO transactionDAO = new TransactionDAOImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private PayeeDAO payeeDAO = new PayeeDAOImpl();
	private User user = UserServiceFactory.getUserService().getCurrentUser();
	
	@Override
	public Transaction getModel() {
		return transaction;
	}
	
	@Override
	public void prepare() throws Exception {
		account = accountDAO.findByGmail(user.getEmail());
	}

	public String listTransactions() {
		return SUCCESS;
	}
	
	public String editTransaction() throws Exception {
		Long id = ContextUtils.getLongParameter("id");
		if (id != null) {
			transaction = transactionDAO.findByKey(KeyFactory.createKey("Transaction", id));
		}
		
		prepare();
		
		return SUCCESS;
	}
	
	public String newTransaction() throws Exception {
		return SUCCESS;
	}
	
	public List<TransactionType> getTransactionTypes(){
		return Arrays.asList(TransactionType.values());
	}
	
	public List<Payee> getPayees(){
		return payeeDAO.findAllByAccount(account);
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
