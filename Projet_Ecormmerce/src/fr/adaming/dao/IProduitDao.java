package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Produit;

/**
 * @author Ewen Fondrillon
 * Interface Dao des méthodes reliées aux fonctionnalités Produits
 */
@Local
public interface IProduitDao {

	/**<b>addProduit</b>
	 * Cette méthode permet à un admin d'ajouter un produit à la base de données.
	 * @param le produit qu'on veut ajouter à la base de données
	 * @return un int généré par la requête pour confirmer l'ajout.
	 */
	public int addProduit(Produit pr);
	
}
