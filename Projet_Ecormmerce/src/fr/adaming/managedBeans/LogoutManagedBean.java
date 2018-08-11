package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "logout")
@SessionScoped
public class LogoutManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	private HttpSession maSession;

	// Constructeurs
	public LogoutManagedBean() {
		super();
	}

	// Getters and Setters
	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	// m�thode @PostConstruct
	@PostConstruct
	public void init() {

		// r�cup�rer la session
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// M�thode logout

	public String executeLogout() {
		maSession.invalidate();
		return "login";
	}

}
