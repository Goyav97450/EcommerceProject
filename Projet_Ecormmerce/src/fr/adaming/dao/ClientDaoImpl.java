/**
 * 
 */
package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;

/**
 * @author Thibault
 * Classe impl�mentant l'interface IClientDao
 * L'anotation @Stateless permet au conteneur EJB de comprendre que c'est un EJB session de type <i>Stateless</i>
 */
@Stateless
public class ClientDaoImpl implements IClientDao{

	/** R�cup�ration d'un entityManager � partir de la PersistenteUnit
	 *  gr�ce � l'annotation @PersistenceContext
	 */
	@PersistenceContext(unitName="Projet_Ecormmerce")
	private EntityManager em;
	
	public List<Categorie> getAllCategorie() {
		//Cr�ation d'une requ�te JPQL
		String req = "SELECT cat FROM Categorie cat";
		
		//R�cup�ration d'une query
		Query query = em.createQuery(req);
		
		//R�cup�ration de la liste		
		return query.getResultList();
	}

	public List<Produit> getProdByCategorie(Categorie cat) {
		//Cr�ation d'une requ�te JPQL
		String req = "SELECT p FROM Produit p WHERE p.ca.idCategorie=:pCat";
		
		//R�cup�ration d'une query
		Query query = em.createQuery(req);
		
		//Param�trage de la query
		query.setParameter("pCat", cat.getIdCategorie());
		
		//R�cup�ration de la liste		
		return query.getResultList();
	}

	public List<Produit> getProdByKeyWord(String keyWord) {
		//Cr�ation d'une requ�te JPQL
		String req = "SELECT p FROM Produit p WHERE p.designation LIKE ?1 OR p.description LIKE ?2";
		
		//R�cup�ration d'une query
		Query query = em.createQuery(req);
		
		//Param�trage de la query
		//Passage de l'entr�e en expression like
		String rech = '%' + keyWord + '%';
		query.setParameter(1, rech);
		query.setParameter(2, rech);
		
		//R�cup�ration de la liste		
		return query.getResultList();
	}

	public int saveClient(Client cl) {
		//Application de la m�thode persist
		try {
			em.persist(cl);
			return 1;
		} catch (Exception EntityExistsException){
			return 0;
		}
	}

	public int saveCommande(Commande co) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCatByNom(String rech) {
		//Cr�ation d'une requ�te JPQL
		String req = "SELECT p FROM Produit p WHERE p.designation LIKE :pNom";
		
		//R�cup�ration d'une query
		Query query = em.createQuery(req);
		
		//Param�trages
		String nom = '%' + rech + '%';
		query.setParameter("pNom", nom);
		
		return (Categorie) query.getSingleResult();
	}

	
	
}
