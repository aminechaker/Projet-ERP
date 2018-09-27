package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceLocal;

@ManagedBean
@ViewScoped
public class ProductBean {

	private boolean showForm;
	private Product product;
	private List<String> materialsID;
	private List<Product> products;
	private List<RawMaterial> materials;
	@EJB
	private ProductServiceLocal productServiceLocal;
	@EJB
	private RawMaterialServiceLocal rawMaterialServiceLocal;
	@EJB
	private AssignementServiceLocal assignementServiceLocal;

	@PostConstruct
	private void init() {
		product = new Product();
		materials = rawMaterialServiceLocal.findAll();
		products = productServiceLocal.findAll();
		showForm = false;
	}

	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}

	public void doAddProduct() {
		int z = 0;
		if (materialsID.size() != 0) {
			for (Product p : products) {
				if (product.getId() == p.getId()) {
					product.setQuantity(product.getQuantity());
					productServiceLocal.update(product);
					Product product2 = productServiceLocal.findProductByDescription(product.getDescription());
					for (String x : materialsID) {
						RawMaterial m = rawMaterialServiceLocal.find(Integer.parseInt(x));
						assignementServiceLocal.assignRawMaterialToProduct(m, product2);
						m.setQuantity(m.getQuantity() - 1);
						rawMaterialServiceLocal.update(m);
					}
				}
				else {
					z++;
				}
			}
			if (z != 0) {
				product.setQuantity(0);
				productServiceLocal.update(product);
				Product product2 = productServiceLocal.findProductByDescription(product.getDescription());
				for (String x : materialsID) {
					RawMaterial m = rawMaterialServiceLocal.find(Integer.parseInt(x));
					assignementServiceLocal.assignRawMaterialToProduct(m, product2);
					m.setQuantity(m.getQuantity() - 1);
					rawMaterialServiceLocal.update(m);
				}
			}
		} else {
			System.out.println("error");
		}
		this.init();
	}

	public void doDeleteProduct() {
		productServiceLocal.delete(product);
		this.init();
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public List<RawMaterial> getMaterials() {
		return materials;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<String> getMaterialsID() {
		return materialsID;
	}

	public void setMaterialsID(List<String> materialsID) {
		this.materialsID = materialsID;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<RawMaterial> getMaterials2() {
		return materials;
	}

	public void setMaterials(List<RawMaterial> materials) {
		this.materials = materials;
	}

}
