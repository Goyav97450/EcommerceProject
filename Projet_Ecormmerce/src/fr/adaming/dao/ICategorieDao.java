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
}
