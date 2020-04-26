package ma.lndroid.tp.mappings.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.mappings.map.dto.Question;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static final String PATH = "ma/lndroid/tp/mappings/map/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			Question q1 = new Question();
			q1.setQname("what is A Set: ");
			Map<String,String> ans1 = new HashMap<String,String>();
			ans1.put("MAP 1", "MAP - AAAAAAAAAAA");
			ans1.put("MAP 2", "MAP - aaaaaaaaaaa");
			q1.setAnswers(ans1);

			Question q2 = new Question();
			q2.setQname("what is B Set: ");
			Map<String,String> ans2 = new HashMap<String,String>();
			ans2.put("MAP 1", "MAP - BBBBBBBBBbBB");
			ans2.put("MAP 2", "MAP - bbbbbbbbbbbb");
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
				Map<String, String> answersMap =  q.getAnswers();
				for (String s : answersMap.keySet() ) {
					System.out.println("\t" +s+" -> "+ answersMap.get(s));
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
