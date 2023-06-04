package ru.brkmed.dtk.dao.mainClasses.entityes;

import ru.brkmed.dtk.dao.mainClasses.references.OperatingSystemEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "PositionFRMR")
public class CurrentPositionFRMR extends AbstractEntity {
    //Спровочник НСИ 1.2.643.5.1.13.13.99.2.709_3.4.xml
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "id_Nsi")
    @Basic(optional = false)
    private Integer idNsi;
    @Column(name = "parent_Id")
    private Integer parentId;
    @Column(name = "work_position")
    private String workPosition;
    @Column(name = "visibility")
    private boolean visibility;
    @Column(name = "id_FRMO")
    private Integer med;
    @OneToMany(mappedBy = "currentPosition")
    public Collection<Employee> employee = new ArrayList<>(  );

    public CurrentPositionFRMR() {
    }

    public CurrentPositionFRMR(Long id, Integer idNsi, Integer parentId, String workPosition, boolean visibility, Integer med) {
        Id = id;
        this.idNsi = idNsi;
        this.parentId = parentId;
        this.workPosition = workPosition;
        this.visibility = visibility;
        this.med = med;
    }

    public CurrentPositionFRMR(Integer idNsi, Integer parentId, String workPosition, Integer med) {
        this.idNsi = idNsi;
        this.parentId = parentId;
        this.workPosition = workPosition;
        this.med = med;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getIdNsi() {
        return idNsi;
    }

    public void setIdNsi(Integer idNsi) {
        this.idNsi = idNsi;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public Integer getMed() {
        return med;
    }

    public void setMed(Integer med) {
        this.med = med;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
