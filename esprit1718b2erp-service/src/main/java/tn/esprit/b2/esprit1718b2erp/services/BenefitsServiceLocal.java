package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;


import tn.esprit.b2.esprit1718b2erp.entities.Benefits;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface BenefitsServiceLocal extends IGenericDAO<Benefits> {

	

	List<Benefits> getBenefitsById(int id);

	List<Benefits> getBenefitsByType(String type);

	List<Benefits> getBenefits();

}
