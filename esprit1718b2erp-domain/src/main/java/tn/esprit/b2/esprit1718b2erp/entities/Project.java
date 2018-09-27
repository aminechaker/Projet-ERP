package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String sector;
	private String title;
//	@Temporal(TemporalType.DATE)
	private Date startDate;
//	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	/*@ManyToMany(mappedBy="projects")
	private List<Employee> employees;*/
	
	@ManyToOne
	private Supervisor supervisor;
	
	@OneToMany(mappedBy="project", cascade = CascadeType.MERGE )
	private List<Task> tasks;
	
	/*@OneToMany(mappedBy="project", cascade = CascadeType.MERGE )
	private List<Incident> incidents;*/
	
	@ManyToOne
	private Contact contacts;
	
	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}  
	
	/*public Project(int id, String sector, String title, Date startDate, Date endDate, List<Employee> employees,
			List<Task> tasks, List<Incident> incidents, Contact contacts) {
		super();
		this.id = id;
		this.sector = sector;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees = employees;
		this.tasks = tasks;
		this.incidents = incidents;
		this.contacts = contacts;
	}*/
	
	public Project(int id, String sector, String title, Date startDate, Date endDate, List<Task> tasks, Contact contacts) {
		super();
		this.id = id;
		this.sector = sector;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tasks = tasks;
		this.contacts = contacts;
	}
	

	/*public Project(String sector, String title, Date startDate, Date endDate, List<Employee> employees,
			Contact contacts) {
		super();
		this.sector = sector;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees = employees;
		this.contacts = contacts;
	}*/

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Project(String sector, String title, Date startDate, Date endDate) {
		super();
		this.sector = sector;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	/*public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}*/
	/*public List<Incident> getIncidents() {
		return incidents;
	}
	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}*/

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Contact getContacts() {
		return contacts;
	}

	public void setContacts(Contact contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return title;
	}
	
	public void linkTasksToThisProject(List<Task> task) {
		this.tasks = tasks;
		for (Task t : tasks) {
			t.setProject(this);
		}
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	
	
	
	/*public void linkIncidentsToThisProject(List<Incident> incident) {
		this.incidents = incidents;
		for (Incident inc : incidents) {
			inc.setProject(this);
		}
	}*/

	

	

	/*public Project(String sector, String title, java.sql.Date startDate, java.sql.Date endDate, Employee employees,
			Contact contacts) {
		super();
		this.sector = sector;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees =(List<Employee>) employees;
		this.contacts = contacts;
		
	}*/
	
	
	
   
}
