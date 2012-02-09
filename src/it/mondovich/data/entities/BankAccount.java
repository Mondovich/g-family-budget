package it.mondovich.data.entities;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Unique(name="IX_NAME", members={"name", "account"})
public class BankAccount {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Key account;
	
	@Persistent
	private Set<Key> persons = new HashSet<Key>();
	
	@Persistent
	private String name;
	
	@Persistent
	private BigDecimal initialValue;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public Long getId() {
		return getKey().getId();
	}
	
	public void setId(Long id) {
		key = KeyFactory.createKey("BankAccount", id);
	}
	
	public Key getAccount() {
		return account;
	}

	public void setAccount(Key account) {
		this.account = account;
	}

	public Set<Key> getPerson() {
		return persons;
	}

	public void setPerson(Set<Key> person) {
		this.persons = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(BigDecimal initialValue) {
		this.initialValue = initialValue;
	}
	
	public List<Long> getOwners(){
		List<Long> result = new ArrayList<Long>();
		for (Key owner : persons) {
			result.add(owner.getId());
		}
		return result;
	}
	
	public void setOwners(List<Long> owners) {
		for (Long ownerId : owners) {
			persons.add(KeyFactory.createKey("Person", ownerId));
		}
	}

}
