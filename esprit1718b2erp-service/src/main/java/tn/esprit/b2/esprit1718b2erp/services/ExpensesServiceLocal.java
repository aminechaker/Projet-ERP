package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.entities.Expenses;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface ExpensesServiceLocal extends IGenericDAO<Expenses> {

	List<Expenses> getExpensesByType(String type);

	List<Assets> getAssetsExp();
	List<Expenses> getExpenses();
	
}
