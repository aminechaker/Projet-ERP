package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.entities.Expenses;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Remote
public interface ExpensesServiceRemote extends IGenericDAO<Expenses> {

	List<Assets> getAssetsExp();

	List<Expenses> getExpensesByType(String type);

	List<Expenses> getExpenses();
	
}
