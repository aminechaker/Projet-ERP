package tn.esprit.b2.esprit1718b2erp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.User;

/**
 * Session Bean implementation class AssignAppointment
 */
@Stateless
@LocalBean
public class AssignAppointment implements AssignAppointmentRemote, AssignAppointmentLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private AppointmentServiceLocal appointmentServiceLocal;

	public AssignAppointment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void assignContactToAppointment(Contact contact, Appointment appointment) {
		/*List<Appointment> appointmentsOld = contact.getAppointments();
		if (appointmentsOld != null) {
			appointmentsOld.add(appointment);
			contact.linkAppointmentToContact(appointmentsOld);
		} else {*/
			List<Appointment> newOne = new ArrayList<>();
			newOne.add(appointment);
			contact.linkAppointmentToContact(newOne);

	//}
		appointmentServiceLocal.update(appointment);
	}

	

}
