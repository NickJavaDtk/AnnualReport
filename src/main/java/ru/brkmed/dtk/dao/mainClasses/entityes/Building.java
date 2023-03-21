package ru.brkmed.dtk.dao.mainClasses.entityes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


//класс Зданий
@Entity
public class Building extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    public String nameBuilding;
    @Column(nullable = false)
    private String adressBuilding;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Unit unit;
    @OneToMany(mappedBy = "build")
    public Collection<Connection> listConnection = new ArrayList<>(  );

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

    public Building(Long id, String nameBuilding, String adressBuilding) {
        Id = id;
        this.nameBuilding = nameBuilding;
        this.adressBuilding = adressBuilding;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
