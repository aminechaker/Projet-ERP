package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface ProductServiceLocal extends IGenericDAO<Product> {
	Product findProductByDescription(String description);
	List<Product> findProductLowQuantity();
}
