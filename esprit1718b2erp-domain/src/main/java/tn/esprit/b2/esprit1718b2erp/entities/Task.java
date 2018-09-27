package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Task
 *
 */
@Entity

public class Task implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private Date startDate;
	private Date endDate;
	private String description;
	private String document;
	//private String employee;
	private String status;
	private String priority;
	
	@OneToMany(mappedBy="task", cascade = CascadeType.MERGE )
	private List<Incident> incidents;
	
	@ManyToOne
	private Project project;
	
	/*@ManyToMany(mappedBy="tasks")
	private List<Employee> employees;*/
	@ManyToOne
	private Employee employee;
	
	private static final long serialVersionUID = 1L;

	public Task() {
		super();
	} 
	

	/*public Task(int id, String title, Date startDate, Date endDate, String description, String document,
			String employee, String status, int priority, List<Incident> incidents, Project project) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		Document = document;
		this.employee = employee;
		this.status = status;
		this.priority = priority;
		this.incidents = incidents;
		this.project = project;
	}*/



	public Task(String title, Date startDate, Date endDate, String description, String status, String priority) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.status = status;
		this.priority = priority;
	}

	public Task(int id, String title, Date startDate, Date endDate, String description, String document, String status,
			String priority, List<Incident> incidents, Project project, Employee employee) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.document = document;
		this.status = status;
		this.priority = priority;
		this.incidents = incidents;
		this.project = project;
		this.employee = employee;
	}

	public int getId() {
		return this.id;
	}

	


	public void setId(int id) {
		this.id = id;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	public String getName() {
		return title;
	}
	public void setName(String name) {
		this.title = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	/*public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}*/
	
	public String getStatus() {
		return status;
	}

	/*public List<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}*/
	
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public List<Incident> getIncidents() {
		return incidents;
	}


	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	

	public void linkIncidentsToThisTask(List<Incident> incident) {
		this.incidents = incidents;
		for (Incident inc : incidents) {
			inc.setTask(this);
		}
	}


	@Override
	public String toString() {
		return title;
	}
	
}
