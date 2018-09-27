package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: InventoryRawMaterial
 *
 */
@Entity
public class InventoryRawMaterial implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date dateInventory;
	private int quantity;
	
	@ManyToOne
	private Employee employee;
	
	/*@OneToMany(mappedBy="inventoryRawMaterial")
	private List<RawMaterial> rawMaterial;*/
	@ManyToOne
	private RawMaterial material;
	
	private static final long serialVersionUID = 1L;

	public InventoryRawMaterial() {
		super();
	} 
	


	public InventoryRawMaterial(String description, Date dateInventory, int quantity) {
		super();
		this.description = description;
		this.dateInventory = dateInventory;
		this.quantity = quantity;
	}



	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public RawMaterial getMaterial() {
		return material;
	}

	public void setMaterial(RawMaterial material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "InventoryRawMaterial [quantity=" + quantity + ", material=" + material + "]";
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

	public InventoryRawMaterial(Date dateInventory, int quantity) {
		super();
		this.dateInventory = dateInventory;
		this.quantity = quantity;
	}
	
   
}
