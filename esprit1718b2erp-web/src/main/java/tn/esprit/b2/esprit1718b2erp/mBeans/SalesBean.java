package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Quotation;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.QuotationServiceLocal;

@ManagedBean
@ViewScoped
public class SalesBean {
	private boolean showForm;

	private Quotation quotation;
	private String idContact;
	private String idProduct;

	private List<Contact> contacts;
	private List<Quotation> quotations;
	private List<Quotation> quotations1;
	private List<Product> products;

	@EJB
	ContactServiceLocal contactServiceLocal;
	@EJB
	ProductServiceLocal productServiceLocal;
	@EJB
	QuotationServiceLocal quotationServiceLocal;
	@EJB
	AssignementServiceLocal assignementServiceLocal;

	@PostConstruct
	private void init() {
		quotations1 = new ArrayList<>();
		quotations = new ArrayList<>();
		quotation = new Quotation();
		quotations = quotationServiceLocal.findAll();
		for (Quotation x : quotations) {
			if (x.getStatus().equalsIgnoreCase("Quotation")) {
				quotations1.add(x);
			}
		}
		products = productServiceLocal.findAll();
		contacts = contactServiceLocal.findAll();
		showForm = false;
	}

	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}

	public void doUpdate() {
		quotationServiceLocal.update(quotation);
		this.init();
	}

	public void doAddSale() {

		int x = Integer.parseInt(idContact);
		int y = Integer.parseInt(idProduct);
		Contact contact = contactServiceLocal.find(x);
		Product product = productServiceLocal.find(y);
		Float finalPrice;
		Float unitPrice = product.getPrice();
		if (quotation.getTaxes() == 0.07F) {
			finalPrice = (unitPrice + (unitPrice * 0.07F)) * quotation.getQuantity();
			quotation.setTaxes(0.07F);
			quotation.setFinalPrice(finalPrice);
			quotation.setStatus("Quotation");
			quotation.setUnitPrice(unitPrice);
		}
		if (quotation.getTaxes() == 0.13F) {
			finalPrice = (unitPrice + (unitPrice * 0.13F)) * quotation.getQuantity();
			quotation.setTaxes(0.13F);
			quotation.setFinalPrice(finalPrice);
			quotation.setStatus("Quotation");
			quotation.setUnitPrice(unitPrice);

		}
		if (quotation.getTaxes() == 0.19F) {
			finalPrice = (unitPrice + (unitPrice * 0.19F)) * quotation.getQuantity();
			quotation.setTaxes(0.19F);
			quotation.setFinalPrice(finalPrice);
			quotation.setStatus("Quotation");
			quotation.setUnitPrice(unitPrice);

		}
		Date sysdate = new Date();
		quotation.setQuotationDate(sysdate);
		int w=product.getNbVente();
		product.setNbVente(w+1);
		assignementServiceLocal.assignQuototaionToProductAndContact(quotation, contact, product);
		this.init();
		// System.out.println(materialsID.size());
	}

	public void doDeleteSale() {
		quotationServiceLocal.delete(quotation);
		this.init();
	}

	public void doValidate() {
		quotation.setStatus("Bill");
		quotationServiceLocal.update(quotation);
		this.init();
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	public String getIdContact() {
		return idContact;
	}

	public void setIdContact(String idContact) {
		this.idContact = idContact;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Quotation> getQuotations1() {
		return quotations1;
	}

	public void setQuotations1(List<Quotation> quotations1) {
		this.quotations1 = quotations1;
	}

}
