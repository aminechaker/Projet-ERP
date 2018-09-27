package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Appointment
 *
 */
@Entity

public class Appointment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String sujet;
	private String type;
	private Date appointementDate;

	@ManyToOne
	private User user;

	@ManyToOne
	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	private static final long serialVersionUID = 1L;

	public Appointment() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public Appointment(String sujet, String type, Date appointementDate, User user, Contact contact) {
		super();
		this.sujet = sujet;
		this.type = type;
		this.appointementDate = appointementDate;
		this.user = user;
		this.contact = contact;
	}
	
	public Appointment(String sujet, String type, Date appointementDate) {
		super();
		this.sujet = sujet;
		this.type = type;
		this.appointementDate = appointementDate;
		
	}
	
	
	

	public Appointment(int id, String sujet, String type, Date appointementDate) {
		super();
		this.id = id;
		this.sujet = sujet;
		this.type = type;
		this.appointementDate = appointementDate;
	}

	public Appointment(String sujet, String type, Date appointementDate, Contact contact) {
		this.sujet = sujet;
		this.type = type;
		this.appointementDate = appointementDate;
		this.contact=contact;}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSujet() {
		return this.sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public Date getAppointementDate() {
		return this.appointementDate;
	}

	public void setAppointementDate(Date appointementDate) {
		this.appointementDate = appointementDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", sujet=" + sujet + ", type=" + type + ", appointementDate="
				+ appointementDate + ", user=" + user + ", contact=" + contact + "]";
	}

}
