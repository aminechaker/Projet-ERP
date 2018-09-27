package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface ProductServiceRemote extends IGenericDAO<Product> {
	Product findProductByDescription(String description);
	List<Product> findProductLowQuantity();
}
