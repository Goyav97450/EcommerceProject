package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Admin;

@Stateless
public class AdminDaoImpl implements IAdminDao{

	@PersistenceContext(unitName = "Projet_Ecormmerce") 
	private EntityManager em;
	
	public Admin isExist(Admin a) {
		em.find(Admin.class, a.getIdAdmin());
		return a;
	}

	
	
}
