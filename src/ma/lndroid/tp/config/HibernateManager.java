package ma.lndroid.tp.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateManager {
	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory(String path){
		
		if(sessionFactory == null){
				//sessionFactory = new Configuration().configure(path).buildSessionFactory();
			
				StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(path).build();
				Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			}	
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactoryByProperties(String path,List<String> mappingRessources,List<String> mappingClasses,String propertiesPath) throws ClassNotFoundException, FileNotFoundException, IOException{
		
		if(sessionFactory == null){
			
			StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().configure(path);
			StandardServiceRegistry serviceRegistry = null;
			if( propertiesPath != null && !propertiesPath.isEmpty()){
				
				String newPathProperties = loadPropertiesFileByEnvironement(propertiesPath);
				
				 serviceRegistry = standardServiceRegistryBuilder.loadProperties(newPathProperties).build();
			}else{
				 serviceRegistry = standardServiceRegistryBuilder.build();
			}
				MetadataSources metadataSources = new MetadataSources(serviceRegistry);
				
				for(String s : mappingRessources)
					metadataSources.addResource(s);
				
				for(String s : mappingClasses){
					//Class c = Class.forName(s).getClassLoader().loadClass(s);
					//metadataSources.addAnnotatedClass(c);
					metadataSources.addAnnotatedClassName(s);
				}
				

				
				Metadata metadata = metadataSources.buildMetadata();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			}	
		return sessionFactory;
	}

	private static String loadPropertiesFileByEnvironement(String propertiesPath) {
		// OCK  FROM JVM PROPERTIES
			System.setProperty("ENV", "dev");
		String env = System.getProperty("ENV");
		String [] ts = propertiesPath.split("/");
		for(int i = 0;i<ts.length;i++){
			if(ts[i].contains(".cfg.")){
				ts[i] = env+"_"+ts[i];						
				break;
			}
		}
		String newPathProperties = "";
		for(String s : ts)
			newPathProperties=newPathProperties +"/"+s;
		newPathProperties = newPathProperties.substring(1,newPathProperties.length());
		return newPathProperties;
	}
	
public static void closeSessionFactory(){
		
		if(sessionFactory != null){
				sessionFactory.close();
			}	
	}

}
