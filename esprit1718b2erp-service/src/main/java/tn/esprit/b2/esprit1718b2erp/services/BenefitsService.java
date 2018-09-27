package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.b2.esprit1718b2erp.entities.Benefits;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class BenefitsService
 */
@Stateless
@LocalBean
public class BenefitsService extends GenericDAO<Benefits> implements BenefitsServiceRemote, BenefitsServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
	
    public BenefitsService() {
        super(Benefits.class);
    }
    @Override
	public List<Benefits> getBenefitsById(int id) {
		String jpql="Select B FROM Benefits B WHERE B.id = :id";
		return entityManager.createQuery(jpql).getResultList();
	}
    @Override
	public List<Benefits> getBenefitsByType(String type) {
		String jpql="Select B FROM Benefits B WHERE B.type = :type";
		return entityManager.createQuery(jpql).getResultList();
	}
	@Override
	public List<Benefits> getBenefits() {
		String jpql="Select * FROM Benefits ";
		return entityManager.createQuery(jpql).getResultList();
	}
	@Override
	public List<Benefits> getBenefitsById() {
		// TODO Auto-generated method stub
		return null;
	}



}