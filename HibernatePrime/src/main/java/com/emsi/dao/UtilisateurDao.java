package com.emsi.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.emsi.HibernateUtil.HibernateUtil;
import com.emsi.model.Utilisateur;


public class UtilisateurDao {
	
	
	public boolean register(Utilisateur utilisateur) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(utilisateur);
			tx.commit();
		}catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return true;
	}
	
	/*public Utilisateur findUser(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Utilisateur u = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String query = "from utilisateur where login = '" + login + "' and password = '" + password + "'";
			Query q = session.createQuery(query);
			if(!q.list().isEmpty()) {
				u = (Utilisateur) q.uniqueResult();
			}
			tx.commit();
		}catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return u;
	}*/
	
	public Utilisateur findUser(String login,String pss) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Utilisateur utilisateur = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = (Query) session.createQuery("from Utilisateur where login='" + login + "' and password ='"+ pss+"'");
			utilisateur = (Utilisateur) ((org.hibernate.Query) query).uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return utilisateur;
	}

}
