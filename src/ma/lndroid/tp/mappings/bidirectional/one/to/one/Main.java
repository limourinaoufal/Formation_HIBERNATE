package ma.lndroid.tp.mappings.bidirectional.one.to.one;

import java.util.List;

import javax.persistence.Query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.mappings.bidirectional.one.to.one.dto.Answer;
import ma.lndroid.tp.mappings.bidirectional.one.to.one.dto.Question;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static final String PATH = "ma/lndroid/tp/mappings/bidirectional/one/to/one/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);

		Session session = null;
		Transaction transaction = null;

		save(sessionFactory, session, transaction);
		fetchByQuestion(sessionFactory, session, transaction);
		fetchByAnswer(sessionFactory, session, transaction);

	}

	private static void fetchByQuestion(SessionFactory sessionFactory,
			Session session, Transaction transaction) {
		System.out.println("Fetch By Question : ");
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Question");
			@SuppressWarnings("unchecked")
			List<Question> lq = query.getResultList();
			for (Question q : lq) {
				System.out.println(q.getQname());
				System.out.println("\t" + q.getAnswer());

			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	private static void fetchByAnswer(SessionFactory sessionFactory,
			Session session, Transaction transaction) {
		System.out.println("Fetch By Answer : ");
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Answer");
			@SuppressWarnings("unchecked")
			List<Answer> la = query.getResultList();
			for (Answer a : la) {
				System.out.println(a.getAnswerName());
				System.out.println("\t" + a.getQuestion());

			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	private static void save(SessionFactory sessionFactory, Session session,
			Transaction transaction) {
		System.out.println("Save Data: ");
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Question q1 = new Question();
			q1.setQname("what is A LIST OBJ: ");
			Answer a1 = new Answer();
			a1.setAnswerName("A1");
			a1.setPostedBy("P1");
			q1.setAnswer(a1);
			a1.setQuestion(q1);

			Question q2 = new Question();
			q2.setQname("what is B LIST OBJ: ");
			Answer a2 = new Answer();
			a2.setAnswerName("A2");
			a2.setPostedBy("P2");
			q2.setAnswer(a2);
			a2.setQuestion(q2);

			session.persist(q1);
			session.persist(q2);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}

}
