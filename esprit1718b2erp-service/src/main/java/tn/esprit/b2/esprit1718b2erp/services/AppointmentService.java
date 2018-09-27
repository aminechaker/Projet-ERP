package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class AppointmentService
 */
@Stateless
@LocalBean
public class AppointmentService extends GenericDAO<Appointment>
		implements AppointmentServiceRemote, AppointmentServiceLocal {

	@PersistenceContext
	EntityManager entityManager;

	public AppointmentService() {
		super(Appointment.class);
	}

	@Override
	public List<Appointment> getAppointmentByClient(Contact contact) {
		String req = "select a from Appointment a JOIN a.contact c where c.id = :id  ";
		return entityManager.createQuery(req).setParameter("id", contact.getId()).getResultList();
	}

	@Override
	public List<Appointment> getAppointmentByType(String type) {
		String jpql = "SELECT A FROM Appointment A WHERE A.type = :type";
		return entityManager.createQuery(jpql).setParameter("type", type).getResultList();

	}

	@Override
	public List<Appointment> getAppointmentByTopic(String sujet) {

		String jpql = "SELECT A FROM Appointment A WHERE A.sujet = :sujet";
		return entityManager.createQuery(jpql).setParameter("sujet", sujet).getResultList();
	}

	@Override
	public List<Appointment> getAppointmentByClientName(Contact contact) {
		String req = "select a from Appointment a JOIN a.contact c where c.id = :id  ";
		return entityManager.createQuery(req).setParameter("id", contact.getId()).getResultList();
	}

	@Override
	public List<Appointment> getAppointmentByName(String name) {
		String req = "select a from Appointment a JOIN a.contact c where c.name = :name  ";
		return entityManager.createQuery(req).setParameter("name", name).getResultList();
	}
	/*
	@Override
	public List<Appointment> getAppointmentByClientId(int id ) {
		String req = "select a from Appointment a JOIN a.contact c where c.id = :id  ";
		return entityManager.createQuery(req).setParameter("id", id).getResultList();
	}
	*/

	@Override
	public List<Appointment> getAppointmentByCLientId(int id) {
		String req = "select a from Appointment a JOIN a.contact c where c.id = :contact_id  ";
		return entityManager.createQuery(req).setParameter("id", id).getResultList();
	}
	
}
