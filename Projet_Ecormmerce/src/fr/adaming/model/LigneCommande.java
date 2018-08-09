package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ligneCommande")
public class LigneCommande implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCommande;
	private int quantite;
	private int prix;

	// Constructeurs
	public LigneCommande(int idCommande, int quantite, int prix) {
		super();
		this.idCommande = idCommande;
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande() {
		super();
	}

	// Getters and Setters
	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	// ToString
	@Override
	public String toString() {
		return "LigneCommande [idCommande=" + idCommande + ", quantite=" + quantite + ", prix=" + prix + "]";
	}

}
