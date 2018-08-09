package fr.adaming.service;


import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen 
 * L'annotation @Local permet au conteneur EJB de comprendre que
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
}
