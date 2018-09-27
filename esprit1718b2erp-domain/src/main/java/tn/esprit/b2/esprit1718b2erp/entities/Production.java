package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Production
 *
 */
@Entity

public class Production implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date dateProduction;
	private int quantite;
	private String productionStatus;

	@ManyToOne
	private Product products;
	@ManyToOne
	private Employee employee;

	private static final long serialVersionUID = 1L;

	public Production() {
		super();
	}

	public Production(String description, Date dateProduction, int quantite, String productionStatus) {
		super();
		this.description = description;
		this.dateProduction = dateProduction;
		this.quantite = quantite;
		this.productionStatus = productionStatus;
	}

	public Production(int id, Date dateProduction, int quantite, String productionStatus) {
		super();
		this.id = id;
		this.dateProduction = dateProduction;
		this.quantite = quantite;
		this.productionStatus = productionStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Production(int id, Date dateProduction, int quantite, Product products) {
		super();
		this.id = id;
		this.dateProduction = dateProduction;
		this.quantite = quantite;
		this.products = products;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateProduction() {
		return this.dateProduction;
	}

	public void setDateProduction(Date dateProduction) {
		this.dateProduction = dateProduction;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	

	@Override
	public String toString() {
		return description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductionStatus() {
		return productionStatus;
	}

	public void setProductionStatus(String productionStatus) {
		this.productionStatus = productionStatus;
	}
	
}
