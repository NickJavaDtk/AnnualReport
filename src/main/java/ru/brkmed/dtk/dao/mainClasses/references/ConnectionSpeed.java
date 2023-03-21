package ru.brkmed.dtk.dao.mainClasses.references;

import java.util.Arrays;
import java.util.List;

public class ConnectionSpeed {
    final static String minSpeed = "до 10 Мбис/с";
    final static String mediumSpeed = "от 10 Мбит/с до 100 Мбит/с";
    final static String maxSpeed = "свыше 100 Мбит/с";
    List<String> listConnection =  Arrays.asList(new String[]{minSpeed, mediumSpeed, maxSpeed});


}
