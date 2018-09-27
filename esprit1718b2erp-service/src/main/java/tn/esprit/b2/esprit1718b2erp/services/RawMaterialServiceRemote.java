package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface RawMaterialServiceRemote extends IGenericDAO<RawMaterial> {
	RawMaterial findRawMaterialByDescription(String description);
	List<RawMaterial> sortByQuantity();
}
