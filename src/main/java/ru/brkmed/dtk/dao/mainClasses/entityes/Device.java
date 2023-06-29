package ru.brkmed.dtk.dao.mainClasses.entityes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Device extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "device", nullable = false)
    private String typeDevice;
    private String number;
    private String brand;
    private String typeOC;
    private Integer term;
    @Column(name = "connect_SN", nullable = false)
    private Boolean connect;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "unit_device",
//            joinColumns = @JoinColumn(name = "device_id", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "Id"))
//    private List<Unit> unitListDevice = new ArrayList<Unit>(  );
    @ManyToOne(optional = true)
    private Unit unitDev;

    public Device() {
    }

    public Device(Long id, String typeDevice, String number, String brand, String typeOC, int term, Boolean connect) {
        Id = id;
        this.typeDevice = typeDevice;
        this.number = number;
        this.brand = brand;
        this.typeOC = typeOC;
        this.term = term;
        this.connect = connect;
    }

    public Device(String typeDevice, String number, String brand, String typeOC, int term, Boolean connect) {
        this.typeDevice = typeDevice;
        this.number = number;
        this.brand = brand;
        this.typeOC = typeOC;
        this.term = term;
        this.connect = connect;
    }

    public Device(String typeDevice, String number, String brand, String typeOC, int term, boolean connect, Unit unitDev) {
        this.typeDevice = typeDevice;
        this.number = number;
        this.brand = brand;
        this.typeOC = typeOC;
        this.term = term;
        this.connect = connect;
        this.unitDev = unitDev;
    }

    public Device(Long id, String typeDevice, String number, String brand, String typeOC, Integer term, Boolean connect, Unit unitDev) {
        Id = id;
        this.typeDevice = typeDevice;
        this.number = number;
        this.brand = brand;
        this.typeOC = typeOC;
        this.term = term;
        this.connect = connect;
        this.unitDev = unitDev;
    }

    @Override
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTypeDevice() {
        return typeDevice;
    }

    public void setTypeDevice(String typeDevice) {
        this.typeDevice = typeDevice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTypeOC() {
        return typeOC;
    }

    public void setTypeOC(String typeOC) {
        this.typeOC = typeOC;
    }

    public boolean isConnect() {
       return connect != null ? connect  : false;

    }

    public void setConnect(Boolean connect) {
        if (connect != null) {
            this.connect = connect;
        } else {
            this.connect = false;
        }
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Unit getUnitDev() {
        return unitDev;
    }

    public void setUnitDev(Unit unitDev) {
        this.unitDev = unitDev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass( ) != o.getClass( )) return false;
        Device device = (Device) o;
        return Id.equals(device.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, typeDevice, number, brand, typeOC, term, connect, unitDev);
    }
}
