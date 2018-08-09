package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;

/**
 * @author Thibault
 * L'annotation @Stateful d�finit la classe comme un EJB Session  qui sera istanciable plusieurs fois
 */
@Stateful
public class ClientServiceImpl implements IClientService{

	/**
	 * Transformation de l'association entre service et dao
	 * L'annotation @EJB sert � d�finir le partage des t�ches
	 */
	@EJB
	private IClientDao clDao;

	@Override
	public List<Categorie> getAllCategorie() {
		
		return clDao.getAllCategorie();
	}

	@Override
	public List<Produit> getProdByCategorie(Categorie cat) {
		
		return clDao.getProdByCategorie(cat);
	}

	@Override
	public List<Produit> getProdByKeyWord(String keyWord) {
		
		return clDao.getProdByKeyWord(keyWord);
	}

	@Override
	public int saveClient(Client cl) {
		
		return clDao.saveClient(cl);
	}

	@Override
	public int saveCommande(Commande co) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCatByNom(String rech) {
		
		return clDao.getCatByNom(rech);
	}
}
