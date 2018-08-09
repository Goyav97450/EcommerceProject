package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Ewen Fondrillon Interface Dao des m�thodes reli�es aux
 *         fonctionnalit�s Categories
 */
@Local
public interface ICategorieDao {

	/**
	 * <b>addCategorie</b> Cette m�thode permet � un admin d'ajouter une
	 * cat�gorie � la base de donn�es.
	 * 
	 * @param la
	 *            cat�gorie qu'on veut ajouter � la base de donn�es
	 * @return un int g�n�r� par la requ�te pour confirmer l'ajout.
	 */
	public int addCategorie(Categorie ca);

	/**
	 * <b>getAllCategorie</b> Cette m�thode permet � un admin de r�cup�rer la
	 * liste compl�te des categories.
	 * 
	 * @return la liste des categories existantes en base de donn�e
	 */
	public List<Categorie> getAllCategorie();

	/**
	 * <b>deleteCategorie</b> Cette m�thode permet � un admin de supprimer une
	 * categorie de la base de donn�es
	 * 
	 * @param la
	 *            categorie qu'on veut supprimer de la base de donn�es
	 * @return la categorie qu'on a supprim� de la base, pour confirmer la
	 *         suppression
	 */
	public Categorie deleteCategorie(Categorie pr);

	/**
	 * <b>updateCategorie</b> Cette m�thode permet � un admin de mettre � jour
	 * une categorie de la base de donn�es
	 * 
	 * @param le
	 *            produit qu'on veut mettre � jour dans la base de donn�es
	 * @return le produit qu'on a mis � jour dans la base, pour confirmer la
	 *         mise � jour
	 */
	public Categorie updateCategorie(Categorie pr);

	/**
	 * <b>getByIdCategorie</b> Cette m�thode permet � un admin de chercher une
	 * categorie sp�cifique dans la base de donn�e par son identifiant
	 * 
	 * @param le
	 *            produit qu'on recherche
	 * @return le produit correspondant � notre param�tre de recherche
	 */
	public Categorie getByIdCategorie(Categorie pr);
}
