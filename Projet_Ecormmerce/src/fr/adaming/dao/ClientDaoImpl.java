/**
 * 
 */
package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<Produit> getProdByKeyWord(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	public int saveClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int saveCommande(Commande co) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
