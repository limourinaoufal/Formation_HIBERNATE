package ma.lndroid.tp.mappings.list.in.collection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.mappings.list.in.collection.dto.Question;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static final String PATH = "ma/lndroid/tp/mappings/list/in/collection/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			Question q1 = new Question();
			q1.setQname("what is A List: ");
			List<String> ans1 = new ArrayList<String>();
			ans1.add("List - AAAAAAAAAAA");
			ans1.add("List - aaaaaaaaaaa");
			q1.setAnswers(ans1);

			Question q2 = new Question();
			q2.setQname("what is B List: ");
			List<String> ans2 = new ArrayList<String>();
			ans2.add("List - BBBBBBBBBBBBBB");
			ans2.add("List - bbbbbbbbbbbbbb");
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
