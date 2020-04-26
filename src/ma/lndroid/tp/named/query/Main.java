package ma.lndroid.tp.named.query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.named.query.dto.MyObjectQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

	private static final String PATH = "ma/lndroid/tp/named/query/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);
		Session session = null;
		Transaction transaction = null;
		
		saveData(sessionFactory, session, transaction);

		try {
			
			session = sessionFactory.openSession();
			
			//AnnQueryGetByIdetById
			Query q= session.getNamedQuery("hbmQueryGetById");
			q.setParameter("myId", 2);
			MyObjectQuery o = (MyObjectQuery) q.uniqueResult();
			System.out.println(o);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			if(session != null)
				session.close();
		}
		

	}
	
	private static void saveData(SessionFactory sessionFactory, Session session,
			Transaction transaction) {

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			for (int i = 1; i < 5; i++) {
				MyObjectQuery o = new MyObjectQuery();
				o.setName("AAAAA-" + (i));
				session.save(o);
			}

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();

		} finally {
			session.close();
		}

	}


}
