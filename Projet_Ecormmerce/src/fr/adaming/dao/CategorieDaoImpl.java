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
		try {
			em.remove(ca);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;

	}

	@Override
	public Categorie getByIdCategorie(Categorie ca) {
		Categorie caFound = em.find(Categorie.class, ca.getIdCategorie());
		caFound.setImage("data:image/png;base64," + Base64.encodeBase64String(ca.getPhoto()));
		return caFound;
	}

	@Override
	public Categorie updateCategorie(Categorie ca) {
		Categorie caDB = em.find(Categorie.class, ca.getIdCategorie());

		caDB = ca;

		em.merge(caDB);

		Categorie caOut = em.find(Categorie.class, caDB.getIdCategorie());

		return caOut;
	}

	@Override
	public Categorie getCatByNom(String rech) {
		// Création d'une requête JPQL
		String req = "SELECT cat FROM Categorie cat WHERE cat.nomCategorie LIKE :pNom";

		// Récupération d'une query
		Query query = em.createQuery(req);

		// Paramétrages
		String nom = '%' + rech + '%';
		query.setParameter("pNom", nom);
		Categorie ca = (Categorie) query.getSingleResult();
		ca.setImage("data:image/png);base64," + Base64.encodeBase64String(ca.getPhoto()));

		return ca;
	}

}
