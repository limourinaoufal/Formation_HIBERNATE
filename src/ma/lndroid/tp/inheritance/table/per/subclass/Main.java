package ma.lndroid.tp.inheritance.table.per.subclass;


import ma.lndroid.tp.config.HibernateManager;
import ma.lndroid.tp.inheritance.table.per.subclass.dto.Contract_Employee;
import ma.lndroid.tp.inheritance.table.per.subclass.dto.Employee;
import ma.lndroid.tp.inheritance.table.per.subclass.dto.Regular_Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	
	private static final String PATH = "ma/lndroid/tp/inheritance/table/per/subclass/ressource/hibernate.cfg.xml";
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = HibernateManager.getSessionFactory(PATH);
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
		}finally{
			session.close();
		}
		
	
	}

}
