package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface EmployeeServiceLocal extends IGenericDAO<Employee> {

}
