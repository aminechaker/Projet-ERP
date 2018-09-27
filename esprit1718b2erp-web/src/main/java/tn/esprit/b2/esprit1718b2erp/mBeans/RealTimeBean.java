package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import tn.esprit.b2.esprit1718b2erp.entities.Quotes;
import tn.esprit.b2.esprit1718b2erp.services.MarketDataJson;

@ManagedBean
@RequestScoped
public class RealTimeBean {
	
	@EJB
	MarketDataJson marketData;
	
	private  List<Quotes> quote;
	
	/*@PostConstruct
	public void init(){
		quote=marketData.chargerData();
	}*/

	public List<Quotes> getQuote() {
		return marketData.chargerData();
	}

	public void setQuote(List<Quotes> quote) {
		this.quote = quote;
	}

}
