package fr.adaming.service;


import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen 
 * L'annotation @Local permet au conteneur EJB de comprendre que
 *         les classes impl�mentant cette interface seront des EJB session avec
 *         une port�e <i>locale</i>
 */

@Local
public interface IProduitService {
	/**
	 * <b>addProduit</b> Cette m�thode permet � un admin d'ajouter un produit �
	 * la base de donn�es.
	 * 
	 * @param le
	 *            produit qu'on veut ajouter � la base de donn�es
	 * @return un int g�n�r� par la requ�te pour confirmer l'ajout.
	 */
	public int addProduitService(Produit pr, Categorie ca);
}
