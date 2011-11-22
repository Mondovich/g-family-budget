package it.mondovich.data.entities;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Person {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Key account;
	
	@Persistent
	private Set<Key> bankAccounts = new HashSet<Key>();
	
	@Persistent
	private String firstName;
	
	@Persistent
	private String lastName;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getAccount() {
		return account;
	}

	public void setAccount(Key account) {
		this.account = account;
	}

	public Set<Key> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<Key> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
