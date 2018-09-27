package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Incident
 *
 */
@Entity

public class Incident implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Date incidentDate;
	private String employee;
	private String status;
	
	@ManyToOne
	private Task task;

	
	private static final long serialVersionUID = 1L;

	public Incident() {
		super();
	}
	
	
	public Incident(int id, String name, String description, Date incidentDate, String employee, String status, Task task) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.incidentDate = incidentDate;
		this.employee = employee;
		this.status = status;
		this.task = task;
	}


	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getIncidentDate() {
		return incidentDate;
	}


	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}


	public String getEmployee() {
		return employee;
	}


	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public Incident(String name, String description, Date incidentDate, String status) {
		super();
		this.name = name;
		this.description = description;
		this.incidentDate = incidentDate;
		this.status = status;
	}


	@Override
	public String toString() {
		return "Incident [id=" + id + ", name=" + name + ", description=" + description + ", incidentDate="
				+ incidentDate + ", employee=" + employee + ", status=" + status + ", task=" + task + "]";
	}

	
}
