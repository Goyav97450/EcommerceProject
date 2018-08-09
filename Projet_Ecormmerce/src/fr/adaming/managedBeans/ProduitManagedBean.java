package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@EJB
	private IProduitService prService;

	// Attributs
	private Produit pr;
	private Categorie cat;
	private List<Produit> listeProduit;
	private boolean indice = false;
	private UploadedFile file;

	public ProduitManagedBean() {
		this.pr = new Produit();
		this.cat = new Categorie();
	}

	// méthode @PostConstruct
	@PostConstruct
	public void init() {

		// récupérer la liste des produits
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
		return cat;
	}

	/**
	 * @param ca
	 *            the ca to set
	 */
	public void setCa(Categorie cat) {
		this.cat = cat;
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

	/**
	 * @return the indice
	 */
	public boolean isIndice() {
		return indice;
	}

	/**
	 * @param indice
	 *            the indice to set
	 */
	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	// Méthodes
	public String addProduit() {
		this.pr.setPhoto(file.getContents());
		int verif = prService.addProduitService(this.pr, cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProduit = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProduit = newListeProduit;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué."));
			return "accueilAdmin";
		}

	}

	public String deleteProduit() {

		int verif = prService.deleteProduitService(pr);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProduit = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProduit = newListeProduit;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué."));
			return "deleteProduit";
		}

	}

	public String rechercherProduit() {
		Produit prFound = prService.getByIdProduitService(this.pr);

		if (prFound != null) {
			this.pr = prFound;
			this.indice = true;
			return "accueilAdmin";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherProduit";
		}

	}

	public String updateProduit() {
		this.pr.setPhoto(file.getContents());
		int verif = prService.updateProduitService(this.pr);

		if (verif!=0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProduit = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProduit = newListeProduit;
			return "accueilAdmin";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification echouée"));
			return "updateProduit";
		}

	}

}
