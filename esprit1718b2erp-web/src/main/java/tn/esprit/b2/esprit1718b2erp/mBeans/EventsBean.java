package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.EventServiceLocal;

@ManagedBean
@ViewScoped
public class EventsBean {

	private Events event;
	private boolean showForm;
	private String idEmployee;

	private List<Employee> employees;
	private List<Events> events;

	@EJB
	EmployeeServiceLocal employeeServiceLocal;
	@EJB
	EventServiceLocal eventServiceLocal;
	@EJB
	AssignementServiceLocal assignementServiceLocal;

	@PostConstruct
	private void init() {
		event = new Events();
		employees = employeeServiceLocal.findAll();
		events = eventServiceLocal.findAll();
		showForm = false;
	}

	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}

	public void doUpdate() {
		eventServiceLocal.update(event);
		this.init();
	}

	public void doAddEvent() {
		int x = Integer.parseInt(idEmployee);
		Employee employee = employeeServiceLocal.find(x);
		event.setRating(null);
		assignementServiceLocal.assignEventToEmployee(event, employee);
		this.init();
	}

	public void rate(float rr, Events e) {
		List<Events> ev1 = new ArrayList<>();

		ev1 = eventServiceLocal.findAll();
		for (Events x : ev1) {
			if (x.getId() == e.getId()) {
				Float y = x.getRating();
				y = rr + 1;

				System.out.println("lahné noté");
				System.out.println(x);

				x.setRating(y);
				eventServiceLocal.update(x);
			}
		}
		this.init();

	}

	public void doDeleteEvent() {
		eventServiceLocal.delete(event);
		this.init();
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public String getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Events> getEvents() {
		return events;
	}

	public void setEvents(List<Events> events) {
		this.events = events;
	}

}
