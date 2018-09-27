package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.InventoryProduct;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface InventoryProductServiceLocal extends IGenericDAO<InventoryProduct> {
	List<InventoryProduct> sortByDate();
	List<InventoryProduct> sortByQuantity();
	List<InventoryProduct> sortByPrice();
}
