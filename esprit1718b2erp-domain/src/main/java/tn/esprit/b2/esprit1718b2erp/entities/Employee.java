package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.lang.Float;
import java.util.List;

import javax.persistence.*;
import tn.esprit.b2.esprit1718b2erp.entities.User;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity
public class Employee extends User implements Serializable {

	
	private Float salary;
	private String poste;
	
	@ManyToMany
	private List<Project> projects;
	
	@OneToMany(mappedBy="employee" , cascade= CascadeType.MERGE)
	private List<InventoryProduct> inventories;
	
	@OneToMany(mappedBy="employee" , cascade= CascadeType.MERGE)
	private List<InventoryRawMaterial> inventoryRawMaterails ;
	
	@OneToMany(mappedBy = "employee", cascade= CascadeType.MERGE)
	private List<Events> events;
	@OneToMany(mappedBy = "employee" ,cascade = CascadeType.MERGE)
	private List<Production> productions;
	
	@OneToMany(mappedBy="employee", cascade = CascadeType.MERGE )
	private List<Task> tasks;
	
	private static final long serialVersionUID = 1L;

	public Employee() {
		super();
	} 
	
	public Float getSalary() {
		return this.salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}

	
	
	/*public boolean equals(Object o) {
		return events.equals(o);
	}*/

	public Employee(String name, String login, String password, String mobile, String adresse, String email,
			Float salary, String poste) {
		super(name, login, password, mobile, adresse, email);
		this.salary = salary;
		this.poste = poste;
	}
	

	public List<InventoryProduct> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryProduct> inventories) {
		this.inventories = inventories;
	}

	public List<InventoryRawMaterial> getInventoryRawMaterails() {
		return inventoryRawMaterails;
	}

	public void setInventoryRawMaterails(List<InventoryRawMaterial> inventoryRawMaterails) {
		this.inventoryRawMaterails = inventoryRawMaterails;
	}

	
	public List<Events> getEnvents() {
		return events;
	}

	public void setEnvents(List<Events> envents) {
		this.events = envents;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	/*public Employee(int code, String name, String login, String password, String mobile, String adresse, String email,
			Expenses expenses, Benefits benefits, List<Claims> claims, List<Events> envents,
			List<Appointment> appointments, List<Quotation> quotations, List<Assets> assets) {
		super(code, name, login, password, mobile, adresse, email, expenses, benefits, claims, envents, appointments,
				quotations, assets);
		// TODO Auto-generated constructor stub
	}*/
	public void linkInventoryProductToThisEmployee(List<InventoryProduct> inventoryProducts) {
		this.inventories = inventoryProducts;
		for (InventoryProduct p : inventoryProducts) {
			p.setEmployee(this);
		}
	}
	public void linkInventoryRawMaterialToThisEmployee(List<InventoryRawMaterial> inventoryRawMaterials) {
		this.inventoryRawMaterails = inventoryRawMaterials;
		for (InventoryRawMaterial p : inventoryRawMaterials) {
			p.setEmployee(this);
		}
	}

	public void linkEventToThisEmployee(List<Events> events) {
		this.events = events;
		for (Events e : events) {
			e.setEmployee(this);
		}
	}
	public void linkProductionToThisEmployee(List<Production> productions) {
		this.productions = productions;
		for (Production p : productions) {
			p.setEmployee(this);
		}
	}
	


	public List<Production> getProductions() {
		return productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	@Override
	public String toString() {
		return getName();
	}

	public Employee(String name, String login, String password, String mobile, String adresse, String email,String poste,Float salary) {
		super(name, login, password, mobile, adresse, email);
		this.poste = poste;
		this.salary = salary;
	}
	
	public void linkTasksToThisEmployee(List<Task> task) {
		this.tasks = task;
		for (Task t : task) {
			t.setEmployee(this);
		}
	}
   
}
