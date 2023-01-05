package ru.brkmed.dtk.dao.mainСlasses.entities.references;

import java.util.Arrays;
import java.util.List;

public class OperatingSystemEnum {
     private static String WINDOWSXP = "WINDOWSXP";
     private static String WINDOWS7 = "WINDOWS7";
     private static String WINDOWS10 = "WINDOWS10";
     private static String AstraLinux = "AstraLinux";
     private static String RedOS = "RedOS";
     List<String> listOC =  Arrays.asList(new String[]{WINDOWSXP, WINDOWS7, WINDOWS10, AstraLinux, RedOS});

}
