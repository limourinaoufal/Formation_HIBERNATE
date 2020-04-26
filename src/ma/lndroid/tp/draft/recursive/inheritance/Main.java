package ma.lndroid.tp.draft.recursive.inheritance;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.draft.recursive.inheritance.dto.RevertClass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

	private static final String PATH = "ma/lndroid/tp/draft/recursive/inheritance/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			RevertClass r1 = new RevertClass();
			r1.setName("R1");

			RevertClass r2 = new RevertClass();
			r2.setName("R2");
			r2.setPereRevClass(r1);

			session.save(r2);
			session.save(r1);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}

}
