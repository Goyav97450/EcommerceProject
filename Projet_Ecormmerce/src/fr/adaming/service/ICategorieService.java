package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Ewen L'annotation @Local permet au conteneur EJB de comprendre que
 *         les classes implémentant cette interface seront des EJB session avec
 *         une portée <i>locale</i>
 */

@Local
public interface ICategorieService {
	/**
	 * <b>addCategorie</b> Cette méthode permet à un admin d'ajouter une
	 * catégorie à la base de données.
	 * 
	 * @param la
	 *            catégorie qu'on veut ajouter à la base de données
	 * @return un int généré par la requête pour confirmer l'ajout.
	 */
	public int addCategorieService(Categorie ca);
	
	/**
	 * <b>getAllCategorie</b> Cette méthode permet à un admin de récupérer la
	 * liste complète des categories.
	 * 
	 * @return la liste des categories existantes en base de donnée
	 */
	public List<Categorie> getAllCategorieService();
}
