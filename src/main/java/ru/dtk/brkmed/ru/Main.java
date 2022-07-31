package ru.dtk.brkmed.ru;

import ru.dtk.brkmed.ru.mainСlasses.GenerationOffice;
import ru.dtk.brkmed.ru.mainСlasses.Office;
import ru.dtk.brkmed.ru.mainСlasses.OfficeBuilder;

public class Main {
    public static void main(String[] args) {
        OfficeBuilder officeBuilder = new Office();
        GenerationOffice generationOffice = new GenerationOffice(officeBuilder);
        Office office = generationOffice.generation();
        System.out.println(office );
    }
}
