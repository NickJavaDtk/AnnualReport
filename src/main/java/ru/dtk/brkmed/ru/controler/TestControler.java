package ru.dtk.brkmed.ru.controler;

import com.fasterxml.classmate.AnnotationConfiguration;
import ru.dtk.brkmed.ru.mainСlasses.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TestControler {
    private static SessionFactory factory;

    public static void main(String[] args) {
       try {
          factory = new Configuration().configure().buildSessionFactory();
       } catch (Throwable x) {
           System.out.println(x);
        }
      //  TestControler testControler = new TestControler();
      //  Long empID1 = testControler.addEmployee("Zara", "Ali", 1000);
      //  Long empID2 = testControler.addEmployee("Daisy", "Das", 5000);
      //  Long empID3 = testControler.addEmployee("John", "Paul", 10000);

        /* List down all the employees */
      //  testControler.listEmployees();

        /* Update employee's records */
      // testControler.updateEmployee(empID1, 5000);

        /* Delete an employee from the database */
      //  testControler.deleteEmployee(empID2);

        /* List down new list of the employees */
      //  testControler.listEmployees();

    }
    /* Method to CREATE an employee in the database */
    public Long addEmployee(String name, String surname, String patronymic, String cPosition, boolean isSert, Date startDate,
                            Date endDate) {
            Session session =  getFactory().openSession( );
            Transaction tx = null;
            Long employeeID = null;

            try {
                tx = session.beginTransaction( );
                Employee employee = new Employee(name, surname, patronymic, cPosition, isSert, startDate, endDate);
                employeeID = (Long) session.save(employee);
                tx.commit( );
            } catch (HibernateException e) {
                if (tx != null) tx.rollback( );
                e.printStackTrace( );
            } finally {
                session.close( );
            }
            return employeeID;

    }

    /* Method to  READ all the employees */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
               // System.out.print("First Name: " + employee.getFirstName());
               // System.out.print("  Last Name: " + employee.getLastName());
               // System.out.println("  Salary: " + employee.getSalary());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Long EmployeeID, int salary ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
           // employee.setSalary( salary );
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Long EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static SessionFactory getFactory() {
        try {

                factory = new Configuration( ).configure( ).buildSessionFactory( );
//            Configuration config = new Configuration().configure();
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//            factory = config.buildSessionFactory(serviceRegistry);
                return factory;

        } catch (Throwable x) {
            System.out.println(x);
        }
        return factory;
    }
}
