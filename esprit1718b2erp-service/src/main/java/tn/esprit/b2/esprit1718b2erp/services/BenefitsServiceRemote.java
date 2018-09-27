package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Benefits;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface BenefitsServiceRemote extends IGenericDAO<Benefits> {

	List<Benefits> getBenefitsById();
	List<Benefits> getBenefitsByType(String type);
	List<Benefits> getBenefits();

}
