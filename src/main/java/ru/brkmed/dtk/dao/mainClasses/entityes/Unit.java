package ru.brkmed.dtk.dao.mainClasses.entityes;




import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//класс Подразделений
@Entity
public class Unit extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "name_unit", nullable = false)
    private String nameUnit;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   // @Fetch(value = FetchMode.SUBSELECT)
    private Department department;
    @Column(name = "is_polic", nullable = false)
    private Boolean helpPolic;
    @Column(name = "is_hosp", nullable = false)
    private Boolean helpHosp;
    @Column(name = "is_help", nullable = false)
    private Boolean help;
    @Column(name = "is_task", nullable = false)
    private Boolean task;
    //@OneToMany(mappedBy = "unitEmp", fetch = FetchType.LAZY)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "unit_employee",
            joinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "Id"))
    List<Employee> employees = new ArrayList<>( );
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "unit_device",
//            joinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "device_id", referencedColumnName = "Id"))
    @OneToMany(mappedBy = "unitDev")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Device> devices = new ArrayList<Device>( );


    public Unit() {
    }

    public Unit(String nameUnit, Department department, boolean helpPolic, boolean helpHosp, boolean help, boolean task, List<Employee> employees, List<Device> devices) {
        this.nameUnit = nameUnit;
        this.department = department;
        this.helpPolic = helpPolic;
        this.helpHosp = helpHosp;
        this.help = help;
        this.task = task;
        this.employees = employees;
        this.devices = devices;
    }

    public Unit(Long id, String nameUnit, Department department, boolean helpPolic, boolean helpHosp, boolean help, boolean task, List<Employee> employees, List<Device> devices) {
        Id = id;
        this.nameUnit = nameUnit;
        this.department = department;
        this.helpPolic = helpPolic;
        this.helpHosp = helpHosp;
        this.help = help;
        this.task = task;
        this.employees = employees;
        this.devices = devices;
    }

    public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isHelpPolic() {
        return helpPolic != null ? helpPolic : false;
    }

    public void setHelpPolic(Boolean helpPolic) {
         Boolean bool =  helpPolic != null ? helpPolic : false;
         this.helpPolic = bool;
    }

    public boolean isHelpHosp() {
        return helpHosp != null ? helpHosp : false;
    }

    public void setHelpHosp(Boolean helpHosp) {
        Boolean bool = helpHosp != null ? helpHosp : false;
        this.helpHosp = bool;
    }

    public boolean isHelp() {
        return help != null ? help : false;
    }

    public void setHelp(Boolean help) {
        Boolean bool = help != null ? help : false;
        this.help = bool;
    }

    public boolean isTask() {
        return task != null ? task : false;
    }

    public void setTask(Boolean task) {
        Boolean bool = task != null ? task : false;
        this.task = bool;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
        devices.forEach(device -> device.setUnitDev(this));
    }
}