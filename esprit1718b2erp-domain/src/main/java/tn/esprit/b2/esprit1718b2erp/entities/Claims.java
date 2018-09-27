package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Claims
 *
 */
@Entity

public class Claims implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date claimDate;
	private String status;
	
	@ManyToOne
	private Contact contact;
    
	
	
	
	

	public Claims(int id, String description, Date claimDate, String status, Contact contact) {
		super();
		this.id = id;
		this.description = description;
		this.claimDate = claimDate;
		this.status = status;
		this.contact = contact;
	}
	
	
	public Claims(String description, Date claimDate, String status, Contact contact) {
		super();
		this.description = description;
		this.claimDate = claimDate;
		this.status = status;
		this.contact = contact;
	}


	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	private static final long serialVersionUID = 1L;

	public Claims() {
		super();
	}   
	public Claims(String status) {
		this.status=status;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Date getClaimDate() {
		return this.claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	



	
   
}
