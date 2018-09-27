package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@Entity
public class Administrator extends User implements Serializable {

	
	private String type;
	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}   
	
	
	
	



	/*public Administrator(int code, String name, String login, String password, String mobile, String adresse,
			String email, Expenses expenses, Benefits benefits, List<Claims> claims, List<Events> envents,
			List<Appointment> appointments, List<Quotation> quotations, List<Assets> assets) {
		super(code, name, login, password, mobile, adresse, email, expenses, benefits, claims, envents, appointments,
				quotations, assets);
		// TODO Auto-generated constructor stub
	}*/







	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "Administrator [type=" + type + "]";
	}
	
   
}
