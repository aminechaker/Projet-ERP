package tn.esprit.b2.esprit1718b2erp.services;

import java.awt.Event;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class EventService
 */
@Stateless
@LocalBean
public class EventService extends GenericDAO<Events> implements EventServiceRemote, EventServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public EventService() {
        super(Events.class);
    }

	@Override
	public List<Events> findEventByName(String name) {
		String req = "SELECT e FROM Events e WHERE e.name like :name";
		return entityManager.createQuery(req).setParameter("name", name+"%").getResultList();
	}

	@Override
	public List<Events> findEventByDate(Date date) {
		
		String req = "SELECT e FROM Events e WHERE e.eventDate like :date";
		return  entityManager.createQuery(req).setParameter("date", date).getResultList();
	}

	@Override
	public List<Events> sortByLocalisation(String localisation) {
		String req = "SELECT e FROM Events e WHERE e.lieu like :localisation order by e.eventDate";
		return  entityManager.createQuery(req).setParameter("localisation", localisation).getResultList();
	}

	

	@Override
	public List<Events> sortBySubject(String subject) {
		String req = "SELECT e FROM Events e WHERE e.subject like :subject order by e.eventDate";
		return  entityManager.createQuery(req).setParameter("subject", subject).getResultList();
	}

	@Override
	public List<Events> sortEventByDate() {
		String req = "SELECT e FROM Events e ORDER BY e.eventDate ASC";
		return entityManager.createQuery(req).getResultList();
	}

}
