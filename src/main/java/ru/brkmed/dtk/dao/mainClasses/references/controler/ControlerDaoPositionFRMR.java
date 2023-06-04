package ru.brkmed.dtk.dao.mainClasses.references.controler;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControlerDaoPositionFRMR {
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

    public void loadNsi(List<CurrentPositionFRMR> list) {
        Session session = ControlerDaoPositionFRMR.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (CurrentPositionFRMR current: list) {
                session.save(current);
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

    public List<CurrentPositionFRMR> listCurrentPositionFRMR() {
        Session session = ControlerDaoPositionFRMR.getFactory().openSession();
        Transaction tx = null;
        Integer parent;
        Integer med;
        String sMed = "";
        String sParent = "";
        List<CurrentPositionFRMR> currentPositionFRMRS = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List  currentTmp =  session.createQuery("select c.id, c.idNsi, c.parentId, c.workPosition," +
                    " c.visibility, c.med FROM CurrentPositionFRMR c").list();
            for (Iterator iterator = currentTmp.iterator(); iterator.hasNext();){
                Object[] obj = (Object[])iterator.next();
                Long id = Long.parseLong(String.valueOf(obj[0]));
                Integer id_Nsi = Integer.parseInt(String.valueOf(obj[1]));
                sParent = String.valueOf(obj[2]);
                if(sParent != null && !sParent.equals("null")) {
                    parent = Integer.parseInt(sParent);
                } else {
                    parent = 0;
                }
                String workPos = String.valueOf(obj[3]);
                Boolean visibility = Boolean.valueOf(String.valueOf(obj[4]));
                sMed = String.valueOf(obj[5]);
                if (sMed != null && !sMed.equals("null")) {
                    med = Integer.parseInt(sMed);
                } else {
                    med = 0;
                }
                currentPositionFRMRS.add(new CurrentPositionFRMR(id, id_Nsi, parent, workPos, visibility, med));
            }
            // System.out.println(building);
            tx.commit();
        } catch (HibernateException  e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return currentPositionFRMRS;
    }

    public void updateCurrentPosition(Long Id, Boolean visibility) {
        Session session = ControlerDaoPositionFRMR.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            CurrentPositionFRMR current = (CurrentPositionFRMR) session.get(CurrentPositionFRMR.class, Id);
            current.setVisibility(visibility);
            session.update(current);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }

    public List<CurrentPositionFRMR> listCurrentIsVisible() {
        Session session = ControlerDaoPositionFRMR.getFactory().openSession();
        Transaction tx = null;
        Integer parent;
        Integer med;
        String sMed = "";
        String sParent = "";
        List<CurrentPositionFRMR> currentPositionFRMRS = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List  currentTmp =  session.createQuery("select c.Id, c.idNsi, c.parentId, c.workPosition," +
                    " c.visibility, c.med FROM CurrentPositionFRMR c WHERE c.visibility = true").list();
            for (Iterator iterator = currentTmp.iterator(); iterator.hasNext();){
                Object[] obj = (Object[])iterator.next();
                Long id = Long.parseLong(String.valueOf(obj[0]));
                Integer id_Nsi = Integer.parseInt(String.valueOf(obj[1]));
                sParent = String.valueOf(obj[2]);
                if(sParent != null && !sParent.equals("null")) {
                    parent = Integer.parseInt(sParent);
                } else {
                    parent = 0;
                }
                String workPos = String.valueOf(obj[3]);
                Boolean visibility = Boolean.valueOf(String.valueOf(obj[4]));
                sMed = String.valueOf(obj[5]);
                if (sMed != null && !sMed.equals("null")) {
                    med = Integer.parseInt(sMed);
                } else {
                    med = 0;
                }
                currentPositionFRMRS.add(new CurrentPositionFRMR(id, id_Nsi, parent, workPos, visibility, med));
            }
            // System.out.println(building);
            tx.commit();
        } catch (HibernateException  e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return currentPositionFRMRS;
    }

}
