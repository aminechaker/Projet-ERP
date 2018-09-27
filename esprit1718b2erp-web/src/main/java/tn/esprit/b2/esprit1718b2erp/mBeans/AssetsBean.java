package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.services.AssetsServiceLocal;

@ManagedBean
@ViewScoped
public class AssetsBean {

	private boolean showForm;
	private Assets asset ;
	private List<Assets> assets;
	
@EJB
private AssetsServiceLocal assetsServiceLocal;

  @PostConstruct
  private void init(){
	  asset = new Assets();
	  assets = assetsServiceLocal.findAll();
	  showForm = false;
  }
  
  public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}
	public void doAddAsset() {
		
		Assets newAsset = new Assets();
		newAsset.setValue((float) 0);
		newAsset.setType("stock");
		assetsServiceLocal.update(asset);
		showForm = false;
		this.init();

	}
	public void doDeleteAsset() {
		assetsServiceLocal.delete(asset);
		this.init();

	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public Assets getAsset() {
		return asset;
	}

	public void setAsset(Assets asset) {
		this.asset = asset;
	}

	public List<Assets> getAssets() {
		return assets;
	}

	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}

	
  
  
  

}
