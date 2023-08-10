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

public class ControlerDaoConnection {
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


    public List<Connection> listConnections() {
        Session session = ControlerDaoConnection.getFactory().openSession();
        Transaction tx = null;
        List<Connection> connectionList = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat( "yyyy-MM-dd", Locale.ENGLISH );
        try {
            tx = session.beginTransaction();
            List  connectionTmp =  session.createQuery("select c.Id, c.nameConnection, c.department, c.dateConnection, c.suplier, c.typeConnection, c.speedConnection, c.tax, c.startTax, c.typeTax, c.helpPolic, " +
                    "c.helpHosp, c.help, c.task FROM Connection c").list();
            for (Iterator iterator = connectionTmp.iterator(); iterator.hasNext();){
                Object[] obj = (Object[])iterator.next();
                Long id = Long.parseLong(String.valueOf(obj[0]));
                String nameConnect = String.valueOf(obj[1]);
                Department depConnect = (Department) (obj[2]);
                Date dateConnect = formatDate.parse(String.valueOf(obj[3]));
                String suplierConnect = String.valueOf(obj[4]);
                String typeConnect = String.valueOf(obj[5]);
                Double speedConnect = Double.parseDouble(String.valueOf(obj[6]));
                Double taxConnect = Double.parseDouble(String.valueOf(obj[7]));
                Double startTax = Double.parseDouble(String.valueOf(obj[8]));
                String typeTaxConnect = String.valueOf(obj[9]);
                Boolean helpPolic = Boolean.valueOf(String.valueOf(obj[10]));
                Boolean helpHosp = Boolean.valueOf(String.valueOf(obj[11]));
                Boolean help = Boolean.valueOf(String.valueOf(obj[12]));
                Boolean task = Boolean.valueOf(String.valueOf(obj[13]));

                connectionList.add(new Connection(id, nameConnect , depConnect, helpPolic, helpHosp, help, task, dateConnect, suplierConnect, typeConnect,
                        speedConnect, taxConnect, startTax, typeTaxConnect ));
            }
            // System.out.println(building);
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
        return connectionList;
    }

    public void addConnection(Connection connect) {
        Session session = ControlerDaoConnection.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(connect);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateConnections(Long Id, Connection tmpConnect) {
        Session session = ControlerDaoConnection.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            Connection connect = (Connection) session.get(Connection.class, Id);
            connect.setNameConnection(tmpConnect.getNameConnection());
            connect.setDepartment(tmpConnect.getDepartment());
            connect.setHelpPolic(tmpConnect.getHelpPolic());
            connect.setHelpHosp(tmpConnect.getHelpHosp());
            connect.setHelp(tmpConnect.getHelp());
            connect.setTask(tmpConnect.getTask());
            connect.setDateConnection(tmpConnect.getDateConnection());
            connect.setSuplier(tmpConnect.getSuplier( ));
            connect.setTypeConnection(tmpConnect.getTypeConnection( ));
            connect.setSpeedConnection(tmpConnect.getSpeedConnection( ));
            connect.setTax(tmpConnect.getTax( ));
            connect.setStartTax(tmpConnect.getStartTax( ));
            connect.setTypeTax(tmpConnect.getTypeTax( ));
            session.update(connect);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }

    public void deleteConnection(Long Id){
        Session session = ControlerDaoConnection.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Connection connect = (Connection) session.get(Connection.class, Id);
            connect.setDepartment(null);
            session.delete(connect);
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
