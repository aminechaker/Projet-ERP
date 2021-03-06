package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface ProductionServiceRemote extends IGenericDAO<Production> {
	
	void assignProductionToProductAndEmployee(Product product , Production production,Employee employee);
	List<Production> findProductionByStatus(String status);
	Production findProductionByDescription(String description);
	void modifier(Production production);
	List<Production> findProductionsByDescription(String description);
	List<Production> sortProductionByDate();
	List<Production> sortProductionByStatus();
	List<Production> sortProductionByQuantity();
	Long findTotalToDo();
	Double findTotalDoing();
	Double findTotalDone();
	public void assignProductionToEmployee(Production production,Employee employee);
	Production findProductionByProduct(Product product);
}
