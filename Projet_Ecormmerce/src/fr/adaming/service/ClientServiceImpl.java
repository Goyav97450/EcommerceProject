package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
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
		
		return clDao.saveCommande(co);
	}

	@Override
	public Categorie getCatByNom(String rech) {
		
		return clDao.getCatByNom(rech);
	}

	@Override
	public Panier ajoutProdPanier(Produit prod, int q, Panier panier) {
		
		LigneCommande LC = new LigneCommande();
		LC.setPr(prod);
		LC.setQuantite(q);
		LC.setPrix(prod.getPrix()*q);
		
		panier.getListeCom().add(LC);
		
		return panier;
	}

	@Override
	public int supprProdPanier(Produit prod) {
		// TODO Auto-generated method stub
		return 0;
	}
}
