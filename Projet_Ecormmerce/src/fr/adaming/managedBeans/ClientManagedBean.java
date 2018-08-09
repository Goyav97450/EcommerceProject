/**
 * 
 */
package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;

/**
 * @author Thibault
 * ManagedBean correspondant � la vue Client
 */
@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	/**
	 * Transformation de l'association UML en JAVA
	 * L'annotation @EJB permet d'�tablir un couplage faible entre les services.
	 */
	@EJB
	private IClientService clService;
	//Attributs
	/**
	 * Attribut client qui servira au client pour s'enregistrer
	 */
	private Client cl;
	/**
	 * Attribut commande qui servira � enregistrer/afficher des commandes
	 */
	private Commande co;
	/**
	 * Attribut commande qui servira � afficher le panier et y rajouter des produits
	 */
	private Panier panier;
	/**
	 * Attribut produit servant � l'affichage des produits
	 */
	private Produit prod;
	/**
	 * Attribut cat�gorie pour r�cup�rer l'input client correspondant au choix de cat�gorie pour la recherche de produit
	 */
	private Categorie cat;
	/**
	 * Attribut liste de cat�gories servant � afficher la liste des cat�gories
	 */
	private List<Categorie> listeCat;
	/**
	 * Attribut liste de produits servant � afficher la liste des produits selon une recherche
	 */
	private List<Produit> listeProd;
	/**
	 * Attribut qui servira a afficher ou non des tables
	 */
	private boolean ind;
	/**
	 * Attribut correpsondant au nom de la cat�gorie ou le(s) mot(s) cl�(s) utilis�s par le client pour rechercher des produits
	 */
	private String rech;
	/**
	 * Attribut correspond au choix du type de recherhe par mots cl�s ou cat�gorie
	 */
	private String type;
	/**
	 * Constructeur vide n�cessaire � un ManagedBean
	 */
	public ClientManagedBean() {
		super();
	}
	
	/**
	 * Cette m�thode permet de r�cup�rer automatiquement la liste des cat�gories
	 * L'annotation @PostConstruct permet de r�aliser cette op�ration d�s le lancement
	 */
	@PostConstruct
	public void init() {
		this.listeCat = clService.getAllCategorie();
		ind = false;
	}

	/**
	 * @return le client s'�tant enregistrer
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * @param set le client du MB pour pouvoir r�cup�rer ses donn�es � enregistrer
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}

	/**
	 * @return la commande r�aliser par le client
	 */
	public Commande getCo() {
		return co;
	}

	/**
	 * @param set la commande afin de r�cup�rer les inputs du client
	 */
	public void setCo(Commande co) {
		this.co = co;
	}

	/**
	 * @return pas vraiment utile
	 */
	public Categorie getCat() {
		return cat;
	}

	/**
	 * @param set une cat�gorie qui servira de filtre pour la m�thode affProdByCat()
	 */
	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	/**
	 * @return affiche le panier et les donn�es contenant dans le panier client
	 */
	public Panier getPanier() {
		return panier;
	}

	/**
	 * @param set le panier du MB n�cessaire pour stocker les courses d'un client
	 */
	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	/**
	 * @return le produit choisie
	 */
	public Produit getProd() {
		return prod;
	}

	/**
	 * @param set un produit depusi un input du client
	 */
	public void setProd(Produit prod) {
		this.prod = prod;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return un boolean qui servira a afficher ou nom des donn�es sur la vue
	 */
	public boolean isInd() {
		return ind;
	}

	/**
	 * @param ind the ind to set
	 */
	public void setInd(boolean ind) {
		this.ind = ind;
	}

	/**
	 * @return the rech
	 */
	public String getRech() {
		return rech;
	}

	/**
	 * @param set pour r�cup�rer les entr�es utlis�es par l'utilisateur pour ses recherches
	 */
	public void setRech(String rech) {
		this.rech = rech;
	}

	/**
	 * @return la liste des cat�gories depuis la DB pour l'afficher dans la vue
	 */
	public List<Categorie> getListeCat() {
		return listeCat;
	}

	/**
	 * @param pas vraiment utile
	 */
	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}

	/**
	 * @return la liste des produits r�sultant d'une recherche
	 */
	public List<Produit> getListeProd() {
		return listeProd;
	}

	/**
	 * @param pas vraiment utile
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
	}
	
	
	//Autres m�thodes correspondant au cas d'utilisation client
	/**
	 * M�thode pour afficher les produits selon le filtre
	 * @return
	 */
	public String affProdByCat () {
		
		switch (type) {
		case "mot":
			listeProd = clService.getProdByKeyWord(rech);
			ind = true;
			break;
		case "cat":
			//R�cup�ration de la cat�gorie � partir de la DB
			cat = clService.getCatByNom(rech);
			listeProd = clService.getProdByCategorie(cat);
			ind = true;
			break;
		default:
			ind=false;
			break;
		}	
		return "affProd";
	}	
}
