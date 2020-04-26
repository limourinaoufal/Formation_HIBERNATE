package ma.lndroid.tp.draft.abs.mapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.draft.abs.mapping.dto.Bike;
import ma.lndroid.tp.draft.abs.mapping.dto.Car;
import ma.lndroid.tp.draft.abs.mapping.dto.Vehicule;
import ma.lndroid.tp.draft.recursive.inheritance.dto.RevertClass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

	private static final String PATH_PROPERIES = "ma/lndroid/tp/draft/abs/mapping/ressource/hibernate.cfg.properties";
	private static final String PATH = "ma/lndroid/tp/draft/abs/mapping/ressource/hibernate.cfg.xml";

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
		List<String> listOfMappingRessources =new ArrayList<String>();
//			listOfMappingRessources.add("ma/lndroid/tp/draft/abs/mapping/hbm/ObjsMapping.hbm.xml");
		
			List<String> listOfMappingClasses =new ArrayList<String>();
			listOfMappingClasses.add("ma.lndroid.tp.draft.abs.mapping.dto.Vehicule");
			listOfMappingClasses.add("ma.lndroid.tp.draft.abs.mapping.dto.Bike");
			listOfMappingClasses.add("ma.lndroid.tp.draft.abs.mapping.dto.Car");
		SessionFactory sessionFactory = HibernateManager
				.getSessionFactoryByProperties(PATH,listOfMappingRessources,listOfMappingClasses,PATH_PROPERIES);
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Vehicule v1 = new Car();
			v1.setName("Hyndai");
			((Car) v1).setTypeCraburant("DIESEL");
			
			
			Vehicule v2 = new Bike();
			v2.setName("VTT");
			((Bike) v2).setTypeAir("O2");
			
			session.save(v1);
			session.save(v2);
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}

}
