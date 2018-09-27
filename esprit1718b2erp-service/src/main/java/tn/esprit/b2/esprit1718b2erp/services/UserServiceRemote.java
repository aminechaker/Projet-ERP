package tn.esprit.b2.esprit1718b2erp.services;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.User;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface UserServiceRemote extends IGenericDAO<User> {
	User login(String login, String password);
}
