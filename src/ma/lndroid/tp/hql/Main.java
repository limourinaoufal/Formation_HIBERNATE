package ma.lndroid.tp.hql;

import java.util.List;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.draft.recursive.inheritance.dto.RevertClass;
import ma.lndroid.tp.hql.dto.MyObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

	private static final String PATH = "ma/lndroid/tp/hql/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);
		Session session = null;
		Transaction transaction = null;

		// save data
		saveData(sessionFactory, session, transaction);

		// get all data from db
		List<MyObject> listMyObjects = getListMyObject(sessionFactory, session,
				1, 2);
		System.out.println("#List des Objet From DB");
		for (MyObject o : listMyObjects) {
			System.out.println("\t" + o);
		}

		// get data from db where name like "dataname"
		String dataName = "2";
		List<MyObject> listMyObjects2 = getListMyObjectByName(sessionFactory,
				session, dataName);
		System.out.println("#List des Objet From DB Where Name like "
				+ dataName);
		for (MyObject o : listMyObjects2) {
			System.out.println("\t" + o);
		}

		// update data
		int idData = 2;
		int res = updateById(sessionFactory, session, transaction, idData);
		System.out.println("Result Update : " + res);

		// get single data
		MyObject TheO = getOneObjectById(sessionFactory, session, idData);
		System.out.println("\t" + TheO);

		// delete data
		int del = deleteById(sessionFactory, session, transaction, idData);
		System.out.println("Result Delete : " + del);

		// get all data from db
		List<MyObject> listMyObjectsEnd = getListMyObject(sessionFactory,
				session, 0, 200);
		System.out.println("#List ALL  Objet From DB");
		for (MyObject o : listMyObjectsEnd) {
			System.out.println("\t" + o);
		}

	}

	private static MyObject getOneObjectById(SessionFactory sessionFactory,
			Session session, int id) {

		MyObject o = null;
		try {
			session = sessionFactory.openSession();

			@SuppressWarnings("unchecked")
			// Query<MyObject> q =
			// session.createQuery("from MyObject where id = ?0 ");
			// q.setParameter(0, id+);
			Query<MyObject> q = session
					.createQuery("from MyObject where id = :BdId ");
			q.setInteger("BdId", id);

			o = q.getSingleResult();

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
			Query<MyObject> q = session
					.createQuery("update MyObject set name= ?1 where id = :idData");
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
			Query<MyObject> q = session
					.createQuery("delete from MyObject where id = :idData");
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

	@SuppressWarnings("deprecation")
	private static List<MyObject> getListMyObject(
			SessionFactory sessionFactory, Session session, int from, int to) {
		List<MyObject> l = null;
		try {
			session = sessionFactory.openSession();

			@SuppressWarnings("unchecked")
			Query<MyObject> q = session.createQuery("from MyObject");
			q.setFirstResult(from);
			q.setMaxResults(to);
			l = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return l;
	}

	@SuppressWarnings("deprecation")
	private static List<MyObject> getListMyObjectByName(
			SessionFactory sessionFactory, Session session, String dataName) {
		List<MyObject> l = null;
		try {
			session = sessionFactory.openSession();

			@SuppressWarnings("unchecked")
			Query<MyObject> q = session
					.createQuery("from MyObject where name like ?0 ");
			q.setParameter(0, "%" + dataName + "%");
			// Query<MyObject> q =
			// session.createQuery("from MyObject where name like :name ");
			// q.setString("name", "%"+dataName+"%");

			l = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return l;
	}

	private static void saveData(SessionFactory sessionFactory, Session session,
			Transaction transaction) {

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			for (int i = 1; i < 5; i++) {
				MyObject o = new MyObject();
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
