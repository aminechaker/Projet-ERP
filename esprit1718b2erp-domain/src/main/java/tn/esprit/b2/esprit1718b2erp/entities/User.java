package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
// @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private String name;
	private String login;
	private String password;
	private String mobile;
	private String adresse;
	private String email;

	@OneToMany(mappedBy = "user")
	private List<Benefits> benefits;

	@OneToMany(mappedBy = "user")
	private List<Expenses> expenses;

	



	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	private List<Appointment> appointments;

	

	@OneToMany(mappedBy = "user",cascade = CascadeType.MERGE)
	private List<Assets> assets;

	
	public User(String name, String login, String password, String mobile, String adresse, String email) {
		super();
		this.name = name;
		this.login = login;
		this.password = password;
		this.mobile = mobile;
		this.adresse = adresse;
		this.email = email;
	}

	public User(int code, String name, String login, String password, String mobile, String adresse, String email,
			Expenses expenses, Benefits benefits, 
			List<Appointment> appointments, List<Assets> assets, List<Expenses> expensesL ,List<Benefits> benefitsL) {
		super();
		this.code = code;
		this.name = name;
		this.login = login;
		this.password = password;
		this.mobile = mobile;
		this.adresse = adresse;
		this.email = email;
		this.appointments = appointments;
		this.assets = assets;
		this.expenses = expensesL;
		this.benefits = benefitsL;
		
		
		

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public User() {
		super();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<Expenses> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expenses> expensesL) {
		this.expenses = expensesL;
	}

	public List<Benefits> getBenefits() {
		return benefits;
	}

	public void setBenefits(List<Benefits> benefits) {
		this.benefits = benefits;
	}

	
	
	public List<Assets> getAssets() {
		return getAssets();
	}

	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}



	@Override
	public String toString() {
		return "User [code=" + code + ", name=" + name + ", login=" + login + ", password=" + password + ", mobile="
				+ mobile + ", adresse=" + adresse + ", email=" + email + ", expenses=" + expenses + ", benefits="
				+ benefits + ", appointments=" + appointments + ", assets=" + assets + "]";
	}

	

	
	

	

}
