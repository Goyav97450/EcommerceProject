/**
 * 
 */
package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;

/**
 * @author Thibault
 * Interface Dao des méthodes relier aux clients
 */
public interface IClientDao {
	
	/**<b>getAllCategorie</b>
	 * Cette méthode permet à un Client de récupérer la liste des catégories depuis la DB
	 * @return la liste des catégories présentent dans la DB
	 */
	public List<Categorie> getAllCategorie();
	

	public List<Produit> getProdByCategorie(Categorie cat);
	

	public List<Produit> getProdByKeyWord (String keyWord);
	

	public int saveClient(Client cl);
	

	public int saveCommande(Commande co);
}
