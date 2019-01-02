package com.emsi.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.emsi.HibernateUtil.HibernateUtil;
import com.emsi.model.Employee;

public class EmployeeDao {

	public boolean register(Employee employee) {
		Session session =  HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(employee);
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

}
