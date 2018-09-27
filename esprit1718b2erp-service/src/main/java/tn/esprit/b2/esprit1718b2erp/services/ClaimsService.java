package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Claims;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ClaimsService
 */
@Stateless
@LocalBean
public class ClaimsService extends GenericDAO<Claims> implements ClaimsServiceRemote, ClaimsServiceLocal {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ClaimsService() {
		super(Claims.class);
	}

	@Override
	public List<Claims> getClaimstByClient(Contact contact) {
		String req = "select p from Claims p JOIN p.contact c where c.id = :id  ";
		return entityManager.createQuery(req).setParameter("id", contact.getId()).getResultList();
	}

	@Override
	public List<Claims> getClaimsByStatus(String Status) {
		String req = "select p from Claims p where p.status= :status";

		return entityManager.createQuery(req).setParameter("status", Status).getResultList();
	}

	@Override
	public List<Claims> getClaimstByClientName(String name) {
		String req = "select p from Claims p JOIN p.contact c where c.name = :name  ";
		return entityManager.createQuery(req).setParameter("name", name).getResultList();
	}

	@Override
	public List<Claims> selectUntreatedClaims(){
		String req = "select p from Claims p where p.status= :status ";
		return entityManager.createQuery(req).setParameter("status","untreated").getResultList();
	}
}
