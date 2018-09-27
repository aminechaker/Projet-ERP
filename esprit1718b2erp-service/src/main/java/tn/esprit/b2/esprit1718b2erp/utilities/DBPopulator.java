package tn.esprit.b2.esprit1718b2erp.utilities;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.b2.esprit1718b2erp.entities.User;
import tn.esprit.b2.esprit1718b2erp.services.UserServiceLocal;

@Singleton
@Startup
public class DBPopulator {
	@EJB
	private UserServiceLocal userServiceLocal;

	public DBPopulator() {
	}

	/*@PostConstruct
	public void init() {
		User user = new User(0, "user", "u", "u", "user@bitbox.tn", null, null, null, null, null, null, null, null, null);

		userServiceLocal.update(user);
	}*/
}
