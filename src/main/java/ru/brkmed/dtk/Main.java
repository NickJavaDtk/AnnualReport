package ru.brkmed.dtk;

import ru.brkmed.dtk.dao.mainСlasses.references.GenerationOffice;
import ru.brkmed.dtk.dao.mainСlasses.entities.Office;
import ru.brkmed.dtk.dao.mainСlasses.references.OfficeBuilder;

public class Main {
    public static void main(String[] args) {
        OfficeBuilder officeBuilder = new Office();
        GenerationOffice generationOffice = new GenerationOffice(officeBuilder);
        Office office = generationOffice.generation();
        System.out.println(office );
    }
}
