package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idAdmin;
	private String mdp;

	// Constructeurs
	public Admin(long idAdmin, String mdp) {
		super();
		this.idAdmin = idAdmin;
		this.mdp = mdp;
	}

	public Admin(String mdp) {
		super();
		this.mdp = mdp;
	}

	public Admin() {
		super();
	}

	// Getters and Setters
	public long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	// ToString
	@Override
	public String toString() {
		return "Admin [idAdmin=" + idAdmin + ", mdp=" + mdp + "]";
	}

}
