package ru.brkmed.dtk.gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Department;
import ru.brkmed.dtk.dao.mainClasses.entityes.Device;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;
import ru.brkmed.dtk.dao.mainClasses.entityes.Unit;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDepartment;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDevice;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoUnit;
import ru.brkmed.dtk.gui.main.GUIDevice;
import ru.brkmed.dtk.gui.main.MainGUIControler;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

public class ControlerUnitCreateEditRecord extends AbstractChildWindow implements Initializable {
    @FXML
    public TableView tblViewEmployee;
    @FXML
    public TableView tblViewAddDevice;
    @FXML
    public TableView tblViewDevice;
    @FXML
    public TableView tblViewAddEmp;
    @FXML
    private Button btnAddDevice;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnDelEmployee;

    @FXML
    private Button btnDeleteDevice;

    @FXML
    private Button btnSave;

    @FXML
    private ChoiceBox<String> chbDepartament;

    @FXML
    private CheckBox chcHelp;

    @FXML
    private CheckBox chcHelpHosp;

    @FXML
    private CheckBox chcHelpPolic;

    @FXML
    private CheckBox chcTask;

    @FXML
    private ListView<?> lstView;

    @FXML
    private ListView<?> lstViewEployee;

    @FXML
    private TableView<?> tblView;

    @FXML
    private TextArea txtAreaEmployee;

    @FXML
    private TextField txtFieldName;

    @FXML
    void btnAddDev(ActionEvent event) {

    }

    @FXML
    void btnAddEmp(ActionEvent event) {

    }

    @FXML
    void btnDelDev(ActionEvent event) {

    }

    @FXML
    void btnDelEmp(ActionEvent event) {

    }
    private ControlerDaoUnit daoUnit = new ControlerDaoUnit();

    @FXML
    void btnSaveUnit(ActionEvent event) {
//
        Button currentButton = MainGUIControler.getButtonList().get(0);
        boolean[] array = new boolean[] {super.checkTextListTxtField(addListTxtField()), checkTextListChoiceBoxField(addListChcBox())} ;
        if (currentButton.getText().equals("Создать")) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Твываыв протве");
//        alert.setHeaderText(null);
//        alert.setContentText("dhgdfhfsdhg");
//        alert.showAndWait();
            if (super.getBoolValue(array)) {
                daoUnit.addUnits(getOverFieldUnit( ));
                txtFieldName.setStyle(null);
                chbDepartament.setStyle(null);
                super.nullSetTxtField(addListTxtField( ));
                chbDepartament.getItems( ).addAll(new MainGUIControler( ).getDepartmentString( ));
                nullSelectedCheckBox(addCheckBox( ));
                tblViewAddEmp.setItems(null);
                tblViewAddDevice.setItems(null);

            }
        }
        else {
            if (super.getBoolValue(array)) {
                Long id = MainGUIControler.getRecordUnit().getId();
                daoUnit.updateUnits(id, getOverFieldUnit());
                TableView<Unit> tblView = MainGUIControler.getTableUnit();
                ObservableList<Unit> observableList = FXCollections.observableArrayList();
                List<Unit> list = daoUnit.listUnits();
                observableList.addAll(list);
                tblView.setItems(observableList);

                MainGUIControler.getParentStage().close();
            }
        }


    }


    @Override
    public List<TextField> addListTxtField() {
        List<TextField> list = new ArrayList<>(  );
        list.add(txtFieldName);
        return list;
    }

    @Override
    public List<ChoiceBox<String>> addListChcBox() {
        List<ChoiceBox<String>> list = new ArrayList<>();
        list.add(chbDepartament);
        return list;
    }

    @Override
    public List<DatePicker> addListDtpick() {
        return null;
    }

    public List<CheckBox> addCheckBox() {
        List<CheckBox> list = new ArrayList<>(  );
        list.add(chcHelpPolic);
        list.add(chcHelpHosp);
        list.add(chcHelp);
        list.add(chcTask);
        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Unit getOverFieldUnit() {
        Unit unit = new Unit();
        ControlerDaoDevice daoDevice = new ControlerDaoDevice();
       // Collection<Employee> employeeList = new ArrayList<>();
       // Collection<Device> deviceList = new ArrayList<>();
        unit.setNameUnit(txtFieldName.getText());
        Optional<Department> department = new ControlerDaoDepartment().listDepartments().stream()
                .filter(dep -> dep.getNameDepartment().equals(chbDepartament.getValue())).findAny();
        unit.setDepartment(department.get());
        unit.setHelpPolic(chcHelpPolic.isSelected());
        unit.setHelpHosp(chcHelpHosp.isSelected());
        unit.setHelp(chcHelp.isSelected());
        unit.setTask(chcTask.isSelected());
        ObservableList<Employee> obsEmp = tblViewAddEmp.getItems();
       // ObservableList<Employee> obsEmployee = setDateAddEmployee(obsEmp);

        unit.getEmployees().addAll(obsEmp);
       // unit.setEmployees(employeeList);
        ObservableList<Device> obsDev = tblViewAddDevice.getItems();
//
        unit.setDevices(obsDev);
        return unit;

    }

    public void nullSelectedCheckBox(List<CheckBox> checkBox) {
        checkBox.forEach(check -> check.setSelected(false));
    }

    public ObservableList<Employee> setDateAddEmployee(ObservableList<Employee> list) {
        List<Employee> employeeList = new ArrayList<>();
        for(Employee employee : list) {
            Date start = Date.from(employee.getBeginningSignature().toInstant( ));
            Date end = Date.from(employee.getEndSignature().toInstant());
            employee.setBeginningSignature(start);
            employee.setEndSignature(end);
        }
        ObservableList<Employee> observableList = FXCollections.observableArrayList( list );
        return observableList;


    }
}
