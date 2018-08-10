package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService{
	// Transformation de l'association UML en Java
		@EJB
		ICategorieDao caDao;

		@Override
		public int addCategorieService(Categorie ca) {
			return caDao.addCategorie(ca);
		}

		@Override
		public List<Categorie> getAllCategorieService() {
			return caDao.getAllCategorie();
		}

		@Override
		public int deleteCategorieService(Categorie ca) {
			return caDao.deleteCategorie(ca);
		}

		@Override
		public Categorie updateCategorieService(Categorie ca) {
			return caDao.updateCategorie(ca);
		}

		@Override
		public Categorie getByIdCategorieService(Categorie ca) {
			return caDao.getByIdCategorie(ca);
		}
		
		
	
	
}
