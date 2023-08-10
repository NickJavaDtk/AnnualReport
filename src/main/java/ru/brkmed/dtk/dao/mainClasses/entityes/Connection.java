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
    protected Department department;
    @Column(name = "is_polic", nullable = true)
    private Boolean helpPolic;
    @Column(name = "is_hosp", nullable = true)
    private Boolean helpHosp;
    @Column(name = "is_help", nullable = true)
    private Boolean help;
    @Column(name = "is_task", nullable = true)
    private Boolean task;
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

    public Connection(Long id, String nameConnection, Department department, Boolean helpPolic, Boolean helpHosp, Boolean help, Boolean task, Date dateConnection, String suplier, String typeConnection, Double speedConnection, Double tax, Double startTax, String typeTax) {
        Id = id;
        this.nameConnection = nameConnection;
        this.department = department;
        this.helpPolic = helpPolic;
        this.helpHosp = helpHosp;
        this.help = help;
        this.task = task;
        this.dateConnection = dateConnection;
        this.suplier = suplier;
        this.typeConnection = typeConnection;
        this.speedConnection = speedConnection;
        this.tax = tax;
        this.startTax = startTax;
        this.typeTax = typeTax;
    }

    public Connection(String nameConnection, Department department, Boolean helpPolic, Boolean helpHosp, Boolean help, Boolean task, Date dateConnection, String suplier, String typeConnection, Double speedConnection, Double tax, Double startTax, String typeTax) {
        this.nameConnection = nameConnection;
        this.department = department;
        this.helpPolic = helpPolic;
        this.helpHosp = helpHosp;
        this.help = help;
        this.task = task;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getHelpPolic() {
        return helpPolic != null ? helpPolic : null;
    }

    public void setHelpPolic(Boolean helpPolic) {
        Boolean bool = helpPolic != null ? helpPolic : false;
        this.helpPolic = bool;

    }

    public Boolean getHelpHosp() {
        return helpHosp != null ? helpHosp : false;
    }

    public void setHelpHosp(Boolean helpHosp) {
        Boolean bool = helpHosp != null ? helpHosp : false;
        this.helpHosp = bool;

    }

    public Boolean getHelp() {
        return help != null ? help : false;
    }

    public void setHelp(Boolean help) {
        Boolean bool = help != null ? help : false;
        this.help = bool;
    }

    public Boolean getTask() {
        return task != null ? task : false;
    }

    public void setTask(Boolean task) {
        Boolean bool = task != null ? task : false;
        this.task = bool;
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
        String[] obsType = new String[]{"Модем", "DSL", "Оптоволокно", "Радиодоступ", "Спутниковый канал", "VPN"};
        return obsType;
    }

    public String[] getObsTypeTax() {
        String[] obsType = new String[]{"Оператор связи", "Региональный бюджет", "Собственные средства МО", "Федеральный бюджет", "Фонд ОМС", "Не указано"};
        return obsType;
    }
}
