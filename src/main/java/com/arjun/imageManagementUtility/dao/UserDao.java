package com.arjun.imageManagementUtility.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.arjun.imageManagementUtility.entity.SignupEntity;
import com.arjun.imageManagementUtility.model.LoginModel;
import com.arjun.imageManagementUtility.util.HibernateUtil;

public class UserDao {
	
	public void saveUser(SignupEntity signup) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
            transaction = session.beginTransaction();
            session.save(signup);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
	public boolean validate(LoginModel login) {
		 Transaction transaction = null;
	
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			NativeQuery query = session.createSQLQuery("Select * from SignupEntity S where S.username = :username");
			query.setParameter("username", login.getUsername());
			query.addEntity(SignupEntity.class);

			SignupEntity signup = new SignupEntity();
			transaction.commit();
			if (login != null) {
				login.getPassword().equals(signup.getPassword());
				return true;
			} else
				return false;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}
		return false;
	}
}
