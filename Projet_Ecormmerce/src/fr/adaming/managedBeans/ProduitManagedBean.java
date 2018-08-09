package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	private List<Produit> listeProduit;

	public ProduitManagedBean() {
		this.pr = new Produit();
		this.ca = new Categorie();
	}

	// m�thode @PostConstruct
	@PostConstruct
	public void init() {

		// r�cup�rer la liste des produits
		listeProduit = prService.getAllProduitService();

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
	 * @param ca
	 *            the ca to set
	 */
	public void setCa(Categorie ca) {
		this.ca = ca;
	}

	/**
	 * @return the listeProduit
	 */
	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	/**
	 * @param listeProduit
	 *            the listeProduit to set
	 */
	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	// M�thodes
	public String addProduit() {

		int verif = prService.addProduitService(this.pr, ca);

		if (verif != 0) {
			// r�cup�rer la nouvelle liste de la BD
			List<Produit> newListeProduit = prService.getAllProduitService();

			// mettre � jour la liste dans l'attribut du MB
			listeProduit = newListeProduit;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou�."));
			return "accueilAdmin";
		}

	}

}