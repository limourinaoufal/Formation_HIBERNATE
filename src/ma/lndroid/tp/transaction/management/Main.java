package ma.lndroid.tp.transaction.management;

import ma.lndroid.tp.config.HibernateManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	
	private static final String PATH = "ma/lndroid/tp/transaction/management/ressource/hibernate.cfg.xml";
	
	public static void main(String[] args) {
		System.out.println("-- START --");
		
		SessionFactory sessionFactory = HibernateManager.getSessionFactory(PATH);
			Session session = null;
			Transaction transaction = null;
			
		try {			
			session = sessionFactory.openSession();
			System.out.println("session = sessionFactory.openSession();");
				System.out.println("\tsessionisConnected: "+session.isConnected());
				System.out.println("\tsession isOpen: "+session.isOpen());
				
			transaction = session.beginTransaction();
			System.out.println("transaction = session.beginTransaction();");
				System.out.println("\ttransaction isActive: "+transaction.isActive());
				System.out.println("\ttransaction getStatus: "+transaction.getStatus());
			
			transaction.commit();
			System.out.println("Commit Transaction");
			System.out.println("\ttransaction isActive: "+transaction.isActive());
			System.out.println("\ttransaction getStatus: "+transaction.getStatus());
			
			transaction.begin();
			System.out.println("#transaction.begin();");
				System.out.println("\ttransaction isActive: "+transaction.isActive());
				System.out.println("\ttransaction getStatus: "+transaction.getStatus());
				
			transaction.begin();


		} catch (Exception e) {
			System.err.println("ERROR : transaction.begin(); --> " + e.getMessage());
				System.out.println("\ttransaction isActive: "+transaction.isActive());
				System.out.println("\ttransaction getStatus: "+transaction.getStatus());
			
			if(transaction != null){
				transaction.rollback();
				System.out.println("RollBack Transaction");
			}
			
		}finally{
			
			if(session != null){
				session.close();
				System.out.println("Close Session ");
			}
				
		}

			if(transaction != null){
					System.out.println("\ttransaction isActive: "+transaction.isActive());
					System.out.println("\ttransaction getStatus: "+transaction.getStatus());
			}
			
			if(session != null){
				System.out.println("\tsessionisConnected: "+session.isConnected());
				System.out.println("\tsession isOpen: "+session.isOpen());
			}
			
		System.out.println("-- END --");
	}

}
