package ru.brkmed.dtk.dao.mainСlasses.entities.references.controler;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
}
