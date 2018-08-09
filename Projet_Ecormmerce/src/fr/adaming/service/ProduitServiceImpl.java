package fr.adaming.service;



import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {
	// Transformation de l'association UML en Java
	@EJB
	IProduitDao prDao;

	@Override
	public int addProduitService(Produit pr, Categorie ca) {
		pr.setCa(ca);
		return prDao.addProduit(pr);
	}

	@Override
	public List<Produit> getAllProduitService() {
		return prDao.getAllProduit();
	}

}
