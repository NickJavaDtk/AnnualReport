package ru.brkmed.dtk.dao.mainСlasses.entities.references;

import java.util.Arrays;
import java.util.List;

public class TypeLocality {
    final static String DISTRICT = "Районная";
    final static String RURAL = "Сельская";
    final static String FAP = "ФАП";
    List<String> listTypeLocality =  Arrays.asList(new String[]{DISTRICT, RURAL, FAP});

}
