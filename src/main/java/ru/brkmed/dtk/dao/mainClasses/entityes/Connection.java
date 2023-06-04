package ru.brkmed.dtk.dao.mainClasses.entityes;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Connection extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    private String nameConnection;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Building build;
    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    @NotNull
    private Date dateConnection;
    @Column(nullable = false)
    private String suplier;
    @Column(nullable = false)
    private String typeConnection;
    @Column(nullable = false)
    private Double speedConnection;
    @Column(nullable = false)
    private Double tax;
    @Column(nullable = false)
    private Double startTax;
    @Column(nullable = false)
    private String typeTax;

    public Connection() {
    }

    public Connection(String nameConnection, Building build, Date dateConnection, String suplier,
                      String typeConnection, Double speedConnection, Double tax, Double startTax, String typeTax) {
        this.nameConnection = nameConnection;
        this.build = build;
        this.dateConnection = dateConnection;
        this.suplier = suplier;
        this.typeConnection = typeConnection;
        this.speedConnection = speedConnection;
        this.tax = tax;
        this.startTax = startTax;
        this.typeTax = typeTax;
    }

    public Connection(Long id, String nameConnection, Building build, Date dateConnection, String suplier,
                      String typeConnection, Double speedConnection, Double tax, Double startTax, String typeTax) {
        Id = id;
        this.nameConnection = nameConnection;
        this.build = build;
        this.dateConnection = dateConnection;
        this.suplier = suplier;
        this.typeConnection = typeConnection;
        this.speedConnection = speedConnection;
        this.tax = tax;
        this.startTax = startTax;
        this.typeTax = typeTax;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNameConnection() {
        return nameConnection;
    }

    public void setNameConnection(String nameConnection) {
        this.nameConnection = nameConnection;
    }

    public Building getBuild() {
        return build;
    }

    public void setBuild(Building build) {
        this.build = build;
    }

    public Date getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date dateConnection) {
        this.dateConnection = dateConnection;
    }

    public String getSuplier() {
        return suplier;
    }

    public void setSuplier(String suplier) {
        this.suplier = suplier;
    }

    public String getTypeConnection() {
        return typeConnection;
    }

    public void setTypeConnection(String typeConnection) {
        this.typeConnection = typeConnection;
    }

    public Double getSpeedConnection() {
        return speedConnection;
    }

    public void setSpeedConnection(Double speedConnection) {
        this.speedConnection = speedConnection;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getStartTax() {
        return startTax;
    }

    public void setStartTax(Double startTax) {
        this.startTax = startTax;
    }

    public String getTypeTax() {
        return typeTax;
    }

    public void setTypeTax(String typeTax) {
        this.typeTax = typeTax;
    }

    public String[] getObsTypeConnect() {
        String[] obsType = new String[]{"Модем", "DSL", "Оптоволокно", "Радиодоступ", "Спутниковый канал", "VPN" };
        return obsType;
    }

    public String[] getObsTypeTax() {
        String[] obsType = new String[]{"Оператор связи", "Региональный бюджет", "Собственные средства МО", "Федеральный бюджет", "Фонд ОМС", "Не указано" };
        return obsType;
    }
}
