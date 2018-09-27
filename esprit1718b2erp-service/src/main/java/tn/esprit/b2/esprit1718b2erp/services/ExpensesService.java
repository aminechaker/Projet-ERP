package tn.esprit.b2.esprit1718b2erp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.entities.Expenses;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ExpensesService
 */
@Stateless
@LocalBean
public class ExpensesService extends GenericDAO<Expenses> implements ExpensesServiceRemote, ExpensesServiceLocal {
	
	@PersistenceContext
	EntityManager entityManager;
	
    public ExpensesService() {
        super(Expenses.class);
    }


@Override
public List<Assets> getAssetsExp() {
	String jpql="Select A FROM Assets A WHERE A.value >= A.actual_value";
	return entityManager.createQuery(jpql).getResultList();
}
@Override
public List<Expenses> getExpenses() {
	String jpql="Select * FROM Expenses ";
	return entityManager.createQuery(jpql).getResultList();
}


@Override
public java.util.List<Expenses> getExpensesByType(String type) {
	// TODO Auto-generated method stub
	return null;
}	



}
