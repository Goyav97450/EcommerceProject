package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName = "Projet_Ecormmerce")
	private EntityManager em;

	@Override
	public int addProduit(Produit pr) {

		// Requete JPQL pour obtenir la liste des produits
		String reqListProduit = "SELECT pr FROM Produit as pr";

		Query query = em.createQuery(reqListProduit);

		List<Produit> oldListProduit = query.getResultList();

		em.persist(pr);

		// Requete JPQL pour obtenir la liste des produits avec l'ajout
		String reqNewListProduit = "SELECT pr FROM Produit as pr";

		Query queryNew = em.createQuery(reqNewListProduit);

		List<Produit> newListProduit = queryNew.getResultList();

		if (oldListProduit.size() != newListProduit.size()) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<Produit> getAllProduit() {
		// Requete JPQL pour obtenir la liste des produits
		String reqListProduit = "SELECT pr FROM Produit as pr";

		Query query = em.createQuery(reqListProduit);

		List<Produit> listProduit = query.getResultList();

		for (Produit pr : listProduit) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}

		return listProduit;
	}

	@Override
	public int deleteProduit(Produit pr) {
		String req = "DELETE FROM Produit as pr WHERE pr.idProduit=:pIdp";

		Query query = em.createQuery(req);

		query.setParameter("pIdp", pr.getIdProduit());

		return query.executeUpdate();

	}

	@Override
	public Produit getByIdProduit(Produit pr) {
		try {
			Produit prFound = em.find(Produit.class, pr.getIdProduit());
			prFound.setImage("data:image/png);base64," + Base64.encodeBase64String(prFound.getPhoto()));
			return prFound;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateProduit(Produit pr) {
		// Requ�te JPQL
		String req = "UPDATE Produit pr SET pr.description=:pDes, pr.designation=:pDesi, pr.photo=:pPho, pr.prix=:pPrix, pr.quantite=:pQua WHERE pr.idProduit=:pIdp";

		// R�cup�rer le query
		Query query = em.createQuery(req);

		// param�tres
		query.setParameter("pDes", pr.getDescription());
		query.setParameter("pDesi", pr.getDesignation());
		query.setParameter("pPho", pr.getPhoto());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQua", pr.getQuantite());
		query.setParameter("pIdp", pr.getIdProduit());

		int verif = query.executeUpdate();
		return verif;
	}

	public List<Produit> getProdByCategorie(Categorie cat) {
		try {// Cr�ation d'une requ�te JPQL
			String req = "SELECT p FROM Produit p WHERE p.ca.idCategorie=:pCat";

			// R�cup�ration d'une query
			Query query = em.createQuery(req);

			// Param�trage de la query
			query.setParameter("pCat", cat.getIdCategorie());

			// R�cup�ration de la liste
			List<Produit> listProduit = query.getResultList();

			for (Produit pr : listProduit) {
				pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
			}

			return listProduit;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	public List<Produit> getProdByKeyWord(String keyWord) {
		// Cr�ation d'une requ�te JPQL
		String req = "SELECT p FROM Produit p WHERE p.designation LIKE ?1 OR p.description LIKE ?2";

		// R�cup�ration d'une query
		Query query = em.createQuery(req);

		// Param�trage de la query
		// Passage de l'entr�e en expression like
		String rech = '%' + keyWord + '%';
		query.setParameter(1, rech);
		query.setParameter(2, rech);

		// R�cup�ration de la liste
		List<Produit> listProduit = query.getResultList();

		for (Produit pr : listProduit) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return listProduit;
	}

	@Override
	public List<String> getAllProdId() {
		// Requete JPQL pour obtenir la liste des produits
		String reqListCategorie = "SELECT pr.idProduit FROM Produit as pr";

		Query query = em.createQuery(reqListCategorie);

		List<String> listIdProduit = query.getResultList();

		return listIdProduit;
	}

}
