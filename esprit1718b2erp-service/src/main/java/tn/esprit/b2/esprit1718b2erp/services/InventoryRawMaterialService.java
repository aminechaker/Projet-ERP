package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.InventoryRawMaterial;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class InventoryRawMaterialService
 */
@Stateless
@LocalBean
public class InventoryRawMaterialService extends GenericDAO<InventoryRawMaterial> implements InventoryRawMaterialServiceRemote, InventoryRawMaterialServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public InventoryRawMaterialService() {
        super(InventoryRawMaterial.class);
    }

	@Override
	public List<InventoryRawMaterial> sortByDate() {
		String req = "SELECT i FROM InventoryRawMaterial i ORDER BY i.dateInventory";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<InventoryRawMaterial> sortByQuantity() {
		String req = "SELECT i FROM InventoryRawMaterial i ORDER BY i.quantity";
		return entityManager.createQuery(req).getResultList();
	}

}
