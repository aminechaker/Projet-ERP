package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface RawMaterialServiceLocal extends IGenericDAO<RawMaterial> {
	RawMaterial findRawMaterialByDescription(String description);
	List<RawMaterial> sortByQuantity();

}
