package com.emsi.web.managed;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.emsi.HibernateUtil.HibernateUtil;
import com.emsi.dao.ContactDao;
import com.emsi.model.Contact;

@ManagedBean
@SessionScoped
public class RegisterContact {
	private Contact contact = new Contact();

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public String register() {
		ContactDao dao = new ContactDao();
		dao.register(contact);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Contact " + this.contact.getNom() + " Is Registered Successfully"));
		return "listeContacts.xhtml";
	}
	
	public List<Contact> ListContact() {
		ContactDao dao = new ContactDao();
		return dao.getAllContacts();
	}
	
	public String deleteAction(Contact contact) {
		ContactDao dao = new ContactDao();
		dao.supprimer(contact);
		return "";
	}
	
	public void modifierContact(Contact c){
		 
		try{
			ContactDao dao = new ContactDao();
			
			c=dao.getContactById(c.getId_contact());
	 
			contact.setNom(c.getNom());
			contact.setNum(c.getNum());
	 
	 // la methode update Utilisateur permet d'appeler la fonction implémentée dans la couche service
			dao.update(c);
	 
			//return "ok";
			}
			catch(Exception e){
				e.printStackTrace();
				//return "fail";
	 
			}
	 
	}
	public void onEdit(RowEditEvent event) {
		ContactDao dao = new ContactDao();
		dao.update((Contact)event.getObject());
		
	}
	 
	public void onCancel(RowEditEvent event) {
	    FacesMessage msg = new FacesMessage("Contact Cancelled", ((Contact) event.getObject()).getNom());
	 
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	 
	}
}
