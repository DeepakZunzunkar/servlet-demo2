package com.dz.app.utility;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.dz.app.model.entity.Employee;

public class HibernateUtilty {

	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	private static Map<String, String> dbSettings;

	static {
		
		try {
			if(sessionFactory==null) {
			
				//create standardServiceRegistry
				StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
				
				// Hibernate setting which is equivalent to hibernate.cfg.xml properties.
				dbSettings = new HashMap<String, String>();
				
				dbSettings.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
				dbSettings.put(Environment.URL,"jdbc:mysql://localhost:3306/hibernatedemo3?createDatabaseIfNotExist=true");
				dbSettings.put(Environment.USER,"root");
				dbSettings.put(Environment.PASS,"root");
				dbSettings.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
				dbSettings.put(Environment.HBM2DDL_AUTO,"true");
				dbSettings.put(Environment.SHOW_SQL,"true");
				dbSettings.put(Environment.FORMAT_SQL,"false");
				
				standardServiceRegistryBuilder.applySettings(dbSettings);
				standardServiceRegistry= standardServiceRegistryBuilder.build();
				
				//create metaDataSource
				MetadataSources metadataSources =new MetadataSources(standardServiceRegistry);
				metadataSources.addAnnotatedClass(Employee.class);
				
				//create metaData 
				Metadata metadata= metadataSources.getMetadataBuilder().build();
				
				//create sessionFactory
				sessionFactory=metadata.getSessionFactoryBuilder().build();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(standardServiceRegistry!=null) {
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}
	
	
	// utility method to get session factory 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
