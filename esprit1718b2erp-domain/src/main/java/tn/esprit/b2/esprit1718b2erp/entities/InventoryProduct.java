	package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: InventoryProduct
 *
 */
@Entity
public class InventoryProduct implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date dateInventory;
	private int quantity;
	private Float price;
	
	@ManyToOne
	private Employee employee;
	/*@OneToMany(mappedBy="inventoryProduct")
	private List<Product> product;*/
	
	@ManyToOne
	private Product product;
	
	private static final long serialVersionUID = 1L;

	public InventoryProduct() {
		super();
	}   
	
	public InventoryProduct(String description, Date dateInventory, int quantity, Float price) {
		super();
		this.description = description;
		this.dateInventory = dateInventory;
		this.quantity = quantity;
		this.price = price;
	}


	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}   
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "InventoryProduct [quantity=" + quantity + ", price=" + price + ", product=" + product + "]";
	}

	public InventoryProduct(int quantity) {
		super();
		this.quantity = quantity;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDateInventory() {
		return dateInventory;
	}


	public void setDateInventory(Date dateInventory) {
		this.dateInventory = dateInventory;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public InventoryProduct(Date dateInventory, int quantity, Float price) {
		super();
		this.dateInventory = dateInventory;
		this.quantity = quantity;
		this.price = price;
	}
	
	
   
}
