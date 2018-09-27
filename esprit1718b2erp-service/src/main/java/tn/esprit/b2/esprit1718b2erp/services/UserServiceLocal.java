package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.User;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface UserServiceLocal extends IGenericDAO<User> {

	User login(String login, String password);

}
