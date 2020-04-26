package ma.lndroid.tp.mappings.many.to.one;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.mappings.many.to.one.dto.Answer;
import ma.lndroid.tp.mappings.many.to.one.dto.Question;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static final String PATH = "ma/lndroid/tp/mappings/many/to/one/ressource/hibernate.cfg.xml";

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
			q1.setAnswers(a1);

			Question q2 = new Question();
			q2.setQname("what is B LIST OBJ: ");
			

			q2.setAnswers(a1);
			
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
					Answer a =  q.getAnswers();
						System.out.println("\t" + a);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}

}
