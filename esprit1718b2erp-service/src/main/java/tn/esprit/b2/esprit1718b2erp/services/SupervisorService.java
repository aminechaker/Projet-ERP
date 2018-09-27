package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Supervisor;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class SupervisorService
 */
@Stateless
@LocalBean
public class SupervisorService extends GenericDAO<Supervisor> implements SupervisorServiceRemote, SupervisorServiceLocal {

    /**
     * Default constructor. 
     */
    public SupervisorService() {
       super(Supervisor.class);
    }

}
