package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface AssetsServiceLocal extends IGenericDAO<Assets>{
	List<Assets> getAssetsById(int id);
	List<Assets> getAssetsByType(String type);
	List<Assets> getAssetsBen();
	List<Assets> getAssetsExp();
	Assets findAssetByName(String name);
	List<String> getAssetsByType();
}
