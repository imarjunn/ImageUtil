package com.arjun.imageManagementUtility.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.arjun.imageManagementUtility.entity.ImageEntity;
import com.arjun.imageManagementUtility.entity.SignupEntity;

public class HibernateUtil {
	

	private static SessionFactory sessionFactory;
	private  Transaction transaction = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/imageutilitymanagement");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "Arjun-singh1");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "create-drop");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(SignupEntity.class);
				configuration.addAnnotatedClass(ImageEntity.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
