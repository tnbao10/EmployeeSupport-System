package com.demo.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Nhanvien;
import com.demo.entities.Yeucau;

public class NhanVienModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public List<Nhanvien> findAll(){
		List<Nhanvien> nhanviens = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			

			nhanviens = session.createQuery("from Nhanvien", Nhanvien.class).getResultList();
			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			nhanviens = null;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return nhanviens;
	}
	
	public List<Nhanvien> searchByUsername(String username) {
		List<Nhanvien> nhanviens = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			nhanviens = session.createQuery("from Nhanvien where username like :username  ", Nhanvien.class)
								.setParameter("username", "%" + username + "%")
								.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			nhanviens = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return nhanviens;
	}
	
	public Nhanvien findByUsername(String username){
		Nhanvien nhanvien = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			

			nhanvien = session.createQuery("from Nhanvien where username = :username", Nhanvien.class)
								.setParameter("username", username)
								.getSingleResult();			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			nhanvien = null;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return nhanvien;
	}
	
	public List<Nhanvien> findAllSupportEmployee(){
		List<Nhanvien> nhanviens = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			

			nhanviens = session.createQuery("from Nhanvien where quyen = 2", Nhanvien.class)
								.getResultList();			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			nhanviens = null;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return nhanviens;
	}
	
	
	
	public Nhanvien findSupportEmployeeByUsername(String username){
		Nhanvien nhanvien = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			

			nhanvien = session.createQuery("from Nhanvien where username = :username and quyen = :quyen", Nhanvien.class)
								.setParameter("username", username)
								.setParameter("quyen", 2)
								.getSingleResult();			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			nhanvien = null;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return nhanvien;
	}
	
	public boolean login(String username, String password){
		boolean isAuthenticated = false;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			

            // Truy vấn lấy nhân viên dựa trên username
            Nhanvien nhanvien = session.createQuery("FROM Nhanvien WHERE username = :username", Nhanvien.class)
                    .setParameter("username", username)
                    .uniqueResult();
			
            if (nhanvien != null) {
                // So sánh mật khẩu đã nhập với mật khẩu đã mã hóa trong database
                if (BCrypt.checkpw(password, nhanvien.getPassword())) {
                    isAuthenticated = true;
                }
            }			
            transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			isAuthenticated = false;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return isAuthenticated;
	}
	
	/* Add */
	public boolean Create(Nhanvien nhanvien){
		boolean result = true;
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			session.persist(nhanvien);
			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			result = false;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}
	
	/* Update */
	public boolean Update(Nhanvien nhanvien){
		boolean result = true;
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			session.merge(nhanvien);
			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction != null) { 
				transaction.rollback();
			}
			result = false;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}
}	
