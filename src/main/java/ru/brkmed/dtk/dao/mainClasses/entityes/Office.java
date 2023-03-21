package ru.brkmed.dtk.dao.mainClasses.entityes;


import ru.brkmed.dtk.dao.mainClasses.references.*;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Office implements OfficeBuilder, Serializable {
    Department department;
    protected String numberOffice;
    Employee employee;

    public Office() {
    }

    CabinetEquipmentARM cabinetEquipmentARM;
    CabinetEquipmentPrinter cabinetEquipmentPrinter;

    String nameDivision;
    TypeDivision typeDivision;
    TypeActivity typeActivity;
    TypeLocality typeLocality;
    ConnectionARM connectionARM;
    ConnectionSpeed connectionSpeed;

    int idEmployee;
    String nameEmploee;
    String surnameEmploee;
    String patronymicEmploee;
    String currentPosition;
    boolean signatureAvailability;
    GregorianCalendar beginningSignature;
    GregorianCalendar endSignature;

    OperatingSystemEnum operatingSystemEnum;
    private boolean operationPeriodARM;
    private boolean connectedSecureNetwork;

    private String namePrinter;
    private boolean operationPeriodPrinter;
    private boolean typePrinter;

    public Office(Department department, String numberOffice, Employee employee, CabinetEquipmentARM cabinetEquipmentARM, CabinetEquipmentPrinter cabinetEquipmentPrinter) {
    }

    @Override
    public OfficeBuilder setDepartament() {
        this.department = new Department(nameDivision,  typeDivision,  typeActivity,  typeLocality,  connectionARM, connectionSpeed);
        return this;
    }

    @Override
    public OfficeBuilder setNumberOffice(String numberOffice) {
        this.numberOffice = numberOffice;
        return this;
    }

    @Override
    public OfficeBuilder setEmployee() {
 //       this.employee = new Employee(idEmployee, nameEmploee, surnameEmploee, patronymicEmploee, currentPosition, signatureAvailability, beginningSignature, endSignature);
        return this;
    }

    @Override
    public OfficeBuilder setCabinetEquipmentARM() {
        this.cabinetEquipmentARM = new CabinetEquipmentARM(operatingSystemEnum, operationPeriodARM, connectedSecureNetwork);
        return this;
    }

    @Override
    public OfficeBuilder setCabinetEquipmentPrinter() {
        this.cabinetEquipmentPrinter = new CabinetEquipmentPrinter(namePrinter, operationPeriodPrinter, typePrinter);
        return this;
    }

    @Override
    public Office build() {
        Office office = new Office(department, numberOffice, employee, cabinetEquipmentARM, cabinetEquipmentPrinter);
        return office;
    }

    @Override
    public String toString() {
        return "Office{" +
                "department=" + department +
                ", numberOffice='" + numberOffice + '\'' +
                ", employee=" + employee +
                ", cabinetEquipmentARM=" + cabinetEquipmentARM +
                ", cabinetEquipmentPrinter=" + cabinetEquipmentPrinter +
                ", nameDivision='" + nameDivision + '\'' +
                ", typeDivision=" + typeDivision +
                ", typeActivity=" + typeActivity +
                ", typeLocality=" + typeLocality +
                ", connectionARM=" + connectionARM +
                ", connectionSpeed=" + connectionSpeed +
                ", idEmployee=" + idEmployee +
                ", nameEmploee='" + nameEmploee + '\'' +
                ", surnameEmploee='" + surnameEmploee + '\'' +
                ", patronymicEmploee='" + patronymicEmploee + '\'' +
                ", currentPosition='" + currentPosition + '\'' +
                ", signatureAvailability=" + signatureAvailability +
                ", beginningSignature=" + beginningSignature +
                ", endSignature=" + endSignature +
                ", operatingSystemEnum=" + operatingSystemEnum +
                ", operationPeriodARM=" + operationPeriodARM +
                ", connectedSecureNetwork=" + connectedSecureNetwork +
                ", namePrinter='" + namePrinter + '\'' +
                ", operationPeriodPrinter=" + operationPeriodPrinter +
                ", typePrinter=" + typePrinter +
                '}';
    }
}
