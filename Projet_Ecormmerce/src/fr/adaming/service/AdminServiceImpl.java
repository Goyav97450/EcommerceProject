package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Stateful
public class AdminServiceImpl implements IAdminService{

	//Transformation de l'association UML en Java
	@EJB
	private IAdminDao aDao;
	
	public Admin isExistService(Admin a) {
		
		return aDao.isExist(a);
	}

	
	
}
