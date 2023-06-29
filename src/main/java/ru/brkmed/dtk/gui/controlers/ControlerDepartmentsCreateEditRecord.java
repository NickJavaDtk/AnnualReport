package ru.brkmed.dtk.gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Department;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDepartment;
import ru.brkmed.dtk.gui.main.GUIDepartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControlerDepartmentsCreateEditRecord extends AbstractChildWindow {
    @FXML
    private Button btnSave;

    @FXML
    private ChoiceBox<String> chbBuild;

    @FXML
    private ChoiceBox<String> chbTypeHelp;

    @FXML
    private ChoiceBox<String> chbTypeTask;

    @FXML
    private CheckBox chkFAP;

    @FXML
    private CheckBox chkRural;

    @FXML
    private CheckBox chkStat;

    @FXML
    private TextField txtNameDepartment;

    ControlerDaoDepartment daoDepartment = new ControlerDaoDepartment();

    @FXML
    void btnSaveAction(ActionEvent event) {
        List<Button> guiButton = GUIDepartment.getGUIDepartment( ).getButtons( );
        boolean[] array = new boolean[] {super.checkTextListTxtField(addListTxtField()), super.checkTextListChoiceBoxField(addListChcBox())} ;
        if (guiButton.get(0).getText().equals("Создать")) {
            if (super.getBoolValue(array)) {
                daoDepartment.addDepartment(getOverFieldDepartment());
                super.nullSetTxtField(addListTxtField());
                super.nullSetChcBox(addListChcBox());

            }

        }

        else {
            if (super.getBoolValue(array)) {
                Long id = GUIDepartment.getDepartmentRecord( ).getId( );
                daoDepartment.updateDepartments(id, getOverFieldDepartment());
                TableView<Department> tblView = GUIDepartment.getGUIDepartment( ).getTableDepartment( );
                ObservableList<Department> observableList = FXCollections.observableArrayList();
                List<Department> list = daoDepartment.listDepartments();
                observableList.addAll(list);
                tblView.setItems(observableList);
                GUIDepartment.getGUIDepartment().getStage().close();
            }
        }

    }

    @Override
    public List<TextField> addListTxtField() {
        List<TextField> list = new ArrayList<>(  );
        list.add(txtNameDepartment);
        return list;
    }

    @Override
    public List<ChoiceBox<String>> addListChcBox() {
        List<ChoiceBox<String>> list = new ArrayList<>();
        list.add(chbBuild);
        list.add(chbTypeHelp);
        list.add(chbTypeTask);
        return list;

    }

    @Override
    public List<DatePicker> addListDtpick() {
        return null;
    }

    public Department getOverFieldDepartment() {
        Department department = new Department();
        department.setNameDepartment(txtNameDepartment.getText());
        Map<Long, Building> choice = GUIDepartment.getMapChcBoxBuild();
        Building build = null;
        String nameCon = chbBuild.getValue();
        for(Map.Entry<Long, Building> map : choice.entrySet()) {
            Building tmpBuild = map.getValue();
            if (nameCon.equals(tmpBuild.getNameBuilding())) {
                build = tmpBuild;
            }
        }
        department.setBuilding(build);
        department.setTypeHelp(chbTypeHelp.getValue());
        department.setTypeTask(chbTypeTask.getValue());
        department.setRural(chkRural.isSelected());
        department.setFap(chkFAP.isSelected());
        department.setStatist(chkStat.isSelected());
        return department;
    }
}
