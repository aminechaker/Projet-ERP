package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Supervisor;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface SupervisorServiceRemote extends IGenericDAO<Supervisor> {

}
