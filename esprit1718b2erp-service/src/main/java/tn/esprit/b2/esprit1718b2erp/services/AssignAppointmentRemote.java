package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.User;

@Remote
public interface AssignAppointmentRemote {
	void assignContactToAppointment(Contact contact, Appointment appointment);

	
}
