package tn.esprit.b2.esprit1718b2erp.services;

import java.awt.Event;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface EventServiceRemote extends IGenericDAO<Events> {
	
	List<Events> findEventByName(String name);
	List<Events> findEventByDate(Date date);
	List<Events> sortByLocalisation(String localisation);
	List<Events> sortBySubject(String subject);
	List<Events> sortEventByDate();
}
