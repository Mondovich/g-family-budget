package it.mondovich.exceptions;

import javax.jdo.JDOUserException;

public class UniqueConstraintException extends JDOUserException {

	private static final long serialVersionUID = -2342675021432651388L;
	
	private String[] fields;
	
	public UniqueConstraintException(String[] fields) {
		super();
		this.fields = fields;
	}

	public String[] getFields() {
		return fields;
	}

	public void setField(String[] fields) {
		this.fields = fields;
	}

}
