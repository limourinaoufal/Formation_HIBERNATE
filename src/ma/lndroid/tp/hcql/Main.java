package ma.lndroid.tp.hcql;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.hcql.dto.MyObjectHcql;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Main {

	private static final String PATH = "ma/lndroid/tp/hcql/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {
		System.out.println("Start ...");

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);
		Session session = null;
		Transaction transaction = null;

		// save data
		saveData(sessionFactory, session, transaction);

		// get all data from db
		List<MyObjectHcql> listMyObjectHcqls = getListMyObjectHcql(
				sessionFactory, session, 1, 2);
		System.out.println("#List des Objet From DB");
		for (MyObjectHcql o : listMyObjectHcqls) {
			System.out.println("\t" + o);
		}

		// get data from db where name like "dataname"
		String dataName = "2";
		List<MyObjectHcql> listMyObjectHcqls2 = getListMyObjectHcqlByName(
				sessionFactory, session, dataName);
		System.out.println("#List des Objet From DB Where NAme like "
				+ dataName);
		for (MyObjectHcql o : listMyObjectHcqls2) {
			System.out.println("\t" + o);
		}

		// upfaye data
		int idData = 2;
		int res = updateById(sessionFactory, session, transaction, idData);
		System.out.println("Result Update : " + res);

		// get sigle data
		MyObjectHcql TheO = getOneObjectById(sessionFactory, session, idData);
		System.out.println("\t" + TheO);

		// delete data
		int del = deleteById(sessionFactory, session, transaction, idData);
		System.out.println("Result Delete : " + del);

		// get all data from db
		List<MyObjectHcql> listMyObjectHcqlsEnd = getListMyObjectHcql(
				sessionFactory, session, 0, 200);
		System.out.println("#List ALL  Objet From DB");
		for (MyObjectHcql o : listMyObjectHcqlsEnd) {
			System.out.println("\t" + o);
		}
		
		//get By Projection Criteron 
		MyObjectHcql exempleObject = new MyObjectHcql();
			exempleObject.setName("AAAAA-4");
			Object po = getMyObjectByProjection(sessionFactory, session,exempleObject);
			System.out.println(po);
		
		System.out.println("END ...");

	}

	private static Object getMyObjectByProjection(
			SessionFactory sessionFactory, Session session,
			MyObjectHcql exempleObject) {
		Object o = null;
		
		try {
			session = sessionFactory.openSession();
			Example ex = Example.create(exempleObject);
			
			Criteria c =  session.createCriteria(MyObjectHcql.class);
				c.setProjection(Projections.count("name"));
				c.add(ex);
				o =   c.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return o;
	}

	private static MyObjectHcql getOneObjectById(SessionFactory sessionFactory,
			Session session, int id) {

		MyObjectHcql o = null;
		try {
			session = sessionFactory.openSession();

			Criteria c= session.createCriteria(MyObjectHcql.class);
			c.add(Restrictions.eq("id", id));
			o = (MyObjectHcql) c.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return o;

	}

	@SuppressWarnings("deprecation")
	private static int updateById(SessionFactory sessionFactory,
			Session session, Transaction transaction, int idData) {

		int newId = 0;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<MyObjectHcql> q = session
					.createQuery("update MyObjectHcql set name= ?1 where id = :idData");
			q.setInteger("idData", idData);
			q.setParameter(1, "BBBBBBBB-2");
			newId = q.executeUpdate();

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return newId;

	}

	@SuppressWarnings("deprecation")
	private static int deleteById(SessionFactory sessionFactory,
			Session session, Transaction transaction, int idData) {

		int newId = 0;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<MyObjectHcql> q = session
					.createQuery("delete from MyObjectHcql where id = :idData");
			q.setInteger("idData", idData);
			newId = q.executeUpdate();

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return newId;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private static List<MyObjectHcql> getListMyObjectHcql(
			SessionFactory sessionFactory, Session session, int from, int to) {
		List<MyObjectHcql> l = null;
		try {
			session = sessionFactory.openSession();
			Criteria c = session.createCriteria(MyObjectHcql.class);
			c.setFirstResult(from)
				.setMaxResults(to)
				.addOrder(Order.asc("id"));
			

			l = c.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return l;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private static List<MyObjectHcql> getListMyObjectHcqlByName(
			SessionFactory sessionFactory, Session session, String dataName) {
		List<MyObjectHcql> l = null;
		try {
			session = sessionFactory.openSession();

			Criteria c = session.createCriteria(MyObjectHcql.class);
			c.add(Restrictions.like("name", "%"+dataName+"%"));
			l = c.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return l;
	}

	private static void saveData(SessionFactory sessionFactory,
			Session session, Transaction transaction) {

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			for (int i = 1; i < 5; i++) {
				MyObjectHcql o = new MyObjectHcql();
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
