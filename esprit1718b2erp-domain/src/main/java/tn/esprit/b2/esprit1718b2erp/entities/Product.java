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
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private int quantity;
	private Float price;
	private int nbVente;

	@OneToMany(mappedBy="product" , cascade = CascadeType.MERGE)
	private List<Quotation> quotations;
	
	@OneToMany(mappedBy = "product")
	private List<InventoryProduct> inventoryProduct;

	@ManyToMany
	private List<RawMaterial> materials;

	@OneToMany(mappedBy = "products", cascade = CascadeType.MERGE)
	private List<Production> productions;

	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}

	public Product(int id, String description, int quantity, Float price) {
		super();
		this.id = id;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	

	public Product(String description, int quantity, Float price, int nbVente) {
		super();
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.nbVente = nbVente;
	}

	
	public int getNbVente() {
		return nbVente;
	}

	public void setNbVente(int nbVente) {
		this.nbVente = nbVente;
	}

	public Product(String description, Float price) {
		super();
		this.description = description;
		this.quantity = 0;
		this.price = price;
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

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	

	public List<Production> getProductions() {
		return productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public List<InventoryProduct> getInventoryProduct() {
		return inventoryProduct;
	}

	public void setInventoryProduct(List<InventoryProduct> inventoryProduct) {
		this.inventoryProduct = inventoryProduct;
	}

	
	
	

	@Override
	public String toString() {
		return description;
	}

	public List<RawMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(List<RawMaterial> materials) {
		this.materials = materials;
	}
	

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public void linkProductionToThisProduct(List<Production> productions) {
		this.productions = productions;
		for (Production p : productions) {
			p.setProducts(this);
		}
	}
	public void linkQuotationToThisProduct(List<Quotation> quotations) {
		this.quotations = quotations;
		for (Quotation q : quotations) {
			q.setProduct(this);
		}
	}
	public void linkInventoryToThisProduct(List<InventoryProduct> inventoryProducts) {
		this.inventoryProduct = inventoryProducts;
		for (InventoryProduct p : inventoryProducts) {
			p.setProduct(this);
		}
	}
	
}
