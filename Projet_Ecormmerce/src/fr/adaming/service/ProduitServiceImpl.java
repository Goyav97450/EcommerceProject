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

	@Override
	public int deleteProduitService(Produit pr) {
		
		return prDao.deleteProduit(pr);
	}

	@Override
	public Produit getByIdProduitService(Produit pr) {
		return prDao.getByIdProduit(pr);
	}

	@Override
	public int updateProduitService(Produit pr) {
		return prDao.updateProduit(pr);
	}

	@Override
	public List<Produit> getProdByCategorie(Categorie cat) {
		return prDao.getProdByCategorie(cat);
	}

	@Override
	public List<Produit> getProdByKeyWord(String keyWord) {
		return prDao.getProdByKeyWord(keyWord);
	}

	@Override
	public List<String> getAllProdIdService() {
		return prDao.getAllProdId();
	}

}
