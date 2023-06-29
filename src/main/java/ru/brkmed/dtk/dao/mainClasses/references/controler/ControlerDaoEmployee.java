package ru.brkmed.dtk.dao.mainClasses.references.controler;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Connection;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControlerDaoEmployee {
    private static SessionFactory factory;


    public static SessionFactory getFactory() {
        try {

            factory = new Configuration( ).configure( ).buildSessionFactory( );
            return factory;

        } catch (Throwable x) {
            System.out.println(x);
        }
        return factory;
    }

    public List<Employee> listEmployees() {
        Session session = ControlerDaoEmployee.getFactory().openSession();
        Transaction tx = null;
        List<Employee> employeeList = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat( "yyyy-MM-dd", Locale.ENGLISH );
        try {
            tx = session.beginTransaction();
            List  employeeTmp =  session.createQuery("select e.id, e.fullName, e.currentPosition, e.beginningSignature," +
                    "e.endSignature, e.typePosition, e.mainPosition, e.inn, e.snils, e.fon FROM Employee e" ).setMaxResults(5) .list();
            for (Iterator iterator = employeeTmp.iterator(); iterator.hasNext();){
                Object[] obj = (Object[])iterator.next();
                Long id = Long.parseLong(String.valueOf(obj[0]));
                String fullName = String.valueOf(obj[1]);
                CurrentPositionFRMR current = (CurrentPositionFRMR) (obj[2]);
                String sDate = String.valueOf(obj[3]);
                Date startDate = formatDate.parse(sDate);
                String eDate = String.valueOf(obj[4]);
                Date endDate = formatDate.parse(eDate);
                String typePosition = String.valueOf(obj[5]);
                Boolean mainPosition = Boolean.valueOf(String.valueOf(obj[6]));
                String inn = String.valueOf(obj[7]);
                String snils = String.valueOf(obj[8]);
                String fon = String.valueOf(obj[9]);

                employeeList.add(new Employee(id, fullName , current, startDate, endDate, typePosition, mainPosition,
                        inn, snils, fon));
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } catch (ParseException parse ) {
            parse.printStackTrace();
        }
        finally {
            session.close();
        }
        return employeeList;
    }

    public void loadFile(List<Employee> list) {
        Session session = ControlerDaoEmployee.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (Employee employees: list) {
                session.save(employees);
                // tx.commit();
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addEmployee(Employee employee) {
        Session session = ControlerDaoEmployee.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployees(Long Id, Employee employee) {
        Session session = ControlerDaoEmployee.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            Employee employees =  session.get(Employee.class, Id);
            employees.setFullName(employee.getFullName());
            employees.setCurrentPosition(employee.getCurrentPosition());
            employees.setBeginningSignature(employee.getBeginningSignature());
            employees.setEndSignature(employee.getEndSignature());
            employees.setTypePosition(employee.getTypePosition( ));
            employees.setMainPosition(employee.getMainPosition());
            employees.setInn(employee.getInn( ));
            employees.setSnils(employee.getSnils( ));
            employees.setFon(employee.getFon( ));
            session.update(employees);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }

    public void deleteEmployee(Long Id){
        Session session = ControlerDaoEmployee.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =  session.get(Employee.class, Id);
            employee.setCurrentPosition(null);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
