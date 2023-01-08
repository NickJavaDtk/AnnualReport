package ru.brkmed.dtk.dao.mainСlasses.entities;




import ru.brkmed.dtk.dao.mainСlasses.entities.Building;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

//класс Подразделений
@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUnit;
    @Column(nullable = false)
    private String nameUnit;

    @OneToMany(mappedBy = "unit")
    public Collection<Building> listBuilding = new ArrayList<>(  );

    @Column
    private String typeConnection;
    @Column
    private String speedConnection;


    public Unit() {
    }

    public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    public Collection<Building> getListBuilding() {
        return listBuilding;
    }

    public void setListBuilding(Collection<Building> listBuilding) {
        this.listBuilding = listBuilding;
    }

    public String getTypeConnection() {
        return typeConnection;
    }

    public void setTypeConnection(String typeConnection) {
        this.typeConnection = typeConnection;
    }

    public String getSpeedConnection() {
        return speedConnection;
    }

    public void setSpeedConnection(String speedConnection) {
        this.speedConnection = speedConnection;
    }
}
