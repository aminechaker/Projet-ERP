package tn.esprit.b2.esprit1718b2erp.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class TaskService
 */
@Stateless
@LocalBean
public class TaskService extends GenericDAO<Task> implements TaskServiceRemote, TaskServiceLocal {
	
	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private EmployeeServiceLocal employeeServiceLocal;
	@EJB
	private ReportingServiceLocal reportingServiceLocal;
	@EJB
	private TaskServiceLocal taskServiceLocal;
	
    /**
     * Default constructor. 
     */
    public TaskService() {
        super(Task.class);
    }

	@Override
	public void assignIncidentToTask(Incident incident, Task task) {
		/*List<Incident> incidentsOld = task.getIncidents();
		if (incidentsOld != null) {
			incidentsOld.add(incident);
			task.linkIncidentsToThisTask(incidentsOld);
		}
		else {*/
			List<Incident> newOne = new ArrayList<>();
			newOne.add(incident);
			task.linkIncidentsToThisTask(newOne);
		//}
		
		taskServiceLocal.update(task);
		
	}

	@Override
	public List<Task> getTasksByTitle(String title) {
		String jpql = "SELECT t FROM Task t WHERE t.title like :title";
		return entityManager.createQuery(jpql).setParameter("title", title+"%").getResultList();
	}

	@Override
	public List<Task> sortTaskByProject() {
		String req = "SELECT t FROM Task t ORDER BY p.project";
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Task> sortTaskByEndDate() {
		String req = "SELECT t FROM Task t ORDER BY t.endDate"; 
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Task> sortTaskByStatus() {
		String req = "SELECT t FROM Task t ORDER BY t.status"; 
		return entityManager.createQuery(req).getResultList();
	}

	@Override
	public List<Task> loadRelatedTasks(Project project) {
		String jpql = "SELECT t FROM Task t WHERE t.project = :project";
		return entityManager.createQuery(jpql).setParameter("project", project).getResultList();
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
	public Task findTaskByTitleAndDescription(String title, String description) {
		String req = "SELECT t FROM Task t WHERE t.title like :title AND t.description like :desc";
		return (Task) entityManager.createQuery(req).setParameter("title", title).setParameter("desc", description).getSingleResult();
	}

	@Override
	public List<Task> findAllTaskByProject(Project project) {
		String req = "SELECT t From Task t WHERE t.project = :project";
		return entityManager.createQuery(req).setParameter("project", project).getResultList();
	}

	@Override
	public Employee findEmployeeByTask(Task task) {
		String req = "SELECT t from Task t JOIN e.Employee c WHERE c.tasks = :code";
		return (Employee) entityManager.createQuery(req).setParameter("code", task.getEmployee()).getSingleResult();
	}
}
