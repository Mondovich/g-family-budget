package it.mondovich.data.entities;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Account {
	
	@PrimaryKey
	@Persistent
	private String gmail;
	
	@Persistent
	private Set<Key> persons = new HashSet<Key>();
	
	public Account() {
		super();
	}

	public Account(String gmail) {
		super();
		this.gmail = gmail;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public Set<Key> getPersons() {
		return persons;
	}

	public void setPersons(Set<Key> persons) {
		this.persons = persons;
	}

}
