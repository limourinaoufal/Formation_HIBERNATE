package ma.lndroid.tp.inheritance.table.per.concrete;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.inheritance.table.per.concrete.dto.Contract_Employee;
import ma.lndroid.tp.inheritance.table.per.concrete.dto.Employee;
import ma.lndroid.tp.inheritance.table.per.concrete.dto.Regular_Employee;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public class Main {

	private static final String PATH = "ma/lndroid/tp/inheritance/table/per/concrete/ressource/hibernate.cfg.xml";

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateManager
				.getSessionFactory(PATH);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Employee emp = new Employee();
			emp.setName("Lina");

			Regular_Employee regEmp = new Regular_Employee();
			regEmp.setSalary(new Float(10000.0));
			regEmp.setBonus(new Integer(500));

			Contract_Employee conEmp = new Contract_Employee();
			conEmp.setContractPeriod("SEPTEMBRE");
			conEmp.setPayPerHour(new Float(50.5));

			session.save(emp);
			session.save(regEmp);
			session.save(conEmp);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
	
			
		

	}

}
