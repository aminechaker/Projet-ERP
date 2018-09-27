package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String adresse;
	private String mobile;
	private String email;
	private String activitySector;
	private String region;
	private String type;

	/*
	 * @OneToMany(mappedBy="contact") private List<Sale> sales;
	 */

	@OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE)
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE)
	private List<Claims> claims;

	@OneToMany(mappedBy = "contacts", cascade = CascadeType.MERGE)
	private List<Project> projects;
	
	@OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE)
	private List<Quotation> quotations;
	
	@ManyToMany
	private List<RawMaterial> materials;

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public List<RawMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(List<RawMaterial> materials) {
		this.materials = materials;
	}

	public Contact( String name, String adresse, String mobile, String email, String activitySector,
			String region, String type) {
		super();
		
		this.name = name;
		this.adresse = adresse;
		this.mobile = mobile;
		this.email = email;
		this.activitySector = activitySector;
		this.region = region;
		this.type = type;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Claims> getClaims() {
		return claims;
	}

	public void setClaims(List<Claims> claims) {
		this.claims = claims;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivitySector() {
		return activitySector;
	}

	public void setActivitySector(String activitySector) {
		this.activitySector = activitySector;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	private static final long serialVersionUID = 1L;

	public Contact() {
		super();
	}

	public void linkAppointmentToContact(List<Appointment> appointments) {
		this.appointments = appointments;
		for (Appointment a : appointments) {
			a.setContact(this);
		}
	}
	
	public void linkClaimsToContact(List<Claims> claims) {
		this.claims = claims;
		for (Claims a : claims) {
			a.setContact(this);
		}
	}
	
	public void linkQuotationToContact(List<Quotation> quotations) {
		this.quotations = quotations;
		for (Quotation a : quotations) {
			a.setContact(this);
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
	public void linkProjectToContact(List<Project> projects) {
		this.projects = projects;
		for (Project p : projects) {
			p.setContacts(this);
		}
	}

}
