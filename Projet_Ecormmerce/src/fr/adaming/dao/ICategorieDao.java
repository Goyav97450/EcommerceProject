package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Ewen Fondrillon Interface Dao des méthodes reliées aux
 *         fonctionnalités Categories
 */
@Local
public interface ICategorieDao {

	/**
	 * <b>addCategorie</b> Cette méthode permet à un admin d'ajouter une
	 * catégorie à la base de données.
	 * 
	 * @param la
	 *            catégorie qu'on veut ajouter à la base de données
	 * @return un int généré par la requête pour confirmer l'ajout.
	 */
	public int addCategorie(Categorie ca);

	/**
	 * <b>getAllCategorie</b> Cette méthode permet à un admin de récupérer la
	 * liste complète des categories.
	 * 
	 * @return la liste des categories existantes en base de donnée
	 */
	public List<Categorie> getAllCategorie();
}
