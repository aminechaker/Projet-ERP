package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class IncidentService
 */
@Stateless
@LocalBean
public class IncidentService extends GenericDAO<Incident> implements IncidentServiceRemote, IncidentServiceLocal {
	
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public IncidentService() {
        super(Incident.class);
    }

	@Override
	public List<Incident> getIncidentsByTitle(String title) {
		String jpql = "SELECT i FROM Incident i WHERE i.title like :title";
		return entityManager.createQuery(jpql).setParameter("title", title+"%").getResultList();
	}

	@Override
	public List<Incident> sortIncidentByProject() { // *** A REVOIR ***
		//String req = "SELECT i FROM Incident i ORDER BY p.project";
		//return entityManager.createQuery(req).getResultList();
		String req = "SELECT i FROM Incident i ORDER BY i.task";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Incident> sortTaskByTask() {
		String req = "SELECT i FROM Incident i ORDER BY i.task";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Incident> sortTaskByStatus() {
		String req = "SELECT i FROM Incident i ORDER BY i.status";
		return entityManager.createQuery(req).getResultList();
	}

}
