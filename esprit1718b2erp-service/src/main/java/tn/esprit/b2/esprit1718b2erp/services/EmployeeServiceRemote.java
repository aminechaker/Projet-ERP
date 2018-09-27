package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface EmployeeServiceRemote extends IGenericDAO<Employee> {

	List<Employee> getEmployees();

}
