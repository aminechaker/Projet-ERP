package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;


import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface ContactServiceLocal extends IGenericDAO<Contact> {

	List<Contact> getContactByRegion(String Region);
	List<Contact> getContactByActivitySector(String activitySector);
	List<Contact> getContactByType(String type);
	Contact getContactByName(String name);
	List<Contact> getContactById(int id);
	List<Contact> getAllProviders();
	List<Contact> getAllClients();
	List<Contact> getAllCompany();
	List<Contact> FindName(String name);
	List<Contact> searchContactByName(String name);
}
