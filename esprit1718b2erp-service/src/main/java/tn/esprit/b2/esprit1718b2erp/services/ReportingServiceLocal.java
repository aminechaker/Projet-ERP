package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;
import javax.enterprise.inject.spi.Producer;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.entities.Task;

@Local
public interface ReportingServiceLocal {
	public List<RawMaterial> findRawMaterialsByProduct(Product product);
	
	public List<RawMaterial> findRawMaterialsByContact(Contact contact);
	
	List<Product> findAllSoldtOutProduct(Production production);
	
	public List<Task> findTaskByEmployee(Employee employee);

}
