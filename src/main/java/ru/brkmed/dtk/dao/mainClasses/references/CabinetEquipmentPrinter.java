package ru.brkmed.dtk.dao.mainClasses.references;

public class CabinetEquipmentPrinter {
    private String namePrinter;
    private boolean operationPeriodPrinter;
    private boolean typePrinter;

    public CabinetEquipmentPrinter(String namePrinter, boolean operationPeriodPrinter, boolean typePrinter) {
        this.namePrinter = namePrinter;
        this.operationPeriodPrinter = operationPeriodPrinter;
        this.typePrinter = typePrinter;
    }

    public String getNamePrinter() {
        return namePrinter;
    }

    public void setNamePrinter(String namePrinter) {
        this.namePrinter = namePrinter;
    }

    public boolean isOperationPeriodPrinter() {
        return operationPeriodPrinter;
    }

    public void setOperationPeriodPrinter(boolean operationPeriodPrinter) {
        this.operationPeriodPrinter = operationPeriodPrinter;
    }

    public boolean isTypePrinter() {
        return typePrinter;
    }

    public void setTypePrinter(boolean typePrinter) {
        this.typePrinter = typePrinter;
    }
}
