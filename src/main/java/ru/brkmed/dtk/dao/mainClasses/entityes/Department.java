package ru.brkmed.dtk.dao.mainClasses.entityes;


import ru.brkmed.dtk.dao.mainClasses.references.ConnectionSpeed;
import ru.brkmed.dtk.dao.mainClasses.references.TypeActivity;
import ru.brkmed.dtk.dao.mainClasses.references.TypeDivision;
import ru.brkmed.dtk.dao.mainClasses.references.TypeLocality;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public  class Department extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false, name = "name")
    private String nameDepartment;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Building building;
    @Column(nullable = false, name = "help")
    private String typeHelp;
    @Column(nullable = false, name = "task")
    private String typeTask;
    private boolean rural;
    private boolean fap;
    private boolean statist;

    @OneToMany(mappedBy = "department")
    public Collection<Unit> departments = new ArrayList<>(  );

    @OneToMany(mappedBy = "department")
    public Collection<Connection> connections = new ArrayList<>();

    public Department() {
    }

    public Department(Long id, String nameDepartment, Building building, String typeHelp, String typeTask, boolean rural, boolean fap, boolean statist) {
        Id = id;
        this.nameDepartment = nameDepartment;
        this.building = building;
        this.typeHelp = typeHelp;
        this.typeTask = typeTask;
        this.rural = rural;
        this.fap = fap;
        this.statist = statist;
    }

    public Department(String nameDepartment, Building building, String typeHelp, String typeTask, boolean rural, boolean fap, boolean statist) {
        this.nameDepartment = nameDepartment;
        this.building = building;
        this.typeHelp = typeHelp;
        this.typeTask = typeTask;
        this.rural = rural;
        this.fap = fap;
        this.statist = statist;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getTypeHelp() {
        return typeHelp;
    }

    public void setTypeHelp(String typeHelp) {
        this.typeHelp = typeHelp;
    }

    public String getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(String typeTask) {
        this.typeTask = typeTask;
    }

    public boolean isRural() {
        return rural;
    }

    public void setRural(boolean rural) {
        this.rural = rural;
    }

    public boolean isFap() {
        return fap;
    }

    public void setFap(boolean fap) {
        this.fap = fap;
    }

    public boolean isStatist() {
        return statist;
    }

    public void setStatist(boolean statist) {
        this.statist = statist;
    }
}
