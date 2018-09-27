package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class RawMaterialService
 */
@Stateless
@LocalBean
public class RawMaterialService extends GenericDAO<RawMaterial> implements RawMaterialServiceRemote, RawMaterialServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public RawMaterialService() {
        super(RawMaterial.class);
    }

	@Override
	public RawMaterial findRawMaterialByDescription(String description) {
		String req = "SELECT r from RawMaterial r WHERE r.description = :param";
		return (RawMaterial) entityManager.createQuery(req).setParameter("param", description).getSingleResult();
	}

	@Override
	public List<RawMaterial> sortByQuantity() {
		String req = "SELECT r FROM RawMaterial r ORDER BY quantity";
		return entityManager.createQuery(req).getResultList();
	}

}
