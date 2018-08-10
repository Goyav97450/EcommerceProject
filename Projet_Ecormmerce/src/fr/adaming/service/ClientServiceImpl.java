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
	public LigneCommande ajoutProdPanier(Produit prod, int q) {
		
		LigneCommande LC = new LigneCommande();
		if (q<=prod.getQuantite()) {
			LC.setPr(prod);
			LC.setQuantite(q);
			LC.setPrix(prod.getPrix()*q);
			return LC;
		} else {
			return null;
		}
		
	}

	@Override
	public int supprProdPanier(Produit prod, Panier panier) {
		
		for(LigneCommande lc: panier.getListeCom()) {
			if (lc.getPr().getIdProduit()==prod.getIdProduit()) {
				panier.getListeCom().remove(lc);
			}
		}
		
		return 0;
	}
}
