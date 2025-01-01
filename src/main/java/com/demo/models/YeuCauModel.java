package com.demo.models;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.entities.Nhanvien;
import com.demo.entities.Yeucau;

public class YeuCauModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public List<Yeucau> findAll() {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau", Yeucau.class).getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public Yeucau findById(int id) {
		Yeucau yeucau = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucau = session.createQuery("from Yeucau where id = :id", Yeucau.class)
							.setParameter("id", id)
							.getSingleResult();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucau = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucau;
	}
	
	
	
	
	
	public List<Yeucau> findByEmployeeUsername(String employeeUsername) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where nhanvienByMaNvGui.username = :employeeUsername", Yeucau.class)
							.setParameter("employeeUsername", employeeUsername)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public List<Yeucau> findBySupportEmployeeUsername(String supportEmployeeUsername) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where nhanvienByMaNvXuLy.username = :supportEmployeeUsername", Yeucau.class)
							.setParameter("supportEmployeeUsername", supportEmployeeUsername)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public List<Yeucau> searchByDate(Date from, Date to) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where ngayGui >= :from and ngayGui <= :to", Yeucau.class)
							.setParameter("from", from)
							.setParameter("to", to)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public List<Yeucau> searchBy_Dates_And_EmployeeUsername(Date from, Date to, String employeeUsername) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where ngayGui >= :from and ngayGui <= :to and nhanvienByMaNvGui.username = :employeeUsername  ", Yeucau.class)
							.setParameter("from", from)
							.setParameter("to", to)
							.setParameter("employeeUsername", employeeUsername)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public List<Yeucau> searchBy_Dates_And_SupportEmployeeUsername(Date from, Date to, String supportEmployeeUsername) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where ngayGui >= :from and ngayGui <= :to and nhanvienByMaNvXuLy.username = :supportEmployeeUsername  ", Yeucau.class)
							.setParameter("from", from)
							.setParameter("to", to)
							.setParameter("supportEmployeeUsername", supportEmployeeUsername)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public List<Yeucau> searchByPriorityId(int id) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where douutien.id = :id", Yeucau.class)
							.setParameter("id", id)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public List<Yeucau> searchBy_PriorityId_And_EmployeeUsername(int priorityId, String employeeUsername) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where douutien.id = :priorityId and nhanvienByMaNvGui.username = :employeeUsername", Yeucau.class)
							.setParameter("priorityId", priorityId)
							.setParameter("employeeUsername", employeeUsername)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	public List<Yeucau> searchBy_PriorityId_And_SupportEmployeeUsername(int priorityId, String supportEmployeeUsername) {
		List<Yeucau> yeucaus = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			yeucaus = session.createQuery("from Yeucau where douutien.id = :priorityId and nhanvienByMaNvXuLy.username = :supportEmployeeUsername", Yeucau.class)
							.setParameter("priorityId", priorityId)
							.setParameter("supportEmployeeUsername", supportEmployeeUsername)
							.getResultList();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			yeucaus = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return yeucaus;
	}
	
	
	public boolean Create(Yeucau yeucau) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.persist(yeucau);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			result = false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	public boolean Update(Yeucau yeucau) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.merge(yeucau);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			result = false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

}
