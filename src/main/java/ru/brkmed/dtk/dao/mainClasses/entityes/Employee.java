package ru.brkmed.dtk.dao.mainClasses.entityes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Employee")
public class Employee extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column (nullable = false, name = "name")
    @NotNull
    private String fullName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CurrentPositionFRMR currentPosition;
    @Column (name = "start_cert")
    @Temporal(TemporalType.DATE)
    private Date beginningSignature;
    @Column (name = "end_cert")
    @Temporal(TemporalType.DATE)
    private Date endSignature;
    @Column (name = "type_position")
    private String typePosition;
    @Column (name = "is_main")
    private boolean mainPosition;
   // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "unit_employee",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "Id"))
    private List<Unit> unitListEmployee = new ArrayList<Unit>();

    
    private String inn;    
   
    private String snils;
    
    private String fon;
    



    public Employee() {

    }

    public Employee(String fullName, CurrentPositionFRMR currentPosition, Date beginningSignature, Date endSignature, String typePosition, boolean mainPosition, String inn, String snils, String fon) {
        this.fullName = fullName;
        this.currentPosition = currentPosition;
        this.beginningSignature = beginningSignature;
        this.endSignature = endSignature;
        this.typePosition = typePosition;
        this.mainPosition = mainPosition;
        this.inn = inn;
        this.snils = snils;
        this.fon = fon;
    }

    public Employee(Long id, String fullName, CurrentPositionFRMR currentPosition, Date beginningSignature, Date endSignature, String typePosition, boolean mainPosition, String inn, String snils, String fon) {
        Id = id;
        this.fullName = fullName;
        this.currentPosition = currentPosition;
        this.beginningSignature = beginningSignature;
        this.endSignature = endSignature;
        this.typePosition = typePosition;
        this.mainPosition = mainPosition;
        this.inn = inn;
        this.snils = snils;
        this.fon = fon;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public CurrentPositionFRMR getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(CurrentPositionFRMR currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getBeginningSignature() {
        return beginningSignature;
    }

    public void setBeginningSignature(Date beginningSignature) {
        this.beginningSignature = beginningSignature;
    }

    public Date getEndSignature() {
        return endSignature;
    }

    public void setEndSignature(Date endSignature) {
        this.endSignature = endSignature;
    }

    public String getTypePosition() {
        return typePosition;
    }

    public void setTypePosition(String typePosition) {
        this.typePosition = typePosition;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getFon() {
        return fon;
    }

    public void setFon(String fon) {
        this.fon = fon;
    }

    public boolean getMainPosition() {
        return mainPosition;
    }

    public void setMainPosition(boolean mainPosition) {
        this.mainPosition = mainPosition;
    }

    public List<Unit> getUnitListEmployee() {
        return unitListEmployee;
    }

    public void setUnitListEmployee(List<Unit> unitListEmployee) {
        this.unitListEmployee = unitListEmployee;
    }

//    @Override
//    public String toString() {
////        return "Employee{" +
////                "fullName='" + fullName + '\'' +
////                ", currentPosition=" + currentPosition +
////                ", beginningSignature=" + beginningSignature +
////                ", endSignature=" + endSignature +
////                ", typePosition='" + typePosition + '\'' +
////                ", mainPosition=" + mainPosition +
////                ", unitEmp=" + unitEmp +
////                ", inn='" + inn + '\'' +
////                ", snils='" + snils + '\'' +
////                ", fon='" + fon + '\'' +
////                '}';
//        int i = fullName.length();
//
//       String name =  String.format("|%-50s|", fullName);
//       String current =  String.format("|%50s|", currentPosition.getWorkPosition());
//       String begin =  String.format("|%td%tm%tY|", beginningSignature, beginningSignature, beginningSignature);
//       String end =  String.format("|%td%tm%tY|", endSignature, endSignature, endSignature);
//       String position = String.format("|%-30s|", typePosition);
//       return (name + " " + current + " " + begin + " " + end + " " + position);
//
//
//    }

    public String[] getObsTypePosition() {
        String[] obsType = new String[]{"Врач", "Средний медицинский персонал", "Младший медицинский персонал", "Административно-хозяйственный персонал", "Вспомогательный персонал"};
        return obsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass( ) != o.getClass( )) return false;
        Employee employee = (Employee) o;
        return Id.equals(employee.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}