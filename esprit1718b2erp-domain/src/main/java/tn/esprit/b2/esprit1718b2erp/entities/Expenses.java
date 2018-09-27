package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * Entity implementation class for Entity: Expenses
 *
 */
@Entity

public class Expenses implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private Float value;
	
	@ManyToOne
	private User user;
	
	private static final long serialVersionUID = 1L;

	public Expenses() {
		super();
	}  
	
	public Expenses( String type, Float value) {
		super();
		
		this.type = type;
		this.value = value;
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Expenses [id=" + id + ", type=" + type + ", value=" + value + ", user=" + user + "]";
	}
   
}
