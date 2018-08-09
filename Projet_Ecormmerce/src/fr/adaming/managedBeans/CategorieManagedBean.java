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

@ManagedBean(name = "cAMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@EJB
	private ICategorieService caService;

	// Attributs
	private Categorie cat;
	private UploadedFile file;
	private List<Categorie> listeCategorie;
	private boolean indice;

	public CategorieManagedBean() {
		this.cat = new Categorie();
	}

	// méthode @PostConstruct
	@PostConstruct
	public void init() {

		// récupérer la liste des categories
		listeCategorie = caService.getAllCategorieService();

	}

	/**
	 * @return the ca
	 */
	public Categorie getCat() {
		return cat;
	}

	/**
	 * @param ca
	 *            the ca to set
	 */
	public void setCat(Categorie cat) {
		this.cat = cat;
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
	 * @return the listeCategorie
	 */
	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	/**
	 * @param listeCategorie
	 *            the listeCategorie to set
	 */
	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
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

	// Méthodes
	public String addCategorie() {
		this.cat.setPhoto(file.getContents());
		int verif = caService.addCategorieService(this.cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCategorie = caService.getAllCategorieService();

			// mettre à jour la liste dans l'attribut du MB
			listeCategorie = newListeCategorie;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué."));
			return "ajoutCategorie";
		}

	}

	public String deleteCategorie() {

		int verif = caService.deleteCategorieService(this.cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCategorie = caService.getAllCategorieService();

			// mettre à jour la liste dans l'attribut du MB
			listeCategorie = newListeCategorie;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué."));
			return "deleteCategorie";
		}

	}

	public String rechercherCategorie() {
		Categorie catFound = caService.getByIdCategorieService(this.cat);

		if (catFound != null) {
			this.cat = catFound;
			this.indice = true;
			return "accueilAdmin";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit introuvable"));
			return "rechercherProduit";
		}

	}

	public String updateCategorie() {
		this.cat.setPhoto(file.getContents());
		int verif = caService.updateCategorieService(this.cat);
		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCategorie = caService.getAllCategorieService();

			// mettre à jour la liste dans l'attribut du MB
			listeCategorie = newListeCategorie;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La mise à jour a échoué."));
			return "updateCategorie";
		}
	}

}
