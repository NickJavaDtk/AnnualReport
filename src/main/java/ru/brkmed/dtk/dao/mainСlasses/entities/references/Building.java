package ru.brkmed.dtk.dao.mainСlasses.entities.references;

import javax.persistence.*;
import java.io.Serializable;


//класс Зданий
@Entity
public class Building implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    public String nameBuilding;
    @Column(nullable = false)
    private String adressBuilding;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Unit unit;

    public Building() {

    }

    public Building(String nameBuilding, String adressBuilding) {
        this.nameBuilding = nameBuilding;
        this.adressBuilding = adressBuilding;
    }

    public Building(String nameBuilding, String adressBuilding, Unit unit) {
        this.nameBuilding = nameBuilding;
        this.adressBuilding = adressBuilding;
        this.unit = unit;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public String getAdressBuilding() {
        return adressBuilding;
    }

    public void setAdressBuilding(String adressBuilding) {
        this.adressBuilding = adressBuilding;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
