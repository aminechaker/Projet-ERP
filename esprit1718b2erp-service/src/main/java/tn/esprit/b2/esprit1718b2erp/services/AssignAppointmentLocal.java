package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.User;

@Local
public interface AssignAppointmentLocal {
	void assignContactToAppointment(Contact contact, Appointment appointment);

	
}
