package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.entities.Task;

/**
 * Session Bean implementation class ReportingService
 */
@Stateless
@LocalBean
public class ReportingService implements ReportingServiceRemote, ReportingServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public ReportingService() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public List<RawMaterial> findRawMaterialsByProduct(Product product) {
		String req = "SELECT r FROM RawMaterial r JOIN r.products p WHERE p.id = :id";
		return entityManager.createQuery(req).setParameter("id", product.getId()).getResultList();
	}

	@Override
	public List<RawMaterial> findRawMaterialsByContact(Contact contact) {
		String req = "SELECT r FROM RawMaterial r JOIN r.contacts c WHERE c.id = :id";
		return entityManager.createQuery(req).setParameter("id", contact.getId()).getResultList();
	}

	@Override
	public List<Product> findAllSoldtOutProduct(Production production) {
		String req = "SELECT p FROM Product p JOIN p.productions c WHERE c.id = :id AND p.quantity=0";
		return entityManager.createQuery(req).setParameter("id", production.getId()).getResultList();
	}

	@Override
	public List<Task> findTaskByEmployee(Employee employee) {
		String req = "SELECT t FROM Task t JOIN t.employees e WHERE e.code = :code";
		return entityManager.createQuery(req).setParameter("code", employee.getCode()).getResultList();
	}
	
}
