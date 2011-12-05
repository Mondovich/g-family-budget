package it.mondovich.actions;

import it.mondovich.util.ContextUtils;

import com.opensymphony.xwork2.ActionSupport;

public class MovementAction extends ActionSupport {

	public String listMovements() {
		Long id = ContextUtils.getLongParameter("id");
		return SUCCESS;
	}
}
