package ru.brkmed.dtk.dao.mainСlasses.entities.references;

import java.util.Arrays;
import java.util.List;

public class TypeActivity {
    final static String ADMINISTRATION = "Административно-хозяйственная";
    final static String MEDICAL = "Медицинская";
    List<String> listTypeActivity =  Arrays.asList(new String[]{ADMINISTRATION, MEDICAL});
}
