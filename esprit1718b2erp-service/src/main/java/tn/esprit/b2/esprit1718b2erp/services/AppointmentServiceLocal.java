package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface AppointmentServiceLocal extends IGenericDAO<Appointment> {

	List<Appointment> getAppointmentByClient(Contact Client );
	List<Appointment> getAppointmentByType(String Type );
	List<Appointment> getAppointmentByTopic(String sujet );
	List<Appointment> getAppointmentByClientName(Contact contact);
	List<Appointment> getAppointmentByName(String name);
	List<Appointment> getAppointmentByCLientId(int id );

}
