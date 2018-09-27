package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;

import tn.esprit.b2.esprit1718b2erp.services.ContactServiceLocal;

@ManagedBean  
@ViewScoped
public class ContactBean {
	private boolean showForm;
	private Contact contact;
	private List<Contact > contacts;

	
	@EJB
	private ContactServiceLocal contactServiceLocal;

	@PostConstruct
	private void init() {
		contact = new Contact();
		contacts =  contactServiceLocal.findAll();
		try {
			this.terminal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showForm = false;
	}
	
	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}
	
	public void doAddContact() {
		contactServiceLocal.update(contact);
		showForm = false;
		this.init();

	}
	public void  doDeleteContact() {
		contactServiceLocal.delete(contact);
		contacts = contactServiceLocal.findAll();
		showForm = false;
		this.init();
		
	}
	
	/*public List<Contact> getContacts() {
		contacts = contactServiceLocal.findAll();
		return contacts;
	}*/
	
	public boolean isShowForm() {
		return showForm;
	}
	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public List<String> terminal() throws IOException{
		
		String url ="https://www.statista.com/statistics/263264/top-companies-in-the-world-by-market-value/";
		//String url ="https://www.telegraph.co.uk/business/2016/07/20/revealed-the-biggest-companies-in-the-world-in-2016/";
	    Document doc = Jsoup.connect(url).get();
	    String data="";
	    List<String> D =  new ArrayList<>();
	     Elements table = doc.select("tr"); //select the first table.
	     for (Element i:table) {
	         if (!i.getElementsByTag("tr").text().equals("")){
	            System.out.println("");
	             
	               data=i.getElementsByTag("tr").text();
	                D.add(data) ;
	                 
	    }
	     }
	     return D; 
		
	}
	
}
