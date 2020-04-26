package ma.lndroid.tp.mappings.lazy.one.to.many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.mappings.lazy.one.to.many.dto.Answer;
import ma.lndroid.tp.mappings.lazy.one.to.many.dto.Question;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static final String PATH = "ma/lndroid/tp/mappings/lazy/one/to/many/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);

		Session session = null;
		Transaction transaction = null;
		
		
		save(sessionFactory, session, transaction);

//fetch the data of List  ( Lazy => (lazy = true ) /  EAGER => (lazy = false )  )
		
		Question q = fetchByQuestionId1(sessionFactory, session, transaction);
			System.out.println(q);
			try {
				System.out.println("=> "+q.getAnswers());
			} catch (Exception e) {
				e.printStackTrace();
					System.err.println("################################################");
					System.err.println("## => CHANGE HBM LAZY => FALSE ( EAGER MODE ) ##");
					System.err.println("## OR                                         ##");
					System.err.println("## => CHANGE ANNOTATION TO EAGER FETCH TYPE   ##");
					System.err.println("################################################");
			}
				

	}

	private static Question fetchByQuestionId1(SessionFactory sessionFactory,
			Session session, Transaction transaction) {
		Question question = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			
			Query query = session.createQuery("from Question where id = 1");
			question  = (Question) query.getSingleResult();
			

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return question;
	}

	private static void save(SessionFactory sessionFactory, Session session,
			Transaction transaction) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Question q1 = new Question();
			q1.setQname("what is A LIST OBJ: ");
			List<Answer> ans1 = new ArrayList<Answer>();
				Answer a1 =new Answer();
					a1.setAnswerName("A1");
					a1.setPostedBy("P1");
				Answer a2 =new Answer();
					a2.setAnswerName("A2");
					a2.setPostedBy("P2");
				ans1.add(a1);ans1.add(a2);
			q1.setAnswers(ans1);

			Question q2 = new Question();
			q2.setQname("what is B LIST OBJ: ");
			List<Answer> ans2 = new ArrayList<Answer>();
				Answer a3 =new Answer();
					a3.setAnswerName("A3");
					a3.setPostedBy("P3");
				Answer a4 =new Answer();
					a4.setAnswerName("A4");
					a4.setPostedBy("P2");
            ans2.add(a3);ans2.add(a4);
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
	}

}
