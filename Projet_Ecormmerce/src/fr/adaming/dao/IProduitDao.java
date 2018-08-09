package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Produit;

/**
 * @author Ewen Fondrillon Interface Dao des m�thodes reli�es aux
 *         fonctionnalit�s Produits
 */
@Local
public interface IProduitDao {

	/**
	 * <b>addProduit</b> Cette m�thode permet � un admin d'ajouter un produit �
	 * la base de donn�es.
	 * 
	 * @param le
	 *            produit qu'on veut ajouter � la base de donn�es
	 * @return un int g�n�r� par la requ�te pour confirmer l'ajout.
	 */
	public int addProduit(Produit pr);

	/**
	 * <b>getAllProduit</b> Cette m�thode permet � un admin de r�cup�rer la
	 * liste compl�te des produits.
	 * 
	 * @return la liste des produits existants en base de donn�e
	 */
	public List<Produit> getAllProduit();

}
