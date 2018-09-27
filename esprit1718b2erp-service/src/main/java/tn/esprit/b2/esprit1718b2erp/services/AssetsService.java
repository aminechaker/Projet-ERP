package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class AssetsService
 */
@Stateless
@LocalBean
public class AssetsService extends GenericDAO<Assets> implements AssetsServiceRemote, AssetsServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
	
    public AssetsService() {
        super(Assets.class);
    }
    @Override
	public List<Assets> getAssetsById(int id) {
		String jpql = "SELECT A FROM Assets A WHERE A.id = :id";
		return entityManager.createQuery(jpql).getResultList();
	}
	@Override
	public List<Assets> getAssetsByType(String type) {
		String jpql = "SELECT A FROM Assets A WHERE A.type = :type";
		return entityManager.createQuery(jpql).setParameter("type", type).getResultList();
	}

    @Override
	public List<Assets> getAssetsBen() {
		String jpql = "SELECT A FROM Assets A WHERE A.value < A.actual_value";
		return entityManager.createQuery(jpql).getResultList();
	}
    @Override
	public List<Assets> getAssetsExp() {
		String jpql = "SELECT A FROM Assets A WHERE A.value > A.actual_value";
		return entityManager.createQuery(jpql).getResultList();
	}
    public Assets  findAssetByName(String name){
    	String jpql = "SELECT A FROM Assets A WHERE A.name = :name";
		return (Assets) entityManager.createQuery(jpql).getResultList();
    }
	@Override
	public List<String> getAssetsByType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}