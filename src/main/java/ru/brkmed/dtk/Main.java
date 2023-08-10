package ru.brkmed.dtk;

import ru.brkmed.dtk.dao.mainClasses.references.GenerationOffice;
import ru.brkmed.dtk.dao.mainClasses.entityes.Office;
import ru.brkmed.dtk.dao.mainClasses.references.OfficeBuilder;
import ru.brkmed.dtk.reports.form30.table7000.Table7000;
import ru.brkmed.dtk.reports.form30.table7000.Table7000Dao;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//       // OfficeBuilder officeBuilder = new Office();
//        GenerationOffice generationOffice = new GenerationOffice(officeBuilder);
//        Office office = generationOffice.generation();
//        System.out.println(office );
        Table7000Dao table7000Dao = new Table7000Dao();
        List<Table7000> list = table7000Dao.getValue();
        System.out.println(list );
    }
}
