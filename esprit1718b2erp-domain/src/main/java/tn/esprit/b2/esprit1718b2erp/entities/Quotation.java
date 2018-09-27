package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Quotation
 *
 */
@Entity

public class Quotation implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date quotationDate;
	private Date expiringDate;
	private String settelment;
	private Float taxes;
	private Float finalPrice;
	private int quantity;
	private String status;
	private Float unitPrice;
	
	@ManyToOne
	private Contact contact;
	
	@ManyToOne
	private Product product;
	
	
	
	private static final long serialVersionUID = 1L;

	public Quotation() {
		super();
	}  
	
	public Quotation(int id, Date quotationDate) {
		super();
		this.id = id;
		this.quotationDate = quotationDate;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Date getQuotationDate() {
		return this.quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	public String getSettelment() {
		return settelment;
	}

	public void setSettelment(String settelment) {
		this.settelment = settelment;
	}

	public Float getTaxes() {
		return taxes;
	}

	public void setTaxes(Float taxes) {
		this.taxes = taxes;
	}

	public Float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	

	@Override
	public String toString() {
		return "Quotation [id=" + id + ", quotationDate=" + quotationDate + ", expiringDate=" + expiringDate
				+ ", settelment=" + settelment + ", taxes=" + taxes + ", finalPrice=" + finalPrice + ", quantity="
				+ quantity + ", status=" + status + ", contact=" + contact + ", product=" + product + "]";
	}

	public Quotation(Date quotationDate, Date expiringDate, String settelment, Float taxes, Float finalPrice,
			int quantity, String status) {
		super();
		this.quotationDate = quotationDate;
		this.expiringDate = expiringDate;
		this.settelment = settelment;
		this.taxes = taxes;
		this.finalPrice = finalPrice;
		this.quantity = quantity;
		this.status = status;
	}

	

	
	
}
