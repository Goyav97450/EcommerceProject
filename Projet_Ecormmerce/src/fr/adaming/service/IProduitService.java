package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen L'annotation @Local permet au conteneur EJB de comprendre que
 *         les classes implémentant cette interface seront des EJB session avec
 *         une portée <i>locale</i>
 */

@Local
public interface IProduitService {
	/**
	 * <b>addProduit</b> Cette méthode permet à un admin d'ajouter un produit à
	 * la base de données.
	 * 
	 * @param le
	 *            produit qu'on veut ajouter à la base de données
	 * @return un int généré par la requête pour confirmer l'ajout.
	 */
	public int addProduitService(Produit pr, Categorie ca);

	/**
	 * <b>getAllProduit</b> Cette méthode permet à un admin de récupérer la
	 * liste complète des produits.
	 * 
	 * @return la liste des produits existants en base de donnée
	 */
	public List<Produit> getAllProduitService();

	/**
	 * <b>deleteProduit</b> Cette méthode permet à un admin de supprimer un
	 * produit de la base de données
	 * 
	 * @param le
	 *            produit qu'on veut supprimer de la base de données
	 * @return un int généré par la requête pour confirmer la suppression
	 */
	public int deleteProduitService(Produit pr);

	/**<b>getByIdProduit</b>
	 * Cette méthode permet à un admin de chercher un produit spécifique dans la base de donnée par son identifiant
	 * @param le produit qu'on recherche
	 * @return le produit correspondant à notre paramètre de recherche
	 */
	public Produit getByIdProduitService(Produit pr);
}
