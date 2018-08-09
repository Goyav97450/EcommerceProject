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
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "Projet_Ecormmerce")
	private EntityManager em;

	@Override
	public int addCategorie(Categorie ca) {
		// Requete JPQL pour obtenir la liste des produits
		String reqListCategorie = "SELECT ca FROM Categorie as ca";

		Query query = em.createQuery(reqListCategorie);

		List<Categorie> oldListCategorie = query.getResultList();

		em.persist(ca);

		// Requete JPQL pour obtenir la liste des produits
		Query queryNew = em.createQuery(reqListCategorie);

		List<Categorie> newListCategorie = queryNew.getResultList();

		if (oldListCategorie.size() != newListCategorie.size()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// Requete JPQL pour obtenir la liste des produits
		String reqListCategorie = "SELECT ca FROM Categorie as ca";

		Query query = em.createQuery(reqListCategorie);

		List<Categorie> listCategorie = query.getResultList();

		for (Categorie ca : listCategorie) {
			ca.setImage("data:image/png);base64," + Base64.encodeBase64String(ca.getPhoto()));
		}

		return listCategorie;
	}

	@Override
	public int deleteCategorie(Categorie ca) {
		String req = "DELETE FROM Categorie as ca WHERE ca.idCategorie=:pIdca";

		Query query = em.createQuery(req);
		
		query.setParameter("pIdca", ca.getIdCategorie());

		return query.executeUpdate();
	}

	@Override
	public int updateCategorie(Categorie ca) {
		// Requête JPQL
		String req = "UPDATE Categorie ca SET ca.description=:pDes, ca.photo=:pPho, ca.nomCategorie=:pNom WHERE ca.idCategorie=:pIdca";

		// Récupérer le query
		Query query = em.createQuery(req);

		// paramètres
		query.setParameter("pDes", ca.getDescription());
		query.setParameter("pPho", ca.getPhoto());
		query.setParameter("pNom", ca.getNomCategorie());
		query.setParameter("pIdca", ca.getIdCategorie());

		int verif = query.executeUpdate();
		return verif;
	}

	@Override
	public Categorie getByIdCategorie(Categorie ca) {
		return em.find(Categorie.class , ca.getIdCategorie());
	}

}
