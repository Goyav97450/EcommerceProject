package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;

@ManagedBean(name = "aMB")
@SessionScoped
public class AdminManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@EJB
	private IAdminService aService;

	// Attributs
	private Admin a;
	private boolean loggedIn = false;

	// Constructeur
	public AdminManagedBean() {
		this.a = new Admin();
	}

	/**
	 * @return the a
	 */
	public Admin getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(Admin a) {
		this.a = a;
	}

	/**
	 * @return the loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn
	 *            the loggedIn to set
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	// Méthodes
	public String connexion() {
		Admin aExist = aService.isExistService(a);

		if (a != null) {
			this.loggedIn = true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aSession", aExist);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedIn", loggedIn);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ID ou mot de passe invalide, veuillez réessayer."));
			return "login";
		}
	}

}
