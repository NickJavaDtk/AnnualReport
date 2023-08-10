package ru.brkmed.dtk.reports.form30.table7000;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Table7000Dao {
    private static SessionFactory factory;
    private final String cell14_7 = "select count(*) from Device d left join d.unitDev where d.unitDev.task = :task and d.unitDev.helpPolic = :helpPolic " +
            "and d.typeDevice = :device";
    private final String cell18other = "select count(*) from Device d left join d.unitDev where d.typeDevice = :device";
    private final String cell24_7 = "select count(*) from Device d left join d.unitDev where d.unitDev.task = :task and d.unitDev.helpPolic = :helpPolic " +
            "and d.typeDevice = :device and d.term > :term";
    private final String cell34_7 = "select count(*) from Device d left join d.unitDev where d.unitDev.task = :task and d.unitDev.helpPolic = :helpPolic " +
            "and d.typeDevice = :device and d.typeOC = :typeOC";

    private final String cell58other = "select count(*) from Device d left join d.unitDev where d.typeDevice = :device and d.typeOC = :typeOC";
    private final String cell104_7 = "select count(*) from Device d left join d.unitDev where d.unitDev.task = :task and d.unitDev.helpPolic = :helpPolic " +
            "and d.typeDevice =:device and d.connect = :connect";
    private final String cell108other = "select count(*) from Device d left join d.unitDev where d.typeDevice = :device and d.connect = :connect";
    private final String cell124_7 = "select count(*) from Device d left join d.unitDev where d.unitDev.task = :task and d.unitDev.helpPolic = :helpPolic " +
            "and d.typeDevice =:device and d.unitDev.department.rural = :rural";
    private final String cell128other = "select count(*) from Device d left join d.unitDev where d.typeDevice = :device and d.unitDev.department.rural = :rural";
    private final String cell134_7 = "select count(*) from Device d left join d.unitDev where d.unitDev.task = :task and d.unitDev.helpPolic = :helpPolic " +
            "and d.typeDevice =:device and d.unitDev.department.fap = :fap";
    private final String cell138other = "select count(*) from Device d left join d.unitDev where d.typeDevice = :device and d.unitDev.department.fap = :fap";
    private final String cell15 = "select count(*) from Device d left join d.unitDev where d.typeDevice like 'VipNet%'";
    private final String cell194_7 = "select count(*) from Connection c where c.task = :task and c.helpPolic = :helpPolic";
    private final String cell204_7 = "select count(*) from Connection c where c.task = :task and c.helpPolic = :helpPolic and c.typeConnection = :type";
    private final String cell264_7 = "select count(*) from Connection c where c.task = :task and c.helpPolic = :helpPolic and c.speedConnection < :speed";
    private final String cell274_7 = "select count(*) from Connection c where c.task = :task and c.helpPolic = :helpPolic and (c.speedConnection >= :minSpeed and c.speedConnection <= :maxSpeed)";
    private final String cell284_7 = "select count(*) from Connection c where c.task = :task and c.helpPolic = :helpPolic and c.speedConnection > :speed";
    private final String cell294_7 = "select count(*) from Connection c left join c.department where c.task = :task and c.helpPolic = :helpPolic and c.department.fap = :fap";



    //private final String cell14other = "select d.Id, d.typeDevice, d.number, d.unitDev.nameUnit, d.unitDev.task from Device d left join d.unitDev where d.typeDevice =:device";


  //  private final String test = "select d.Id, d.typeDevice, d.number, d.unitDev.nameUnit, d.unitDev.task  from Device d left join d.unitDev where d.unitDev.task =:task" ;



    public static SessionFactory getFactory() {
        try {
            factory = new Configuration(  ).configure().buildSessionFactory();
            return factory;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return factory;
    }

    public List<Table7000> getValue() {
        Session session = getFactory().openSession();
        Transaction transaction = null;
        List<Table7000> list = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
//            Query queryRow = session.createQuery(cell14);
//            queryRow.setParameter("task",  false);
//            queryRow.setParameter("helpPolic", true);
//            queryRow.setParameter("device", "Персональные компьютеры");
//           Object obj = queryRow.list();
          //  value = (Integer) queryRow.uniqueResult();
//            Query dsd = session.createQuery(cell14other);
//            dsd.setParameter("device", "Персональные компьютеры");
//            Object obj =  dsd.list();

            List<Query> cells14_7Query = getMainQuery(session, cell14_7, "device", "Персональные компьютеры");
            Table7000 cell14_7Table = partialFilling(cells14_7Query);
            cell14_7Table.setName(Table7000.cellOne);
            cell14_7Table.setNumberString("001");
            setOther(session, cell18other, cell14_7Table);
            list.add(cell14_7Table);

            List<Query> cells24_7Query = getMainSubQuery(session, cell24_7, "device", "Персональные компьютеры", "term", 5);
            Table7000 cell24_7Table = partialFilling(cells24_7Query);
            cell24_7Table.setName(Table7000.cellTwo);
            cell24_7Table.setNumberString("1_1");
            cell24_7Table.setOther(0L);
            list.add(cell24_7Table);

            List<Query> cells34_7Query = getMainSubQuery(session, cell34_7, "device", "Персональные компьютеры", "typeOC", "ОС семейства Windows");
            Table7000 cell34_7Table = partialFilling(cells34_7Query);
            cell34_7Table.setName(Table7000.cellThree);
            cell34_7Table.setNumberString("1_2");
            cell34_7Table.setOther(0L);
            list.add(cell34_7Table);

            List<Query> cells44_7Query = getMainSubQuery(session, cell34_7, "device", "Персональные компьютеры", "typeOC", "ОС отечественной разработки");
            Table7000 cell44_7Table = partialFilling(cells44_7Query);
            cell44_7Table.setName(Table7000.cellFour);
            cell44_7Table.setNumberString("1_3");
            cell44_7Table.setOther(0L);
            list.add(cell44_7Table);

            List<Query> cells54_7Query = getMainSubQuery(session, cell34_7, "device", "Персональные компьютеры", "typeOC", "иные ОС");
            Table7000 cell54_7Table = partialFilling(cells54_7Query);
            cell54_7Table.setName(Table7000.cellFive);
            cell54_7Table.setNumberString("1_4");
            setSubOther(session, cell58other, cell54_7Table, "typeOC", "Иные ОС");
            list.add(cell54_7Table);

            List<Query> cells64_7Query = getMainQuery(session, cell14_7, "device", "Серверное оборудование");
            Table7000 cell64_7Table = partialFilling(cells64_7Query);
            cell64_7Table.setName(Table7000.cellSix);
            cell64_7Table.setNumberString("002");
            cell44_7Table.setOther(0L);
            list.add(cell64_7Table);

            List<Query> cells74_7Query = getMainSubQuery(session, cell24_7, "device", "Серверное оборудование", "term", 5);
            Table7000 cell74_7Table = partialFilling(cells74_7Query);
            cell74_7Table.setName(Table7000.cellSeven);
            cell74_7Table.setNumberString("2_1");
            cell74_7Table.setOther(0L);
            list.add(cell74_7Table);

            List<Query> cells84_7Query = getMainQuery(session, cell14_7, "device", "Печатающие устройства и МФУ");
            Table7000 cell84_7Table = partialFilling(cells84_7Query);
            cell84_7Table.setName(Table7000.cellEight);
            cell84_7Table.setNumberString("003");
            cell84_7Table.setOther(0L);
            list.add(cell84_7Table);

            List<Query> cells94_7Query = getMainSubQuery(session, cell24_7, "device", "Печатающие устройства и МФУ", "term", 5);
            Table7000 cell94_7Table = partialFilling(cells94_7Query);
            cell94_7Table.setName(Table7000.cellNine);
            cell94_7Table.setNumberString("3.1");
            cell94_7Table.setOther(0L);
            list.add(cell94_7Table);

            List<Query> cells104_7Query = getMainSubQuery(session, cell104_7, "device", "Персональные компьютеры", "connect", true);
            Table7000 cell104_7Table = partialFilling(cells104_7Query);
            cell104_7Table.setName(Table7000.cellTen);
            cell104_7Table.setNumberString("004");
            setSubOther(session, cell108other, cell104_7Table, "connect", true);
            list.add(cell104_7Table);

            Table7000 cell114_7Table = new Table7000(cell104_7Table.getName(), cell104_7Table.getNumberString(), cell104_7Table.getTotal(), cell104_7Table.getTaskPolic(),
                    cell104_7Table.getTaskHosp(), cell104_7Table.getHelpPolic(), cell104_7Table.getHelpHosp(), cell104_7Table.getOther());
            cell114_7Table.setName(Table7000.cellEleven);
            cell114_7Table.setNumberString("4.1");
            list.add(cell114_7Table);

            List<Query> cells124_7Query = getMainSubQuery(session, cell124_7, "device", "Персональные компьютеры",  "rural", true);
            Table7000 cell124_7Table = partialFilling(cells124_7Query);
            cell124_7Table.setName(Table7000.cellTwelve);
            cell124_7Table.setNumberString("4.2");
            setSubOther(session, cell128other, cell124_7Table,  "rural", true);
            list.add(cell124_7Table);

            List<Query> cells134_7Query = getMainSubQuery(session, cell134_7, "device", "Персональные компьютеры",  "fap", true);
            Table7000 cell134_7Table = partialFilling(cells134_7Query);
            cell134_7Table.setName(Table7000.cellThirteen);
            cell134_7Table.setNumberString("4.2.1");
            setSubOther(session, cell138other, cell134_7Table,  "fap", true);
            list.add(cell134_7Table);

            List<Query> cells144_7Query = getMainQuery(session, cell14_7, "device", "Мобильные АРМ (планшеты)");
            Table7000 cell144_7Table = partialFilling(cells144_7Query);
            cell144_7Table.setName(Table7000.cellFourteen);
            cell144_7Table.setNumberString("4.3");
            cell144_7Table.setOther(0L);
            list.add(cell144_7Table);

            Query cell15Query = session.createQuery(cell15);
            Table7000 cell15Table = new Table7000(  );
            cell15Table.setName(Table7000.cellFifteen);
            cell15Table.setNumberString("4.4");
            cell15Table.setTotal((Long) cell15Query.uniqueResult());
            list.add(cell15Table);

            Query cell16Query = session.createQuery(cell18other);
            cell16Query.setParameter("device", "VipNet Coordinator HW50");
            Table7000 cell16Table = new Table7000(  );
            cell16Table.setName(Table7000.cellSixteen);
            cell16Table.setNumberString("4.4.1");
            cell16Table.setTotal((Long) cell16Query.uniqueResult());
            list.add(cell16Table);

            Query cell17Query = session.createQuery(cell18other);
            cell17Query.setParameter("device", "VipNet Coordinator HW100");
            Table7000 cell17Table = new Table7000(  );
            cell17Table.setName(Table7000.cellSeventeen);
            cell17Table.setNumberString("4.4.2");
            cell17Table.setTotal((Long) cell17Query.uniqueResult());
            list.add(cell17Table);

            Query cell18Query = session.createQuery(cell18other);
            cell18Query.setParameter("device", "VipNet Coordinator HW1000");
            Table7000 cell18Table = new Table7000(  );
            cell18Table.setName(Table7000.cellEighteen);
            cell18Table.setNumberString("4.4.3");
            cell18Table.setTotal((Long) cell18Query.uniqueResult());
            list.add(cell18Table);


            Table7000 cell19Table = new Table7000();
            cell19Table.setName(Table7000.cellNineteen);
            cell19Table.setNumberString("005");
            Query cell19_4Query = session.createQuery(cell194_7);
            cell19_4Query.setParameter("task", true  );
            cell19_4Query.setParameter("helpPolic", true  );
            cell19Table.setTaskPolic((Long) cell19_4Query.uniqueResult());
            Query cell19_5Query = session.createQuery(cell194_7);
            cell19_5Query.setParameter("task", true  );
            cell19_5Query.setParameter("helpPolic", false  );
            cell19Table.setTaskHosp((Long) cell19_5Query.uniqueResult());
            Query cell19_6Query = session.createQuery(cell194_7);
            cell19_6Query.setParameter("task", false  );
            cell19_6Query.setParameter("helpPolic", true  );
            cell19Table.setHelpPolic((Long) cell19_6Query.uniqueResult());
            Query cell19_7Query = session.createQuery(cell194_7);
            cell19_7Query.setParameter("task", false  );
            cell19_7Query.setParameter("helpPolic", false  );
            cell19Table.setHelpHosp((Long) cell19_7Query.uniqueResult());
            cell19Table.setTotal(cell19Table.getTaskPolic() + cell19Table.getTaskHosp() + cell19Table.getHelpPolic() + cell19Table.getHelpHosp());
            list.add(cell19Table);



            List<Query> cell20Query = getMainQuery(session, cell204_7, "type", "Модем");
            Table7000 cell20Table = partialFilling(cell20Query);
            cell20Table.setName(Table7000.cellTwenty);
            cell20Table.setNumberString("5.1");
            cell20Table.setOther(0L);
            list.add(cell20Table);

            List<Query> cell21Query = getMainQuery(session, cell204_7, "type", "DSL");
            Table7000 cell21Table = partialFilling(cell21Query);
            cell21Table.setName(Table7000.cellTwentyOne);
            cell21Table.setNumberString("5.2");
            cell21Table.setOther(0L);
            list.add(cell21Table);

            List<Query> cell22Query = getMainQuery(session, cell204_7, "type", "Оптоволокно");
            Table7000 cell22Table = partialFilling(cell22Query);
            cell22Table.setName(Table7000.cellTwentyTwo);
            cell22Table.setNumberString("5.3");
            cell22Table.setOther(0L);
            list.add(cell22Table);

            List<Query> cell23Query = getMainQuery(session, cell204_7, "type", "Радиодоступ");
            Table7000 cell23Table = partialFilling(cell23Query);
            cell23Table.setName(Table7000.cellTwentyThree);
            cell23Table.setNumberString("5.4");
            cell23Table.setOther(0L);
            list.add(cell23Table);

            List<Query> cell24Query = getMainQuery(session, cell204_7, "type", "Спутниковый канал");
            Table7000 cell24Table = partialFilling(cell24Query);
            cell24Table.setName(Table7000.cellTwentyFour);
            cell24Table.setNumberString("5.5");
            cell24Table.setOther(0L);
            list.add(cell24Table);

            List<Query> cell25Query = getMainQuery(session, cell204_7, "type", "VPN");
            Table7000 cell25Table = partialFilling(cell25Query);
            cell25Table.setName(Table7000.cellTwentyFive);
            cell25Table.setNumberString("5.6");
            cell25Table.setOther(0L);
            list.add(cell25Table);

            List<Query> cell26Query = getMainQuery(session, cell264_7, "speed", 10D);
            Table7000 cell26Table = partialFilling(cell26Query);
            cell26Table.setName(Table7000.cellTwentySex);
            cell26Table.setNumberString("5.7");
            cell26Table.setOther(0L);
            list.add(cell26Table);

            List<Query> cell27Query = getMainSubQuery(session, cell274_7, "minSpeed", 10D, "maxSpeed", 100D);
            Table7000 cell27Table = partialFilling(cell27Query);
            cell27Table.setName(Table7000.cellTwentySeven);
            cell27Table.setNumberString("5.8");
            cell27Table.setOther(0L);
            list.add(cell27Table);

            List<Query> cell28Query = getMainQuery(session, cell284_7, "speed", 100D);
            Table7000 cell28Table = partialFilling(cell28Query);
            cell28Table.setName(Table7000.cellTwentyEight);
            cell28Table.setNumberString("5.9");
            cell28Table.setOther(0L);
            list.add(cell28Table);

            List<Query> cell29Query = getMainQuery(session, cell294_7, "fap", true);
            Table7000 cell29Table = partialFilling(cell29Query);
            cell29Table.setName(Table7000.cellTwentyNine);
            cell29Table.setNumberString("006");
            cell29Table.setOther(0L);
            list.add(cell28Table);

            String s = "dsds";


        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
      return list;
    }

    public List<Query> getMainQuery(Session session, String sqlString, String inputParameter, Object object) {
        List<Query> list = new ArrayList<>();
        Query first = session.createQuery(sqlString);
        first.setParameter("task", true);
        first.setParameter("helpPolic", true);
        first.setParameter(inputParameter, object);
        list.add(first);
        Query second = session.createQuery(sqlString);
        second.setParameter("task", true);
        second.setParameter("helpPolic", false);
        second.setParameter(inputParameter, object);
        list.add(second);
        Query third = session.createQuery(sqlString);
        third.setParameter("task", false);
        third.setParameter("helpPolic", true);
        third.setParameter(inputParameter, object);
        list.add(third);
        Query fourth = session.createQuery(sqlString);
        fourth.setParameter("task", false);
        fourth.setParameter("helpPolic", false);
        fourth.setParameter(inputParameter, object);
        list.add(fourth);
        return list;

    }

    public List<Query> getMainSubQuery(Session session, String sqlString, String inputParameter, Object object, String addInputParameter, Object addObject) {
        List<Query> list = new ArrayList<>();
        Query first = session.createQuery(sqlString);
        first.setParameter("task", true);
        first.setParameter("helpPolic", true);
        first.setParameter(inputParameter, object);
        first.setParameter(addInputParameter, addObject);
        list.add(first);
        Query second = session.createQuery(sqlString);
        second.setParameter("task", true);
        second.setParameter("helpPolic", false);
        second.setParameter(inputParameter, object);
        second.setParameter(addInputParameter, addObject);
        list.add(second);
        Query third = session.createQuery(sqlString);
        third.setParameter("task", false);
        third.setParameter("helpPolic", true);
        third.setParameter(inputParameter, object);
        third.setParameter(addInputParameter, addObject);
        list.add(third);
        Query fourth = session.createQuery(sqlString);
        fourth.setParameter("task", false);
        fourth.setParameter("helpPolic", false);
        fourth.setParameter(inputParameter, object);
        fourth.setParameter(addInputParameter, addObject);
        list.add(fourth);
        return list;

    }


    public List<Query> getMainSubQueryAdd(Session session, String sqlString, String inputParameter, Object object, String addInputParameter, Object addObject, String addSecondInputParameter, Object addSecondObject) {
        List<Query> list = new ArrayList<>();
        Query first = session.createQuery(sqlString);
        first.setParameter("task", true);
        first.setParameter("helpPolic", true);
        first.setParameter(inputParameter, object);
        first.setParameter(addInputParameter, addObject);
        first.setParameter(addSecondInputParameter, addSecondObject);
        list.add(first);
        Query second = session.createQuery(sqlString);
        second.setParameter("task", true);
        second.setParameter("helpPolic", false);
        second.setParameter(inputParameter, object);
        second.setParameter(addInputParameter, addObject);
        second.setParameter(addSecondInputParameter, addSecondObject);
        list.add(second);
        Query third = session.createQuery(sqlString);
        third.setParameter("task", false);
        third.setParameter("helpPolic", true);
        third.setParameter(inputParameter, object);
        third.setParameter(addInputParameter, addObject);
        third.setParameter(addSecondInputParameter, addSecondObject);
        list.add(third);
        Query fourth = session.createQuery(sqlString);
        fourth.setParameter("task", false);
        fourth.setParameter("helpPolic", false);
        fourth.setParameter(inputParameter, object);
        fourth.setParameter(addInputParameter, addObject);
        fourth.setParameter(addSecondInputParameter, addSecondObject);
        list.add(fourth);
        return list;

    }



    public void setOther(Session session, String sqlString, Table7000 table7000 ) {
        Query query = session.createQuery(sqlString);
        query.setParameter("device", "Мобильные АРМ (планшеты)");
        table7000.setOther((Long) query.uniqueResult());
    }

    public void setSubOther(Session session, String sqlString, Table7000 table7000, String inputParameter, Object object ) {
        Query query = session.createQuery(sqlString);
        query.setParameter("device", "Мобильные АРМ (планшеты)");
        query.setParameter(inputParameter, object);
        table7000.setOther((Long) query.uniqueResult());
    }

    public void setSubOtherAdd(Session session, String sqlString, Table7000 table7000, String inputParameter, Object object, String inputAddParameter, Object addObject ) {
        Query query = session.createQuery(sqlString);
        query.setParameter("device", "Мобильные АРМ (планшеты)");
        query.setParameter(inputParameter, object);
        query.setParameter(inputAddParameter, addObject);
        table7000.setOther((Long) query.uniqueResult());
    }



    public Table7000 partialFilling(List<Query> cell) {
        Table7000 table = new Table7000();
        for(int i = 0; i < cell.size(); i++ ) {
            switch (i) {
                case (0):
                    table.setTaskPolic((Long) cell.get(i).uniqueResult());
                    break;
                case (1):
                    table.setTaskHosp((Long) cell.get(i).uniqueResult());
                    break;
                case (2):
                    table.setHelpPolic((Long) cell.get(i).uniqueResult());
                    break;
                case (3):
                    table.setHelpHosp((Long) cell.get(i).uniqueResult());
                    break;
            }

        }
        table.setTotal(table.getTaskPolic( ) + table.getTaskHosp( ) + table.getHelpPolic( ) + table.getHelpHosp( ));
        return table;
    }


}
