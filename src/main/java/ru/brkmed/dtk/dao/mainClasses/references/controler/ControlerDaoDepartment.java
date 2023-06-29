package ru.brkmed.dtk.dao.mainClasses.references.controler;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Connection;
import ru.brkmed.dtk.dao.mainClasses.entityes.Department;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControlerDaoDepartment {
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


    public List<Department> listDepartments () {
        Session session = ControlerDaoDepartment.getFactory().openSession();
        Transaction tx = null;
        List<Department> departmentList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List  departmentTmp =  session.createQuery("select d.Id, d.nameDepartment, d.building, d.typeHelp," +
                    "d.typeTask, d.rural, d.fap, d.statist FROM Department d").list();
            for (Iterator iterator = departmentTmp.iterator(); iterator.hasNext();){
                Object[] obj = (Object[])iterator.next();
                Long id = Long.parseLong(String.valueOf(obj[0]));
                String nameDepartment = String.valueOf(obj[1]);
                Building buildDepartment = (Building)(obj[2]);
                String typeHelp = String.valueOf(obj[3]);
                String typeTask = String.valueOf(obj[4]);
                Boolean rural = Boolean.valueOf(String.valueOf(obj[5]));
                Boolean fap = Boolean.valueOf(String.valueOf(obj[6]));
                Boolean statist = Boolean.valueOf(String.valueOf(obj[7]));

                departmentList.add(new Department(id, nameDepartment , buildDepartment,  typeHelp,
                        typeTask, rural, fap, statist ));
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return departmentList;
    }

    public void addDepartment (Department department) {
        Session session = ControlerDaoDepartment.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateDepartments (Long Id, Department tmpDepartment) {
        Session session = ControlerDaoConnection.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            Department department = (Department) session.get(Department.class, Id);
            department.setNameDepartment(tmpDepartment.getNameDepartment());
            department.setBuilding(tmpDepartment.getBuilding());
            department.setTypeHelp(tmpDepartment.getTypeHelp());
            department.setTypeTask(tmpDepartment.getTypeTask( ));
            department.setRural(tmpDepartment.isRural());
            department.setFap(tmpDepartment.isFap());
            department.setStatist(tmpDepartment.isStatist());
            session.update(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }

    public void deleteDepartments (Long Id){
        Session session = ControlerDaoDepartment.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Department department = (Department) session.get(Department.class, Id);
            department.setBuilding(null);
            session.delete(department);
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
