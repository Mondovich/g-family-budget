package it.mondovich.actions;

import it.mondovich.data.entities.Payee;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PayeeAction extends ActionSupport implements ModelDriven<Payee>,
		Preparable {
	
	private static final long serialVersionUID = -8620766690684277862L;
	private Payee payee = new Payee();
	
	@Override
	public Payee getModel() {
		return payee;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	

}
