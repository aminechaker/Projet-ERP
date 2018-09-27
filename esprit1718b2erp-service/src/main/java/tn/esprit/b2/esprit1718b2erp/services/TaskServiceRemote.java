package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface TaskServiceRemote extends IGenericDAO<Task> {
	public void assignIncidentToTask(Incident incident, Task task);
	public List<Task> getTasksByTitle(String title);
	public List<Task> sortTaskByProject();
	public List<Task> sortTaskByEndDate();
	public List<Task> sortTaskByStatus();
	public List<Task> loadRelatedTasks(Project project);
	void assignTaskToEmployee(Task task,Employee employee);
	Task findTaskByTitleAndDescription(String title,String description);
	List<Task> findAllTaskByProject(Project project);
	Employee findEmployeeByTask( Task task);
}
