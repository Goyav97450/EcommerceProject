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
 * Classe implémentant l'interface IClientDao
 * L'anotation @Stateless permet au conteneur EJB de comprendre que c'est un EJB session de type <i>Stateless</i>
 */
@Stateless
public class ClientDaoImpl implements IClientDao{

	/** Récupération d'un entityManager à partir de la PersistenteUnit
	 *  grâce à l'annotation @PersistenceContext
	 */
	@PersistenceContext(unitName="Projet_Ecormmerce")
	private EntityManager em;
	
	public List<Categorie> getAllCategorie() {
		//Création d'une requête JPQL
		String req = "SELECT cat FROM Categorie cat";
		
		//Récupération d'une query
		Query query = em.createQuery(req);
		
		//Récupération de la liste		
		return query.getResultList();
	}

	public List<Produit> getProdByCategorie(Categorie cat) {
		//Création d'une requête JPQL
		String req = "SELECT p FROM Produit p WHERE p.ca.idCategorie=:pCat";
		
		//Récupération d'une query
		Query query = em.createQuery(req);
		
		//Paramétrage de la query
		query.setParameter("pCat", cat.getIdCategorie());
		
		//Récupération de la liste		
		return query.getResultList();
	}

	public List<Produit> getProdByKeyWord(String keyWord) {
		//Création d'une requête JPQL
		String req = "SELECT p FROM Produit p WHERE p.designation LIKE ?1 OR p.description LIKE ?2";
		
		//Récupération d'une query
		Query query = em.createQuery(req);
		
		//Paramétrage de la query
		//Passage de l'entrée en expression like
		String rech = '%' + keyWord + '%';
		query.setParameter(1, rech);
		query.setParameter(2, rech);
		
		//Récupération de la liste		
		return query.getResultList();
	}

	public int saveClient(Client cl) {
		//Application de la méthode persist
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
		//Création d'une requête JPQL
		String req = "SELECT p FROM Produit p WHERE p.designation LIKE :pNom";
		
		//Récupération d'une query
		Query query = em.createQuery(req);
		
		//Paramétrages
		String nom = '%' + rech + '%';
		query.setParameter("pNom", nom);
		
		return (Categorie) query.getSingleResult();
	}

	
	
}
