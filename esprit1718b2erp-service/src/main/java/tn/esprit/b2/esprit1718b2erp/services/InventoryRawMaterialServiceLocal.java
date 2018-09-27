package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.InventoryRawMaterial;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface InventoryRawMaterialServiceLocal extends IGenericDAO<InventoryRawMaterial> {
	List<InventoryRawMaterial> sortByDate();
	List<InventoryRawMaterial> sortByQuantity();
}
