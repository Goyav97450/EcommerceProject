package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prMB")
@SessionScoped
public class ProduitManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@EJB
	private IProduitService prService;

	// Attributs
	private Produit pr;
	private Categorie ca;

	public ProduitManagedBean() {
		this.pr = new Produit();
	}

	/**
	 * @return the pr
	 */
	public Produit getPr() {
		return pr;
	}

	/**
	 * @param pr
	 *            the pr to set
	 */
	public void setPr(Produit pr) {
		this.pr = pr;
	}

	/**
	 * @return the ca
	 */
	public Categorie getCa() {
		return ca;
	}

	/**
	 * @param ca the ca to set
	 */
	public void setCa(Categorie ca) {
		this.ca = ca;
	}

	// Méthodes
	public String addProduit() {

		int verif = prService.addProduitService(this.pr, ca);

		if (verif != 0) {
			return "accueilAdmin";
		} else {
			return "accueilAdmin";
		}

	}

}
