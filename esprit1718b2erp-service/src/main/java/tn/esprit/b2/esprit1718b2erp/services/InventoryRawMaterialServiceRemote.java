package tn.esprit.b2.esprit1718b2erp.services;

import java.net.Inet4Address;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.InventoryRawMaterial;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface InventoryRawMaterialServiceRemote extends IGenericDAO<InventoryRawMaterial>{
	List<InventoryRawMaterial> sortByDate();
	List<InventoryRawMaterial> sortByQuantity();
}
