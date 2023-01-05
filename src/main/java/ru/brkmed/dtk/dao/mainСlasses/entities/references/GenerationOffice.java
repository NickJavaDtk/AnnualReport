package ru.brkmed.dtk.dao.mainСlasses.entities.references;

public class GenerationOffice {
    OfficeBuilder officeBuilder;

    public GenerationOffice(OfficeBuilder officeBuilder) {
        super();
        this.officeBuilder = officeBuilder;
    }

    public Office generation() {
        return officeBuilder.setDepartament().setEmployee().setCabinetEquipmentARM( ).setCabinetEquipmentPrinter().build();
    }
}
