package ru.brkmed.dtk;

import ru.brkmed.dtk.dao.mainСlasses.entities.references.GenerationOffice;
import ru.brkmed.dtk.dao.mainСlasses.entities.references.Office;
import ru.brkmed.dtk.dao.mainСlasses.entities.references.OfficeBuilder;

public class Main {
    public static void main(String[] args) {
        OfficeBuilder officeBuilder = new Office();
        GenerationOffice generationOffice = new GenerationOffice(officeBuilder);
        Office office = generationOffice.generation();
        System.out.println(office );
    }
}
