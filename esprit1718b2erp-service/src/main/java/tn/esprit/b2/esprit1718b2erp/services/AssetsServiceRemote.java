package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface AssetsServiceRemote extends IGenericDAO<Assets>{
	List<Assets> getAssetsById(int id);
	List<Assets> getAssetsBen();
	List<Assets> getAssetsExp();
	Assets findAssetByName(String name);
}
