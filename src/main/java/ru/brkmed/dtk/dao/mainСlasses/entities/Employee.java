package ru.brkmed.dtk.dao.mainСlasses.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.*;

import com.sun.istack.NotNull;
import org.hibernate.*;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmployee;
    @Column (nullable = true)
    @NotNull
    private String name;
    @Column (nullable = true)
    private String surname;
    @Column (nullable = true)
    private String patronymic;
    @Column (nullable = true)

    private String currentPosition;
    private boolean signatureAvailability;
    @Temporal(TemporalType.DATE)
    private Date beginningSignature;
    @Temporal(TemporalType.DATE)
    private Date endSignature;

    public Employee() {

    }

    public Employee(String name, String surname, String patronymic, String currentPosition, boolean signatureAvailability, Date beginningSignature, Date endSignature) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.currentPosition = currentPosition;
        this.signatureAvailability = signatureAvailability;
        this.beginningSignature = beginningSignature;
        this.endSignature = endSignature;
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

//    public GregorianCalendar getBeginningSignature() {
//        return beginningSignature;
//    }
//
//    public void setBeginningSignature(GregorianCalendar beginningSignature) {
//        this.beginningSignature = beginningSignature;
//    }
//
//    public GregorianCalendar getEndSignature() {
//        return endSignature;
//    }
//
//    public void setEndSignature(GregorianCalendar endSignature) {
//        this.endSignature = endSignature;
//    }
//}
}