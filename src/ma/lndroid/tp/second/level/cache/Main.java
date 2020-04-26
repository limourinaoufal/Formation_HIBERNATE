package ma.lndroid.tp.second.level.cache;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.second.level.cache.dto.MyObjectCache;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

	private static final String PATH = "ma/lndroid/tp/second/level/cache/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);
		Session session = null;
		Transaction transaction = null;

		saveData(sessionFactory, session, transaction);

		Session session1 = sessionFactory.openSession();
		MyObjectCache objectQuery = (MyObjectCache) session1.load(
				MyObjectCache.class, 2);
		System.out.println(objectQuery.getId() + " " + objectQuery.getName());
		session1.close();

		Session session2 = sessionFactory.openSession();
		MyObjectCache objectQuery2 = (MyObjectCache) session2.load(
				MyObjectCache.class, 2);
		System.out.println(objectQuery2.getId() + " " + objectQuery2.getName());
		session2.close();

		Session session3 = sessionFactory.openSession();
		Query q1 = session3.getNamedQuery("AnnQueryGetByIdetById");
		q1.setCacheable(true);
		q1.setParameter("myId", 3);

		MyObjectCache objectQuery3 = (MyObjectCache) q1.uniqueResult();
		System.out.println(objectQuery3.getId() + " " + objectQuery3.getName());
		session3.close();

		Session session4 = sessionFactory.openSession();
		Query q2 = session4.getNamedQuery("AnnQueryGetByIdetById");
		q2.setCacheable(true);
		q2.setParameter("myId", 3);
		MyObjectCache objectQuery4 = (MyObjectCache) q2.uniqueResult();
		System.out.println(objectQuery4.getId() + " " + objectQuery4.getName());
		session4.close();

		System.out.println("END");

		HibernateManager.closeSessionFactory();
	}

	private static void saveData(SessionFactory sessionFactory,
			Session session, Transaction transaction) {

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			for (int i = 1; i < 5; i++) {
				MyObjectCache o = new MyObjectCache();
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
