package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "cAMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@EJB
	private ICategorieService caService;

	@EJB
	private IProduitService prService;

	// Attributs
	private Categorie cat;
	private UploadedFile file;
	private List<Categorie> listeCat;
	private List<Produit> listeProd;
	private boolean indice = false;
	private boolean catSelector = false;
	private boolean idSelector = true;
	private String rech;

	public CategorieManagedBean() {
		this.cat = new Categorie();
	}

	// méthode @PostConstruct
	@PostConstruct
	public void init() {

		// récupérer la liste des categories
		listeCat = caService.getAllCategorieService();
		listeProd = prService.getAllProduitService();

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
	public List<Categorie> getListeCat() {
		return listeCat;
	}

	/**
	 * @param listeCategorie
	 *            the listeCategorie to set
	 */
	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
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
	 * @return the listeProd
	 */
	public List<Produit> getListeProd() {
		return listeProd;
	}

	/**
	 * @param listeProd
	 *            the listeProd to set
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
	}

	/**
	 * @return the type
	 */

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
	 * @return the catSelector
	 */
	public boolean isCatSelector() {
		return catSelector;
	}

	/**
	 * @param catSelector
	 *            the catSelector to set
	 */
	public void setCatSelector(boolean catSelector) {
		this.catSelector = catSelector;
	}

	/**
	 * @return the idSelector
	 */
	public boolean isIdSelector() {
		return idSelector;
	}

	/**
	 * @param idSelector
	 *            the idSelector to set
	 */
	public void setIdSelector(boolean idSelector) {
		this.idSelector = idSelector;
	}

	// Méthodes
	public String addCategorie() {
		this.cat.setPhoto(file.getContents());
		int verif = caService.addCategorieService(this.cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();

			// mettre à jour la liste dans l'attribut du MB
			listeCat = newListeCat;
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
			List<Categorie> newListeCat = caService.getAllCategorieService();
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			listeCat = newListeCat;
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
			return "rechercherCategorie";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherCategorie";
		}

	}

	public String rechercherCategorieParNom() {
		// Récupération de la catégorie à partir de la DB
		Categorie catFound = caService.getCatByNom(rech);

		if (catFound != null) {
			this.cat = catFound;
			this.indice = true;
			return "rechercherCategorie";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherCategorie";
		}

	}

	public String updateCategorie() {
		this.cat.setPhoto(file.getContents());
		Categorie caOut = caService.updateCategorieService(this.cat);
		if (caOut != null) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();

			// mettre à jour la liste dans l'attribut du MB
			listeCat = newListeCat;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La mise à jour a échoué."));
			return "updateCategorie";
		}
	}

	public void changeType(ValueChangeEvent e) {
		this.catSelector = true;
		this.idSelector = false;
	}

}
