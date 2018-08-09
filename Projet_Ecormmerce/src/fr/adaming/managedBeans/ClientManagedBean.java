/**
 * 
 */
package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;

/**
 * @author Thibault
 * ManagedBean correspondant à la vue Client
 */
@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	/**
	 * Transformation de l'association UML en JAVA
	 * L'annotation @EJB permet d'établir un couplage faible entre les services.
	 */
	@EJB
	private IClientService clService;
	//Attributs
	/**
	 * Attribut client qui servira au client pour s'enregistrer
	 */
	private Client cl;
	/**
	 * Attribut commande qui servira à enregistrer/afficher des commandes
	 */
	private Commande co;
	/**
	 * Attribut commande qui servira à afficher le panier et y rajouter des produits
	 */
	private Panier panier;
	/**
	 * Attribut liste de catégories servant à afficher la liste des catégories
	 */
	private List<Categorie> listeCat;
	/**
	 * Attribut liste de produits servant à afficher la liste des produits selon une recherche
	 */
	private List<Produit> listeProd;
	
	
	/**
	 * Constructeur vide nécessaire à un ManagedBean
	 */
	public ClientManagedBean() {
		super();
	}

	/**
	 * @return le client s'étant enregistrer
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * @param set le client du MB pour pouvoir récupérer ses données à enregistrer
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}

	/**
	 * @return la commande réaliser par le client
	 */
	public Commande getCo() {
		return co;
	}

	/**
	 * @param set la commande afin de récupérer les inputs du client
	 */
	public void setCo(Commande co) {
		this.co = co;
	}

	/**
	 * @return affiche le panier et les données contenant dans le panier client
	 */
	public Panier getPanier() {
		return panier;
	}

	/**
	 * @param set le panier du MB nécessaire pour stocker les courses d'un client
	 */
	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	/**
	 * @return la liste des catégories depuis la DB pour l'afficher dans la vue
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
	 * @return la liste des produits résultant d'une recherche
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
	
	//Autres méthodes correspondant au cas d'utilisation client
	
	public String affCat() {
		
		
		
		return "recherche";
	}
	
}
