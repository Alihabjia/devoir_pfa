package com.emsi.web.managed;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.emsi.dao.UtilisateurDao;
import com.emsi.model.Utilisateur;

@ManagedBean
@SessionScoped
public class RegisterUtilisateur {
	private Utilisateur utilisateur = new Utilisateur();

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String register() {
		UtilisateurDao dao = new UtilisateurDao();
		dao.register(utilisateur);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Utilisateur " + this.utilisateur.getNom() + " Is Registered Successfully")
				);
		return "";
	}
	
	public String VerifierLogin() {
		UtilisateurDao dao = new UtilisateurDao();
		
		
			try {
				if(dao.findUser(utilisateur.getLogin(), utilisateur.getPassword()) != null) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("listeContacts.xhtml");
				}else {
					FacesContext.getCurrentInstance().getExternalContext().redirect("erreur.xhtml");
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return "";
	}
	
	public void logout() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().invalidateSession();
        try {
			context.getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
