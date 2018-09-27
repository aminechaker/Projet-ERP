package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface IncidentServiceRemote extends IGenericDAO<Incident> {
	public List<Incident> getIncidentsByTitle(String title);
	public List<Incident> sortIncidentByProject();
	public List<Incident> sortTaskByTask();
	public List<Incident> sortTaskByStatus();

}
