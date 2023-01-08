package ru.brkmed.dtk.dao.mainСlasses.references.controler;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.brkmed.dtk.dao.mainСlasses.entities.Building;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControlerDaoBuilding {
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


    public List<Building> listBuilding() {
        Session session = ControlerDaoBuilding.getFactory().openSession();
        Transaction tx = null;
        List<Building> building = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List  buildingTmp =  session.createQuery("select b.id, b.nameBuilding, b.adressBuilding FROM Building b").list();
            for (Iterator iterator = buildingTmp.iterator(); iterator.hasNext();){
                Object[] obj = (Object[])iterator.next();
                Long id = Long.parseLong(String.valueOf(obj[0]));
                String nameBuild = String.valueOf(obj[1]);
                String adressBuild = String.valueOf(obj[2]);
                building.add(new Building(id, nameBuild ,adressBuild ));
            }
            // System.out.println(building);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return building;
    }

    public void addBuild(String nameBuild, String adressBuild) {
        Session session = ControlerDaoBuilding.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Building build = new Building( nameBuild, adressBuild);
            session.save(build);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateBuild(Long Id, String nameBuild, String adress) {
        Session session = ControlerDaoBuilding.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            Building build = (Building) session.get(Building.class, Id);
            build.setNameBuilding(nameBuild);
            build.setAdressBuilding(adress);
            session.update(build);
            tx.commit();
            } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }
}
