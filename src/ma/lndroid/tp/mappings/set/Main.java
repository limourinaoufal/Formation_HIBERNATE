package ma.lndroid.tp.mappings.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.mappings.set.dto.Question;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static final String PATH = "ma/lndroid/tp/mappings/set/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			Question q1 = new Question();
			q1.setQname("what is A Set: ");
			Set<String> ans1 = new HashSet<String>();
			ans1.add("Set - AAAAAAAAAAA");
			ans1.add("Set - aaaaaaaaaaa");
			q1.setAnswers(ans1);

			Question q2 = new Question();
			q2.setQname("what is B Set: ");
			Set<String> ans2 = new HashSet<String>();
			ans2.add("Set - BBBBBBBBBBBBBB");
			ans2.add("Set - bbbbbbbbbbbbbb");
			q2.setAnswers(ans2);

			session.save(q1);
			session.save(q2);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {

			Query query = session.createQuery("from Question");
			List<Question> lq = query.getResultList();
			for (Question q : lq) {
				System.out.println(q.getQname());
				for (String s : q.getAnswers()) {
					System.out.println("\t" + s);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}

}
