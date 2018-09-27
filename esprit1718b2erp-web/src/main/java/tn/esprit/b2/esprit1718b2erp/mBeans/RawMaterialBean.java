package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceLocal;

@ManagedBean
@ViewScoped
public class RawMaterialBean {
	private boolean showForm;
	private int idProvider;
	private Contact provider;
	private RawMaterial rawMaterial;
	private List<Contact> providers ;
	private List<RawMaterial> materials;
	@EJB
	private RawMaterialServiceLocal rawMaterialServiceLocal;
	@EJB
	private ContactServiceLocal contactServiceLocal;
	@EJB
	private AssignementServiceLocal assignementServiceLocal;

	
	@PostConstruct
	private void init() {
		rawMaterial = new RawMaterial();
		providers = contactServiceLocal.getAllProviders();
		materials = rawMaterialServiceLocal.findAll();
		showForm = false;
	}
	 public void addMessage(String summary, String detail) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}

	
	public void doAddRawMaterial() {
		if (idProvider != 0) {
			System.out.println(idProvider);
			provider = contactServiceLocal.find(idProvider);
			rawMaterialServiceLocal.update(rawMaterial);
			RawMaterial rawMaterial2 = rawMaterialServiceLocal.findRawMaterialByDescription(rawMaterial.getDescription());
			assignementServiceLocal.assignRawMaterialToContact(rawMaterial2, provider);
			addMessage("Confirmation", "Raw Material added Successfully");
			this.init();
		} else {
			System.out.println("error");
		}

	}
	public void doDeleteRawMaterial(){
		rawMaterialServiceLocal.delete(rawMaterial);
		this.init();
	}
	public boolean isShowForm() {
		return showForm;
	}
	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	public int getIdProvider() {
		return idProvider;
	}
	public void setIdProvider(int idProvider) {
		this.idProvider = idProvider;
	}
	public Contact getProvider() {
		return provider;
	}
	public void setProvider(Contact provider) {
		this.provider = provider;
	}
	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
	public List<Contact> getProviders() {
		return providers;
	}
	public void setProviders(List<Contact> providers) {
		this.providers = providers;
	}
	public List<RawMaterial> getMaterials() {
		return materials;
	}
	public void setMaterials(List<RawMaterial> materials) {
		this.materials = materials;
	}

}
