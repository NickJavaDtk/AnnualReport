package ru.brkmed.dtk.dao.mainСlasses.references;

import ru.brkmed.dtk.dao.mainСlasses.entities.Office;

public interface OfficeBuilder {
    public OfficeBuilder setDepartament();
    public OfficeBuilder setNumberOffice(String numberOffice);
    public OfficeBuilder setEmployee();
    public OfficeBuilder setCabinetEquipmentARM();
    public OfficeBuilder setCabinetEquipmentPrinter();

    public Office build();

}
