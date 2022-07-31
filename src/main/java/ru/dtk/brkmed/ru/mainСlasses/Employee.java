package ru.dtk.brkmed.ru.mainСlasses;

import java.util.GregorianCalendar;

public class Employee {
    private int idEmployee;
    private String name;
    private String surname;
    private String patronymic;

    private String currentPosition;
    private boolean signatureAvailability;
    private GregorianCalendar beginningSignature;
    private GregorianCalendar endSignature;

    public Employee(int idEmployee, String name, String surname, String patronymic, String currentPosition, boolean signatureAvailability, GregorianCalendar beginningSignature, GregorianCalendar endSignature) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.currentPosition = currentPosition;
        this.signatureAvailability = signatureAvailability;
        this.beginningSignature = beginningSignature;
        this.endSignature = endSignature;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isSignatureAvailability() {
        return signatureAvailability;
    }

    public void setSignatureAvailability(boolean signatureAvailability) {
        this.signatureAvailability = signatureAvailability;
    }

    public GregorianCalendar getBeginningSignature() {
        return beginningSignature;
    }

    public void setBeginningSignature(GregorianCalendar beginningSignature) {
        this.beginningSignature = beginningSignature;
    }

    public GregorianCalendar getEndSignature() {
        return endSignature;
    }

    public void setEndSignature(GregorianCalendar endSignature) {
        this.endSignature = endSignature;
    }
}
