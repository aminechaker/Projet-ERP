package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Supervisor
 *
 */
@Entity

public class Supervisor extends User implements Serializable {

	
	private String rank;
	
	@OneToMany(mappedBy = "supervisor" ,cascade = CascadeType.MERGE)
	private List<Project> projects;
	
	private static final long serialVersionUID = 1L;

	public Supervisor() {
		super();
	}   
	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	public Supervisor(String name, String login, String password, String mobile, String adresse, String email,
			String rank) {
		super(name, login, password, mobile, adresse, email);
		this.rank = rank;
	}
	@Override
	public String toString() {
		return getName();
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public void linkProjectToSupervisor(List<Project> projects) {
		this.projects = projects;
		for (Project p : projects) {
			p.setSupervisor(this);
		}
	}
	
	
}
