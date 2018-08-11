package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@EJB
	private IProduitService prService;

	@EJB
	private ICategorieService caService;

	// Attributs
	private Produit pr;
	private Categorie cat;
	private List<Produit> listeProd;
	private List<String> listeIdProd;
	private List<Produit> listeFiltreProd;
	private boolean indice = false;
	private UploadedFile file;
	private String rech;
	private String type;

	public ProduitManagedBean() {
		this.pr = new Produit();
		this.cat = new Categorie();
	}

	// méthode @PostConstruct
	@PostConstruct
	public void init() {

		// récupérer la liste des categories
		listeProd = prService.getAllProduitService();
		listeIdProd = prService.getAllProdIdService();

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
	public List<Produit> getListeProd() {
		return listeProd;
	}

	/**
	 * @param listeProduit
	 *            the listeProduit to set
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
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

	/**
	 * @return the rech
	 */
	public String getRech() {
		return rech;
	}

	/**
	 * @param rech
	 *            the rech to set
	 */
	public void setRech(String rech) {
		this.rech = rech;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the cat
	 */
	public Categorie getCat() {
		return cat;
	}

	/**
	 * @param cat
	 *            the cat to set
	 */
	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	/**
	 * @return the listeIdProd
	 */
	public List<String> getListeIdProd() {
		return listeIdProd;
	}

	/**
	 * @param listeIdProd
	 *            the listeIdProd to set
	 */
	public void setListeIdProd(List<String> listeIdProd) {
		this.listeIdProd = listeIdProd;
	}

	/**
	 * @return the listeFiltreProd
	 */
	public List<Produit> getListeFiltreProd() {
		return listeFiltreProd;
	}

	/**
	 * @param listeFiltreProd
	 *            the listeFiltreProd to set
	 */
	public void setListeFiltreProd(List<Produit> listeFiltreProd) {
		this.listeFiltreProd = listeFiltreProd;
	}

	// Méthodes
	public String addProduit() {
		this.pr.setPhoto(file.getContents());
		int verif = prService.addProduitService(this.pr, cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
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
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué."));
			return "deleteProduit";
		}

	}

	public String rechercherProduitById() {
		Produit prFound = prService.getByIdProduitService(this.pr);

		if (prFound != null) {
			this.pr = prFound;
			this.indice = true;
			return "rechercherProduitById";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherProduitById";
		}

	}

	public String rechercherProduit() {

		switch (type) {
		case "mot":
			// Appel de la méthode
			listeProd = prService.getProdByKeyWord(rech);
			this.indice = true;
			break;
		case "cat":
			// Récupération de la catégorie à partir de la DB
			cat = caService.getCatByNomService(rech);
			// Appel de la méthode
			listeProd = prService.getProdByCategorie(cat);
			this.indice = true;
			break;
		default:
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'avez pas sélectionné le type de recherche que vous voulez faire"));
			this.indice = false;
			break;
		}
		return "rechercherProduit";
	}

	public String updateProduit() {
		this.pr.setPhoto(file.getContents());
		int verif = prService.updateProduitService(this.pr);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			return "accueilAdmin";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification echouée"));
			return "updateProduit";
		}

	}

}
