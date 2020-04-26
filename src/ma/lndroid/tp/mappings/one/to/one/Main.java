package ma.lndroid.tp.mappings.one.to.one;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.mappings.one.to.one.dto.Answer;
import ma.lndroid.tp.mappings.one.to.one.dto.Question;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static final String PATH = "ma/lndroid/tp/mappings/one/to/one/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			Question q1 = new Question();
			q1.setQname("what is A LIST OBJ: ");
				Answer a1 =new Answer();
					a1.setAnswerName("A1");
					a1.setPostedBy("P1");
			q1.setAnswer(a1);
			a1.setQuestion(q1);
			
			Question q2 = new Question();
			q2.setQname("what is B LIST OBJ: ");
				Answer a2 =new Answer();
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

//fetch the data of List
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {

			Query query = session.createQuery("from Question");
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

}
