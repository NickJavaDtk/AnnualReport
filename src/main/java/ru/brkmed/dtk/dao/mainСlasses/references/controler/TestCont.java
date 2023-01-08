package ru.brkmed.dtk.dao.mainСlasses.references.controler;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.brkmed.dtk.dao.mainСlasses.entities.Building;
import ru.brkmed.dtk.dao.mainСlasses.entities.Unit;

public class TestCont {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration(  ).configure().buildSessionFactory();
            TestCont cont = new TestCont();
            cont.addDao();
        } catch (Throwable t) {
            t.printStackTrace(  );
        }
    }

    private void addDao() {
      Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Building buildingVZ = new Building( "Здание взрослой поликлиники", "Ленина 218" );
            Building buildingDP = new Building( "Здание детской поликлиники", "Ленина 218" );
            Building buildingBAK = new Building( "Здание баклаборатории", "Ленина 218" );
            Unit unit = new Unit();
            unit.setNameUnit("Взрослая поликлиника");
            unit.setSpeedConnection("100 мб");
            unit.setTypeConnection("ЛВС");
            unit.getListBuilding().add(buildingVZ);
            unit.getListBuilding().add(buildingDP);
            buildingVZ.setUnit(unit);
            buildingDP.setUnit(unit);

            Unit unit1 = new Unit();
            unit1.setNameUnit("Детская поликлиника");
            unit1.setSpeedConnection("100 мб");
            unit1.setTypeConnection("ЛВС и Оптоволокно");
            unit1.getListBuilding().add(buildingDP);
            unit1.getListBuilding().add(buildingBAK);
            buildingBAK.setUnit(unit1);

            session.save(unit);
            session.save(unit1);
            session.save(buildingVZ);
            session.save(buildingDP);
            session.save(buildingBAK);
//            session.save(unit2);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback( );
            }
            e.printStackTrace( );
        } finally {
            session.close( );
        }
    }
}
