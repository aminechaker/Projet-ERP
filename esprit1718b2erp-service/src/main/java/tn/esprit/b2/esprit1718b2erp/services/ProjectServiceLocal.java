package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface ProjectServiceLocal extends IGenericDAO<Project> {
	public void assignTaskToProject(Task task, Project project);
	//public void assignIncidentToProject(Incident incident, Project project);
	public List<Project> getProjectsByTitle(String title);
	public List<Project> sortProjectBySector();
	public List<Project> sortProjectByClient();
	public List<Project> sortProjectBySupervisor();
	//public Double findTotalTasks();
	//public Double findTotalIncidents();
	public Project findProjectByTitle(String title);

}
