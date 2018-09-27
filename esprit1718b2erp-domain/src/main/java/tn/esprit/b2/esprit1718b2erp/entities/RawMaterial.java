package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: RawMaterial
 *
 */
@Entity
public class RawMaterial implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private int quantity;
	private Float price;
	
	
	/*@ManyToOne
	private InventoryRawMaterial inventoryRawMaterial;*/
	
	@OneToMany(mappedBy="material")
	private List<InventoryRawMaterial> inventoryRawMaterial;
	
	@ManyToMany(mappedBy="materials")
	private List<Product> products;
	
	@ManyToMany(mappedBy="materials")
	private List<Contact> contacts;
	
	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public List<Contact> getContacts() {
		return contacts;
	}
	

	public RawMaterial(String description, int quantity, Float price) {
		super();
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	private static final long serialVersionUID = 1L;

	public RawMaterial() {
		super();
	} 
	
	
	public RawMaterial(String description, int quantity) {
		super();
		this.description = description;
		this.quantity = quantity;
	}


	public RawMaterial(int id, String description, int quantity) {
		super();
		this.id = id;
		this.description = description;
		this.quantity = quantity;
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
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<InventoryRawMaterial> getInventoryRawMaterial() {
		return inventoryRawMaterial;
	}

	public void setInventoryRawMaterial(List<InventoryRawMaterial> inventoryRawMaterial) {
		this.inventoryRawMaterial = inventoryRawMaterial;
	}

	@Override
	public String toString() {
		return description;
	}



	public List<Product> getProducts() {
		return products;
	}



	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void linkInventoryToThisRawMaterial(List<InventoryRawMaterial> inventoryRawMaterials) {
		this.inventoryRawMaterial = inventoryRawMaterials;
		for (InventoryRawMaterial p : inventoryRawMaterials) {
			p.setMaterial(this);
		}
	}
   
}
