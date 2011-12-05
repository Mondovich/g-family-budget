package it.mondovich.data.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.apache.commons.lang.StringUtils;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class BankAccount {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
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

}
