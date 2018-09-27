package tn.esprit.b2.esprit1718b2erp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
@LocalBean
public class ProjectService extends GenericDAO<Project> implements ProjectServiceRemote, ProjectServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private ProjectServiceLocal projectServiceLocal;

	/**
	 * Default constructor.
	 */
	public ProjectService() {
		super(Project.class);
	}

	@Override
	public void assignTaskToProject(Task task, Project project) {
		
			List<Task> newOne = new ArrayList<>();
			newOne.add(task);
			project.linkTasksToThisProject(newOne);

		projectServiceLocal.update(project);

	}

	/*
	@Override
	public void assignIncidentToProject(Incident incident, Project project) {
		List<Incident> incidentsOld = project.getIncidents();
		if (incidentsOld != null) {
			incidentsOld.add(incident);
			project.linkIncidentsToThisProject(incidentsOld);
		}
		else {
			List<Incident> newOne = new ArrayList<>();
			newOne.add(incident);
			project.linkIncidentsToThisProject(newOne);
		}
		
		projectServiceLocal.update(project);	
	}*/
	
	@Override
	public List<Project> getProjectsByTitle(String title) {
		String jpql = "SELECT T FROM Project T WHERE T.title like :title";
		return entityManager.createQuery(jpql).setParameter("title", title+"%").getResultList();
	}

	@Override
	public List<Project> sortProjectBySector() {
		String req = "SELECT p FROM Project p ORDER BY p.sector";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Project> sortProjectByClient() {
		String req = "SELECT p FROM Project p ORDER BY p.contacts"; 
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Project> sortProjectBySupervisor() {
		String req = "SELECT p FROM Project p ORDER BY p.supervisor";
		return entityManager.createQuery(req).getResultList();
	}

	/*@Override
	public Double findTotalTasks() {
		String req = "SELECT COUNT(t.id) FROM task t WHERE t.project_id LIKE "; //***verif comment avoir le projet selectionné
		return (Double) entityManager.createQuery(req).getSingleResult();
	}

	@Override
	public Double findTotalIncidents() {
		String req = "SELECT COUNT(i.id) FROM incident i WHERE i.project_id LIKE "; //***verif comment avoir le projet selectionné
		return (Double) entityManager.createQuery(req).getSingleResult();
	}*/

	@Override
	public Project findProjectByTitle(String title) {
		String req = "SELECT p FROM Project p WHERE p.title like :title";
		return (Project) entityManager.createQuery(req).setParameter("title", title).getSingleResult();
		}


}
