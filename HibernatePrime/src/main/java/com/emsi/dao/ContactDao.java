package com.emsi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.emsi.HibernateUtil.HibernateUtil;
import com.emsi.model.Contact;

public class ContactDao {
	public boolean register(Contact contact) {
		Session session =  HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(contact);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact>  getAllContacts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Contact> list = new ArrayList<Contact>();
		try {
			
			tx = session.getTransaction();
			tx.begin();	
			Query query = session.createQuery("from Contact");
            list = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return  list;
	}
	
	public boolean supprimer(Contact contact) {
		Session session =  HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.delete(contact);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}
	
	public Contact getContactById(int id) {
        Session session = null;
        Contact contact = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            contact =  (Contact) session.load(Contact.class, id);
            Hibernate.initialize(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return contact;
    }
	
	public Contact update(Contact contact) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.merge(contact);
			tx.commit();
			session.close();
		}catch(HibernateException ex) {
		if(tx!=null) {
			tx.rollback();
		}
		session.close();
		}
		return contact;
	}
	
}
