package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ContactService
 */
@Stateless
@LocalBean
public class ContactService extends GenericDAO<Contact> implements ContactServiceRemote, ContactServiceLocal {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ContactService() {
		super(Contact.class);
	}

	@Override
	public List<Contact> getContactByRegion(String Region) {

		String jpql = "SELECT C FROM Contact C WHERE C.region = :region";
		return entityManager.createQuery(jpql).setParameter("region", Region).getResultList();
	}

	@Override
	public List<Contact> getContactByActivitySector(String activitySector) {
		String jpql = " SELECT C FROM Contact C WHERE C.activitySector = :activitySector";
		return entityManager.createQuery(jpql).setParameter("activitySector", activitySector).getResultList();
	}

	@Override
	public List<Contact> getContactByType(String type) {
		String jpql = "SELECT C FROM Contact C WHERE C.type = :type";
		return entityManager.createQuery(jpql).setParameter("type", type).getResultList();
	}

	@Override
	public Contact getContactByName(String name) {
		String jpql = "SELECT C FROM Contact C WHERE C.name = :name";
		return (Contact) entityManager.createQuery(jpql).setParameter("name", name).getResultList();
				
	}

	@Override
	public List<Contact> getContactById(int id) {
		String jpql = "SELECT C FROM Contact C WHERE C.id = :id";
		return entityManager.createQuery(jpql).setParameter("id", id).getResultList();
	}

	@Override
	public List<Contact> getAllProviders() {
		String req = "SELECT c FROM Contact c WHERE c.type like :type";
		return entityManager.createQuery(req).setParameter("type", "Provider").getResultList();
	}

	@Override
	public List<Contact> getAllClients() {
		String req = "SELECT c FROM Contact c WHERE c.type like :type";
		return entityManager.createQuery(req).setParameter("type", "individual Client").getResultList();
	}
	
	@Override
	public List<Contact> getAllCompany() {
		String req = "SELECT c FROM Contact c WHERE c.type like :type";
		return entityManager.createQuery(req).setParameter("type", "Company").getResultList();
	}

	@Override
	public List<Contact> FindName(String name) {
		TypedQuery<Contact> query=entityManager.createQuery("select j.name  from Contact j where j.name=:name" ,Contact.class);
    	query.setParameter(name, name);
    	return query.getResultList();
	}

	@Override
	public List<Contact> searchContactByName(String name) {
		String jpql = "SELECT C FROM Contact C WHERE C.name = :name";
		return entityManager.createQuery(jpql).setParameter("name", name).getResultList();
	}
	

}
