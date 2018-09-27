package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.InventoryProduct;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface InventoryProductServiceRemote extends IGenericDAO<InventoryProduct> {
	List<InventoryProduct> sortByDate();
	List<InventoryProduct> sortByQuantity();
	List<InventoryProduct> sortByPrice();
}
