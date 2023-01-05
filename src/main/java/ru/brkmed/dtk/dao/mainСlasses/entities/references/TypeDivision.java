package ru.brkmed.dtk.dao.mainСlasses.entities.references;

import java.util.Arrays;
import java.util.List;

public class TypeDivision {
    final static String AMBULATORY = "Амбулаторная";
    final static String HOSPITAL = "Стационарная";
    List<String> listTypeDivision =  Arrays.asList(new String[]{AMBULATORY, HOSPITAL});

}
