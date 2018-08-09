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
 * L'annotation @Stateful définit la classe comme un EJB Session  qui sera istanciable plusieurs fois
 */
@Stateful
public class ClientServiceImpl implements IClientService{

	/**
	 * Transformation de l'association entre service et dao
	 * L'annotation @EJB sert à définir le partage des tâches
	 */
	@EJB
	private IClientDao clDao;

	@Override
	public List<Categorie> getAllCategorie() {
		
		return clDao.getAllCategorie();
	}

	@Override
	public List<Produit> getProdByCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProdByKeyWord(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveCommande(Commande co) {
		// TODO Auto-generated method stub
		return 0;
	}
}
