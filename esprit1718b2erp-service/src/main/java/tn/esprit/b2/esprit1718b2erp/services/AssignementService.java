package tn.esprit.b2.esprit1718b2erp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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

/**
 * Session Bean implementation class AssignementService
 */
@Stateless
@LocalBean
public class AssignementService implements AssignementServiceRemote, AssignementServiceLocal {

	/**
	 * Default constructor.
	 */
	@EJB
	ReportingServiceLocal reportingServiceLocal;
	@EJB
	ProductServiceLocal productServiceLocal;
	@EJB
	RawMaterialServiceLocal rawMaterialServiceLocal;
	@EJB
	EmployeeServiceLocal employeeServiceLocal;
	@EJB
	ContactServiceLocal contactServiceLocal;

	public AssignementService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void assignRawMaterialToProduct(RawMaterial rawMaterial, Product product) {
		List<RawMaterial> list = reportingServiceLocal.findRawMaterialsByProduct(product);
		if (!list.contains(rawMaterial)) {
			list.add(rawMaterial);
		}
		product.setMaterials(list);
		productServiceLocal.update(product);
	}

	@Override
	public void assignInventoryToProduct(InventoryProduct inventoryProduct, Product product) {

		/*
		 * List<Production> productionOld = product.getProductions(); if
		 * (productionOld != null) { productionOld.add(production);
		 * product.linkProductionToThisProduct(productionOld); } else {
		 */
		List<InventoryProduct> newOne = new ArrayList<>();
		newOne.add(inventoryProduct);
		product.linkInventoryToThisProduct(newOne);
		// }

		productServiceLocal.update(product);
	}

	@Override
	public void assignInventoryToRawMaterial(InventoryRawMaterial inventoryRawMaterial, RawMaterial rawMaterial) {
		/*
		 * List<Production> productionOld = product.getProductions(); if
		 * (productionOld != null) { productionOld.add(production);
		 * product.linkProductionToThisProduct(productionOld); } else {
		 */
		List<InventoryRawMaterial> newOne = new ArrayList<>();
		newOne.add(inventoryRawMaterial);
		rawMaterial.linkInventoryToThisRawMaterial(newOne);
		// }

		rawMaterialServiceLocal.update(rawMaterial);
	}

	@Override
	public void assignInventoryRawMaterialToEmployee(InventoryRawMaterial inventory, Employee employee) {

		/*
		 * List<Production> productionOld = product.getProductions(); if
		 * (productionOld != null) { productionOld.add(production);
		 * product.linkProductionToThisProduct(productionOld); } else {
		 */
		List<InventoryRawMaterial> newOne = new ArrayList<>();
		newOne.add(inventory);
		employee.linkInventoryRawMaterialToThisEmployee(newOne);
		// }

		employeeServiceLocal.update(employee);
	}

	@Override
	public void assignInventoryProductToEmployee(InventoryProduct inventory, Employee employee) {
		/*
		 * List<Production> productionOld = product.getProductions(); if
		 * (productionOld != null) { productionOld.add(production);
		 * product.linkProductionToThisProduct(productionOld); } else {
		 */
		List<InventoryProduct> newOne = new ArrayList<>();
		newOne.add(inventory);
		employee.linkInventoryProductToThisEmployee(newOne);
		// }

		employeeServiceLocal.update(employee);
	}

	@Override
	public void assignQuotationToContact(Quotation quotation, Contact contact) {
		/*
		 * List<Production> productionOld = product.getProductions(); if
		 * (productionOld != null) { productionOld.add(production);
		 * product.linkProductionToThisProduct(productionOld); } else {
		 */
		List<Quotation> newOne = new ArrayList<>();
		newOne.add(quotation);
		contact.linkQuotationToContact(newOne);
		// }

		contactServiceLocal.update(contact);
		
	}

	@Override
	public void assignQuotationToProduct(Quotation quotation, Product product) {
		/*
		 * List<Production> productionOld = product.getProductions(); if
		 * (productionOld != null) { productionOld.add(production);
		 * product.linkProductionToThisProduct(productionOld); } else {
		 */
		List<Quotation> newOne = new ArrayList<>();
		newOne.add(quotation);
		product.linkQuotationToThisProduct(newOne);
		// }

		productServiceLocal.update(product);
		
	}

	@Override
	public void assignQuototaionToProductAndContact(Quotation quotation, Contact contact, Product product) {
		/*this.assignQuotationToContact(quotation, contact);
		this.assignQuotationToProduct(quotation, product);*/
		List<Quotation> newOne = new ArrayList<>();
		newOne.add(quotation);
		product.linkQuotationToThisProduct(newOne);
		contact.linkQuotationToContact(newOne);
		productServiceLocal.update(product);
		//contactServiceLocal.update(contact);
	}

	@Override
	public void assignRawMaterialToContact(RawMaterial rawMaterial, Contact contact) {
		List<RawMaterial> list = reportingServiceLocal.findRawMaterialsByContact(contact);
		if (!list.contains(rawMaterial)) {
			list.add(rawMaterial);
		}
		contact.setMaterials(list);
		contactServiceLocal.update(contact);
	}

	@Override
	public void assignInventoryToProductAndEmployee(InventoryProduct inventoryProduct, Product product,
			Employee employee) {
		List<InventoryProduct> list = new ArrayList<>();
		list.add(inventoryProduct);
		product.linkInventoryToThisProduct(list);
		employee.linkInventoryProductToThisEmployee(list);
		productServiceLocal.update(product);
		employeeServiceLocal.update(employee);
		
	}

	@Override
	public void assingInventoryToRawMaterialAndEmployee(InventoryRawMaterial inventoryRawMaterial,
			RawMaterial rawMaterial, Employee employee) {
		List<InventoryRawMaterial> list = new ArrayList<>();
		list.add(inventoryRawMaterial);
		rawMaterial.linkInventoryToThisRawMaterial(list);
		employee.linkInventoryRawMaterialToThisEmployee(list);
		rawMaterialServiceLocal.update(rawMaterial);
		employeeServiceLocal.update(employee);
		
	}
	@Override
	public void assignEventToEmployee(Events events, Employee employee) {
		List<Events> newOne = new ArrayList<>();
		newOne.add(events);
		employee.linkEventToThisEmployee(newOne);
		employeeServiceLocal.update(employee);
		
	}

	@Override
	public void assignTaskToEmployee(Task task, Employee employee) {
		List<Task> list = reportingServiceLocal.findTaskByEmployee(employee);
		if (!list.contains(task)) {
			list.add(task);
		}
		employee.setTasks(list);
		employeeServiceLocal.update(employee);
		
	}

	@Override
	public void assignProjectToContactAndSupervisor(Project project, Contact contact, Supervisor supervisor) {
		List<Project> newOne = new ArrayList<>();
		newOne.add(project);
		contact.linkProjectToContact(newOne);
		supervisor.linkProjectToSupervisor(newOne);
		contactServiceLocal.update(contact);
		//supervisorServiceLocal.update(supervisor);
		
	}

	

}
