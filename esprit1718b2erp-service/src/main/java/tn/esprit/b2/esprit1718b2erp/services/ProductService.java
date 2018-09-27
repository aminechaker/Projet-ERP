package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ProductService
 */
@Stateless
@LocalBean
public class ProductService extends GenericDAO<Product> implements ProductServiceRemote, ProductServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public ProductService() {
        super(Product.class);
    }

	@Override
	public Product findProductByDescription(String description) {
		String req = "SELECT p FROM Product p WHERE p.description like :desc";
		return (Product) entityManager.createQuery(req).setParameter("desc", description).getSingleResult();
	}

	@Override
	public List<Product> findProductLowQuantity() {
		String req = "SELECT p FROM Product p WHERE p.quantity < 5";
		return entityManager.createQuery(req).getResultList();
	}

}
