package ru.brkmed.dtk.dao.mainClasses.entityes;


import ru.brkmed.dtk.dao.mainClasses.references.ConnectionSpeed;
import ru.brkmed.dtk.dao.mainClasses.references.TypeActivity;
import ru.brkmed.dtk.dao.mainClasses.references.TypeDivision;
import ru.brkmed.dtk.dao.mainClasses.references.TypeLocality;


public  class Department {
    private String nameDivision;
    protected TypeDivision typeDivision;
    protected TypeActivity typeActivity;
    protected TypeLocality typeLocality;

    protected ConnectionARM connectionARM;

    protected ConnectionSpeed connectionSpeed;

    public Department(String nameDivision, TypeDivision typeDivision, TypeActivity typeActivity, TypeLocality typeLocality, ConnectionARM connectionARM, ConnectionSpeed connectionSpeed) {
        this.nameDivision = nameDivision;
        this.typeDivision = typeDivision;
        this.typeActivity = typeActivity;
        this.typeLocality = typeLocality;
        this.connectionARM = connectionARM;
        this.connectionSpeed = connectionSpeed;
    }

    public String getNameDivision() {
        return nameDivision;
    }

    public void setNameDivision(String nameDivision) {
        this.nameDivision = nameDivision;
    }

    public TypeDivision getTypeDivision() {
        return typeDivision;
    }

    public void setTypeDivision(TypeDivision typeDivision) {
        this.typeDivision = typeDivision;
    }

    public TypeActivity getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(TypeActivity typeActivity) {
        this.typeActivity = typeActivity;
    }

    public TypeLocality getTypeLocality() {
        return typeLocality;
    }

    public void setTypeLocality(TypeLocality typeLocality) {
        this.typeLocality = typeLocality;
    }

    public ConnectionARM getConnectionARM() {
        return connectionARM;
    }

    public void setConnectionARM(ConnectionARM connectionARM) {
        this.connectionARM = connectionARM;
    }

    public ConnectionSpeed getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(ConnectionSpeed connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }
}
