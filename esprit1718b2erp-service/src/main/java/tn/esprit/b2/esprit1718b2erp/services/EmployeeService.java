package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class EmployeeService
 */
@Stateless
@LocalBean
public class EmployeeService extends GenericDAO<Employee> implements EmployeeServiceRemote, EmployeeServiceLocal {

    /**
     * Default constructor. 
     */
    public EmployeeService() {
    	super(Employee.class);
    }

	@Override
	public List<Employee> getEmployees() {
		String jpql = "SELECT * FROM Employee E ";
		return null;
	}



}
