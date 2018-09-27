package tn.esprit.b2.esprit1718b2erp.mBeans;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2erp.entities.Claims;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.services.ClaimsServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceLocal;

@ManagedBean
@ViewScoped
public class ClaimsBean {

	private Boolean showForm;
	private Contact contact;
	private Claims claims;
	private List<Contact> contacts;
	private List<Claims> claimss;
	private int SelectedContactId;
	private Date date;
	private List<Claims> claimsUntreated;
	
	@EJB
	ContactServiceLocal contactServiceLocal;
	@EJB
	ClaimsServiceLocal claimsServiceLocal;
	
	@PostConstruct
	private void init() {
		date = new  Date();
		 claims = new Claims();
		contacts = contactServiceLocal.findAll();
		claimss = claimsServiceLocal.findAll();
		claimsUntreated =claimsServiceLocal.selectUntreatedClaims();
		showForm = false;
	}
	
	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}
	
	public void doAddClaims() {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
		//Claims claims = new Claims();
		Contact selectedContact= new Contact();
		selectedContact.setId(SelectedContactId);
		claims.setClaimDate(date);
		claims.setContact(selectedContact);
		claims.setStatus("untreated");
		
		//Claims claims = new Claims(description, claimDate, status, selectedContact)
		claimsServiceLocal.update(claims);
			this.init();
		}
	public void doDeleteClaims(){
		claimsServiceLocal.delete(claims);
		this.init();
	}
	
	public void doTreatClaims(){
		claims.setStatus("treated");
		claimsServiceLocal.update(claims);
		//this.sendSms();
		this.init();
	}
	
	public boolean isShowForm() {
		return showForm;
	}
	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}



	public Boolean getShowForm() {
		return showForm;
	}

	public void setShowForm(Boolean showForm) {
		this.showForm = showForm;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Claims> getClaimss() {
		return claimss;
	}

	public void setClaimss(List<Claims> claimss) {
		this.claimss = claimss;
	}

	public int getSelectedContactId() {
		return SelectedContactId;
	}

	public void setSelectedContactId(int selectedContactId) {
		SelectedContactId = selectedContactId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Claims> getClaimsUntreated() {
		return claimsUntreated;
	}

	public void setClaimsUntreated(List<Claims> claimsUntreated) {
		this.claimsUntreated = claimsUntreated;
	}
	
	
	public  void callURL(String a ) {
        
        String myURL="https://rest.nexmo.com/sms/json?api_key=fa966f8a&api_secret=02KdmnMmerHnJWEa&to=216"+
                "21492477"+"&from=OrionErp&text="+a+"your+claim+has+been+treated+";
        System.out.println(myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }
        
        
    }
	public void sendSms(){
		this.callURL(claims.getContact().getName());
	}
	
	
	
	
}
