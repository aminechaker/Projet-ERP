package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.InventoryProduct;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class InventoryProductService
 */
@Stateless
@LocalBean
public class InventoryProductService extends GenericDAO<InventoryProduct> implements InventoryProductServiceRemote, InventoryProductServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public InventoryProductService() {
        super(InventoryProduct.class);
    }

	@Override
	public List<InventoryProduct> sortByDate() {
		String req = "SELECT i from InventoryProduct i ORDER BY i.dateInventory";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<InventoryProduct> sortByQuantity() {
		String req = "SELECT i from InventoryProduct i ORDER BY i.quantity";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<InventoryProduct> sortByPrice() {
		String req = "SELECT i from InventoryProduct i ORDER BY i.price";
		return entityManager.createQuery(req).getResultList();
	}

}
