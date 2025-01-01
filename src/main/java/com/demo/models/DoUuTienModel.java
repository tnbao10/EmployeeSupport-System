package com.demo.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Douutien;
import com.demo.entities.Nhanvien;

public class DoUuTienModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public List<Douutien> findAll(){
		List<Douutien> douutiens = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			

			douutiens = session.createQuery("from Douutien", Douutien.class).getResultList();
			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			douutiens = null;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return douutiens;
	}
	
	public Douutien findById(int id){
		Douutien douutien = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			

			douutien = session.createQuery("from Douutien where id = :id", Douutien.class)
								.setParameter("id", id)
								.getSingleResult();
			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			douutien = null;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return douutien;
	}
	
	
}	
