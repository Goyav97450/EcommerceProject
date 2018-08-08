package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pr")
	private long idProduit;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	private boolean selectionne;
	private byte photo;

	// Transformation de l'association UML en Java
	@ManyToOne
	@JoinColumn(name = "ca_id", referencedColumnName = "id_ca")
	private Produit pr;

	// Constructeurs
	public Produit(long idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, byte photo) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	public Produit(String designation, String description, double prix, int quantite, boolean selectionne, byte photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	public Produit() {
		super();
	}

	// Getters and Setters
	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public byte getPhoto() {
		return photo;
	}

	public void setPhoto(byte photo) {
		this.photo = photo;
	}

	public Produit getPr() {
		return pr;
	}

	public void setPr(Produit pr) {
		this.pr = pr;
	}

	// ToString
	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", selectionne=" + selectionne + ", photo=" + photo
				+ "]";
	}

}
