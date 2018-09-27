package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.text.SimpleDateFormat;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.services.AppointmentServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceLocal;

@ManagedBean
@ViewScoped
public class AppointmentBean {
	
	private Boolean showForm;
	private Contact contact;
	private Appointment appointment;
	private List<Contact> contacts;
	private List<Appointment> appointments;
	private int SelectedContactId;
	
	
	@EJB
	ContactServiceLocal contactServiceLocal;
	@EJB
	AppointmentServiceLocal appointmentServiceLocal;
	
	@PostConstruct
	private void init() {
		//date = new  Date();
		appointment = new Appointment();
		contacts = contactServiceLocal.findAll();
		appointments=appointmentServiceLocal.findAll();
		showForm = false;
	}
	
	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}
	
	public void doAddAppointment() {
		//SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
		//Appointment appointment = new Appointment(sujet, type, date);
		Contact selectedContact= new Contact();
		selectedContact.setId(SelectedContactId);
		appointment.setContact(selectedContact);
		appointmentServiceLocal.update(appointment);
			this.init();
		}
	public void doDeleteAppointment(){
		appointmentServiceLocal.delete(appointment);
		
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

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public int getSelectedContactId() {
		return SelectedContactId;
	}

	public void setSelectedContactId(int selectedContactId) {
		SelectedContactId = selectedContactId;
	}

	public void confirmation() {
        addMessage("confirmation", "are you sure");
        
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
        
    }
	
	
	
}
