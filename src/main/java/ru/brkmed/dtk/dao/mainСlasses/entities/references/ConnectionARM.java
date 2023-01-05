package ru.brkmed.dtk.dao.mainСlasses.entities.references;

import java.util.Arrays;
import java.util.List;

public  class ConnectionARM {
    final static String MODEM = "Модем";
    final static String xDSL = "xDSL";
    final static String OPTICAL_FIBER = "Оптоволокно";
    final static String RADIO_STATION = "Радиоточка";
    final static String SATELLITE_CHANNEL = "Спутниковый канал";

    public List<String> getListConnection() {
        List<String> listConnection = Arrays.asList(new String[]{MODEM, xDSL, OPTICAL_FIBER, RADIO_STATION, SATELLITE_CHANNEL});
        return listConnection;
    }
}
