/**
 * 
 */
package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

/**
 * @author Thibault
 * ManagedBean des activit�s propres au panier
 */
@ManagedBean(name="paMB")
@RequestScoped
public class PanierManagedBean {
	/**
	 * Transformation de l'association UML en JAVA
	 * L'annotation @EJB permet d'�tablir un couplage faible entre les services.
	 */
	@EJB
	private IClientService clService;
	@EJB
	private IProduitService pService;
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
	 * Attribut lignedecommande qui va servir a remplir le panier et a pass� commande.
	 */
	private LigneCommande lc;
	/**
	 * Attribut servant au client � choisir la quantit� de produit � ajouter au panier
	 */
	private int q;
		
	/**
	 * 
	 */
	public PanierManagedBean() {
		super();
		this.cl = new Client();
		this.co = new Commande();
		this.prod = new Produit();
		this.lc = new LigneCommande();
		this.panier = new Panier();
	}
	
	@PostConstruct
	public void init () {
		
	}
	/**
	 * @return the cl
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * @param cl the cl to set
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}

	/**
	 * @return the co
	 */
	public Commande getCo() {
		return co;
	}
	/**
	 * @param co the co to set
	 */
	public void setCo(Commande co) {
		this.co = co;
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
	 * @return permet de r�cup�rer une ligne de commande
	 */
	public LigneCommande getLc() {
		return lc;
	}

	/**
	 * @param permet de cr�er une ligne de commande � partir des inputs clients
	 */
	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}
	/**
	 * @return affiche la quantit� de produit d'une ligne d ecommande
	 */
	public int getQ() {
		return q;
	}

	/**
	 * @param permet de set la quantit� de produit d'une ligne de commande
	 */
	public void setQ(int q) {
		this.q = q;
	}
	
	//Autres m�thodes
	public String ajoutProdPanier() {
		Panier panierSession = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panierCl");
		
		List<LigneCommande> newList = new ArrayList<LigneCommande>();
		
		if (panierSession!=null) {
		
			if (panierSession.getListeCom()!=null) {
				List<LigneCommande> oldList = panierSession.getListeCom();
				
				System.out.println(oldList.size());
				for (LigneCommande elem:oldList) {
					newList.add(elem);
				}
			}
		}
		Produit prodOut = pService.getByIdProduitService(this.prod);
		lc = clService.ajoutProdPanier(prodOut, q);
		
		if (lc!=null) {
			newList.add(lc);
			panier.setListeCom(newList);
			
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierCl", panier);
			
			return "panier";
		} else {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou� veuillez r�essayer"));
			
			return "ajoutPanier";
		}
	}
	
	public String supprProdPanier () {
		Panier panierSession = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panierCl");
		
		if (panierSession==null | panierSession.getListeCom()==null) {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a �chou� veuillez r�essayer"));
			
			return "panier";
		} else {
			int verif = clService.supprProdPanier(prod, panierSession);
			
			if (verif!=0) {
				//Envoie d'un message de confirmation
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a r�ussi, vous pouvez continuer vos achats"));
				
				return "panier";
			} else {
				//Envoie d'un message d'erreur
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a �chou� veuillez r�essayer"));
				
				return "panier";
			}
		}
	}
}
