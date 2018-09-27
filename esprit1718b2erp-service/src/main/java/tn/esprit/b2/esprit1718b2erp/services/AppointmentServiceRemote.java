package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface AppointmentServiceRemote extends IGenericDAO<Appointment> {

	List<Appointment> getAppointmentByClient(Contact contact );
	List<Appointment> getAppointmentByType(String Type );
	List<Appointment> getAppointmentByTopic(String sujet );
	List<Appointment> getAppointmentByClientName(Contact contact);
	List<Appointment> getAppointmentByName(String name);
	List<Appointment> getAppointmentByCLientId(int id );
	
}
