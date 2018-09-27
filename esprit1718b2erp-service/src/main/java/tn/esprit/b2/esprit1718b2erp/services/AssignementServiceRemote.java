package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.entities.InventoryProduct;
import tn.esprit.b2.esprit1718b2erp.entities.InventoryRawMaterial;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Quotation;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.entities.Supervisor;
import tn.esprit.b2.esprit1718b2erp.entities.Task;

@Remote
public interface AssignementServiceRemote {
	public void assignRawMaterialToProduct(RawMaterial rawMaterial, Product product);
	
	public void assignRawMaterialToContact(RawMaterial rawMaterial, Contact contact);

	void assignInventoryToProduct(InventoryProduct inventoryProduct, Product product);

	void assignInventoryToRawMaterial(InventoryRawMaterial inventoryRawMaterial, RawMaterial rawMaterial);

	void assignInventoryRawMaterialToEmployee(InventoryRawMaterial inventory, Employee employee);

	void assignInventoryProductToEmployee(InventoryProduct inventory, Employee employee);

	void assignQuotationToProduct(Quotation quotation, Product product);
	
	void assignQuotationToContact(Quotation quotation, Contact contact);
	
	void assignQuototaionToProductAndContact(Quotation quotation,Contact contact,Product product);

	void assignEventToEmployee(Events events, Employee employee);
	
	void assignInventoryToProductAndEmployee(InventoryProduct inventoryProduct,Product product,Employee employee);
	
	void assingInventoryToRawMaterialAndEmployee(InventoryRawMaterial inventoryRawMaterial,RawMaterial rawMaterial,Employee employee);
	
	public void assignTaskToEmployee(Task task, Employee employee);
	
	public void assignProjectToContactAndSupervisor(Project project,Contact contact,Supervisor supervisor);
	

}
