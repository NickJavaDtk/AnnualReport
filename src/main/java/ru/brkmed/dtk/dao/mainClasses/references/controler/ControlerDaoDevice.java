package ru.brkmed.dtk.dao.mainClasses.references.controler;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.entityes.Device;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;
import ru.brkmed.dtk.dao.mainClasses.entityes.Unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControlerDaoDevice {
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

    public List<Device> listDevices() {
        Session session = ControlerDaoDevice.getFactory().openSession();
        Transaction tx = null;
        List<Device> deviceList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List  deviceTmp =  session.createQuery("select d.Id, d.typeDevice, d.number, d.brand," +
                    "d.typeOC, d.term, d.connect FROM Device d").list();
            for (Iterator iterator = deviceTmp.iterator(); iterator.hasNext();){
                Object[] obj = (Object[])iterator.next();
                Long id = Long.parseLong(String.valueOf(obj[0]));
                String typeDevice = String.valueOf(obj[1]);
                String number = String.valueOf(obj[2]);
                String brand = String.valueOf(obj[3]);
                String typeOC = String.valueOf(obj[4]);
                Integer term = Integer.parseInt(String.valueOf(obj[5]));
                Boolean connect = Boolean.valueOf(String.valueOf(obj[6]));

                deviceList.add(new Device(id, typeDevice , number, brand, typeOC, term, connect));
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return deviceList;
    }

    public List<Device> listDevicesUnit() {
        Session session = ControlerDaoDevice.getFactory().openSession();
        Transaction tx = null;
        List<Device> deviceList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
//            List  deviceTmp =  session.createQuery("select d.Id, d.typeDevice, d.number, d.brand," +
//                    " d.typeOC, d.term, d.connect, d.unitDev FROM Device d left outer join Unit u unitDev = u.Id").list();
            List deviceTmp =  session.createQuery("from Device d").list();
            deviceList.addAll(deviceTmp);
//            List<Object[]> deviceTmp = session.createQuery("select d from Device d left outer join d.unitDev").list();
//            for (Object[] obj : deviceTmp){
//               // Object[] obj = (Object[])iterator.next();
//                Long id = Long.parseLong(String.valueOf(obj[0]));
//                String typeDevice = String.valueOf(obj[1]);
//                String number = String.valueOf(obj[2]);
//                String brand = String.valueOf(obj[3]);
//                String typeOC = String.valueOf(obj[4]);
//                Integer term = Integer.parseInt(String.valueOf(obj[5]));
//                Boolean boolValue = Boolean.valueOf(String.valueOf(obj[6]));
//                Unit unit = (Unit) obj[7];
//
//                deviceList.add(new Device(id, typeDevice , number, brand, typeOC, term, boolValue, unit));
//            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return deviceList;
    }



    public void addDevice(Device device) {
        Session session = ControlerDaoDevice.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(device);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateDevices(Long Id, Device device) {
        Session session = ControlerDaoDevice.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            Device devices =  session.get(Device.class, Id);
            devices.setTypeDevice(device.getTypeDevice());
            devices.setNumber(device.getNumber());
            devices.setBrand(device.getBrand());
            devices.setTypeOC(device.getTypeOC());
            devices.setTerm(device.getTerm());
            devices.setConnect(device.isConnect());
            devices.setUnitDev(device.getUnitDev());
            session.update(devices);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }

    public void updateDevicesUnit(Long Id, Device device) {
        Session session = ControlerDaoDevice.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction( );
            Device devices =  session.get(Device.class, Id);
            devices.setUnitDev(device.getUnitDev());
            session.update(devices);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback( );
            e.printStackTrace( );

        } finally {
            session.close();
        }
    }

    public void deleteDevice(Long Id){
        Session session = ControlerDaoDevice.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Device device =  session.get(Device.class, Id);
            session.delete(device);
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
