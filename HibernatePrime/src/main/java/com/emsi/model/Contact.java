package com.emsi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.emsi.web.managed.RegisterUtilisateur;

@Entity
@Table(name="contact")
public class Contact {
	@Id
	@GeneratedValue
	private int id_contact;
	private String nom;
	private String num;
	public int getId_contact() {
		return id_contact;
	}
	public void setId_contact(int id_contact) {
		this.id_contact = id_contact;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Contact(String nom, String num) {
		super();
		this.nom = nom;
		this.num = num;
	}
	public Contact() {
		super();
	}
	
	
}
