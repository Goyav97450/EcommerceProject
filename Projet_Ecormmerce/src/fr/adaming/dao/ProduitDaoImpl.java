package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

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

		return em.find(Produit.class, pr.getIdProduit());
	}

	@Override
	public int updateProduit(Produit pr) {
		// Requête JPQL
		String req = "UPDATE Produit pr SET pr.description=:pDes, pr.designation=:pDesi, pr.photo=:pPho, pr.prix=:pPrix, pr.quantite=:pQua WHERE pr.idProduit=:pIdp";

		// Récupérer le query
		Query query = em.createQuery(req);

		// paramètres
		query.setParameter("pDes", pr.getDescription());
		query.setParameter("pDesi", pr.getDesignation());
		query.setParameter("pPho", pr.getPhoto());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQua", pr.getQuantite());
		query.setParameter("pIdp", pr.getIdProduit());

		int verif = query.executeUpdate();
		return verif;
	}

}
