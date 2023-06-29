package ru.brkmed.dtk.dao.mainClasses.references.controler;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.brkmed.dtk.dao.mainClasses.entityes.Department;
import ru.brkmed.dtk.dao.mainClasses.entityes.Device;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;
import ru.brkmed.dtk.dao.mainClasses.entityes.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControlerDaoUnit {
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
    public List<Unit> listUnits() {
        Session session = ControlerDaoUnit.getFactory().openSession();
        Transaction tx = null;
        List<Unit> unitList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
           // List  unitTmp =  session.createQuery("select u.Id, u.nameUnit, u.department, u.helpPolic," +
           //         "u.helpHosp, u.help, u.task, u.employees, u.devices FROM Unit u").list();

//            List unitTmp = session.createQuery("select u.Id, u.nameUnit, u.department, u.helpPolic," +
//                             "u.helpHosp, u.help, u.task, u.employees, u.devices FROM Unit u " +
//                    "left outer join Employee e on u.Id = e.unitEmp " +
//                    "left outer join Device d on u.Id = d.unitDev").list();

//            List unitTmp = session.createQuery("select u.*, e.*, d.* from Unit u " +
//                    "left outer join Employee e on u.")
//            List unitTmp = session.createQuery("select u from Unit u left join fetch u.unitEmp " +
//                    "left join fetch u.unitDev").list();
            List unitTmp = session.createQuery("SELECT u FROM Unit u JOIN FETCH u.department").list();
            unitList.addAll(unitTmp);
//            for (Iterator iterator = unitTmp.iterator(); iterator.hasNext();){
//                Object[] obj = (Object[])iterator.next();
//                Long id = Long.parseLong(String.valueOf(obj[0]));
//                String nameUnit = String.valueOf(obj[1]);
//                Department department = (Department) (obj[2]);
//                Boolean helpPolic = Boolean.valueOf(String.valueOf(obj[3]));
//                Boolean helpHosp = Boolean.valueOf(String.valueOf(obj[4]));
//                Boolean help = Boolean.valueOf(String.valueOf(obj[5]));
//                Boolean task = Boolean.valueOf(String.valueOf(obj[6]));
//                List<Employee> employee = (List<Employee>) (obj[7]);
//                List<Device> device = (List<Device>) (obj[8]);
//
//
//                unitList.add(new Unit(id, nameUnit , department, helpPolic, helpHosp, help, task, employee, device));
//            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return unitList;
    }



    public void addUnits(Unit unit) {
        Session session = ControlerDaoUnit.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            session.persist(unit);
//            session.persist(unit.getDevices());
            session.save(unit);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateUnits(Long Id, Unit unit) {
        Session session = ControlerDaoUnit.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            Unit units =  session.get(Unit.class, Id);
            units.setNameUnit(unit.getNameUnit());
            units.setDepartment(unit.getDepartment());
            units.setHelpPolic(unit.isHelpPolic());
            units.setHelpHosp(unit.isHelpHosp());
            units.setHelp(unit.isHelp());
            units.setTask(unit.isTask());
            units.setEmployees(unit.getEmployees());
            units.setDevices(unit.getDevices());
            //session.saveOrUpdate(units);
            //session.update(units);
            session.merge(units);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }

    public void deleteUnit(Long Id){
        Session session = ControlerDaoDevice.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Unit unit =  session.get(Unit.class, Id);
            unit.setDepartment(null);
            unit.setEmployees(null);
            unit.setDevices(null);
            session.delete(unit);
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
