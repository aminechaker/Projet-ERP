package tn.esprit.b2.esprit1718b2erp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ProductionService
 */
@Stateless
@LocalBean
public class ProductionService extends GenericDAO<Production>
		implements ProductionServiceRemote, ProductionServiceLocal {

	@PersistenceContext
	EntityManager entityManager;

	@EJB
	ProductServiceLocal productServiceLocal;
	@EJB
	EmployeeServiceLocal employeeServiceLocal;

	/**
	 * Default constructor.
	 */
	public ProductionService() {
		super(Production.class);
	}

	@Override
	public void assignProductionToProductAndEmployee(Product product, Production production,Employee employee) {
		List<Production> newOne = new ArrayList<>();
		newOne.add(production);
		product.linkProductionToThisProduct(newOne);
		employee.linkProductionToThisEmployee(newOne);
		productServiceLocal.update(product);
	}

	@Override
	public List<Production> findProductionByStatus(String status) {
		String req = "SELECT p from Production p WHERE p.productionStatus like :status";
		return entityManager.createQuery(req).setParameter("status", status).getResultList();
	}

	@Override
	public Production findProductionByDescription(String description) {
		String req = "SELECT p FROM Production p WHERE p.description like :desc";
		return (Production) entityManager.createQuery(req).setParameter("desc", description).getSingleResult();
	}

	@Override
	public void modifier(Production production) {
		entityManager.merge(production);
	}

	@Override
	public List<Production> sortProductionByDate() {
		String req = "SELECT p FROM Production p ORDER BY dateProduction";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Production> sortProductionByStatus() {
		String req = "SELECT p FROM Production p ORDER BY productionStatus";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Production> sortProductionByQuantity() {
		String req = "SELECT p FROM Production p ORDER BY quantite";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public Long findTotalToDo() {
		String req = "SELECT COUNT(p) FROM Production p WHERE p.productionStatus like To_Do";
		return (Long) entityManager.createQuery(req).getSingleResult();
	}

	@Override
	public Double findTotalDoing() {
		String req = "SELECT COUNT(p.id) FROM Production p WHERE p.productionStatus like Doing";
		return (Double) entityManager.createQuery(req).getSingleResult();
	}

	@Override
	public Double findTotalDone() {
		String req = "SELECT COUNT(p.id) FROM Production p WHERE p.productionStatus like Done";
		return (Double) entityManager.createQuery(req).getSingleResult();
	}

	@Override
	public List<Production> findProductionsByDescription(String description) {
		String req = "SELECT p FROM Production p WHERE p.description like :desc";
		return entityManager.createQuery(req).setParameter("desc", description+"%").getResultList();
	}
	@Override
	public void assignProductionToEmployee(Production production, Employee employee) {
		List<Production> list = new ArrayList<>();
		list.add(production);
		employee.linkProductionToThisEmployee(list);
		employeeServiceLocal.update(employee);
	}

	@Override
	public Production findProductionByProduct(Product product) {
		String req = "SELECT p FROM Production p WHERE p.products = :product";
		return (Production) entityManager.createQuery(req).setParameter("product", product).getSingleResult();
	}
}
