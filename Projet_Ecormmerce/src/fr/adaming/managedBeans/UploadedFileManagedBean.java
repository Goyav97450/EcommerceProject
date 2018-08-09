package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

@ManagedBean(name = "uPF")
@RequestScoped
public class UploadedFileManagedBean implements Serializable {

	// Attributs
	private UploadedFile file;

	public UploadedFileManagedBean() {
		super();
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return file;
	}

	/**
	 * @param uploadedFile
	 *            the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}
}
