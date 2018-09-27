package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Claims;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface ClaimsServiceRemote extends IGenericDAO<Claims> {

	List<Claims> getClaimstByClient(Contact contact);

	List<Claims> getClaimsByStatus(String Status);

	List<Claims> getClaimstByClientName(String name);
	List<Claims> selectUntreatedClaims();

}
