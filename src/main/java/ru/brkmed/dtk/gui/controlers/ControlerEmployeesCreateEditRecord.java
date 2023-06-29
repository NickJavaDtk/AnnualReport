package ru.brkmed.dtk.gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Connection;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoEmployee;
import ru.brkmed.dtk.gui.main.AbstractGUIControler;
import ru.brkmed.dtk.gui.main.GUIConnections;
import ru.brkmed.dtk.gui.main.GUIEmployee;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ControlerEmployeesCreateEditRecord extends AbstractChildWindow implements Initializable {

    @FXML
    private Button btnSaveEdit;

    @FXML
    private ChoiceBox<String> chcBoxCurPosition;

    @FXML
    private ChoiceBox<String> chcBoxTypePosition;

    @FXML
    private CheckBox chkMainCurrent;

    @FXML
    private DatePicker dpEndCert;

    @FXML
    private DatePicker dpStartCert;

    @FXML
    private TextField txtFieldFIO;

    @FXML
    private TextField txtFieldInn;

    @FXML
    private TextField txtFieldPhone;

    @FXML
    private TextField txtFieldSNILS;


    ControlerDaoEmployee daoEmployee = new ControlerDaoEmployee();

    private static final String startCert = "01.01.2022";
    private static final String endCert = "01.01.2023";



    @FXML
    void saveEditEmployee(ActionEvent event) {
        List<Button> guiButton = GUIEmployee.getGUIEmployees( ).getButtons( );
        boolean[] array = new boolean[] {super.checkTextListTxtField(addListTxtField()),
                super.checkTextListChoiceBoxField(addListChcBox())};
        if(guiButton.get(0).getText().equals("Создать")) {
            if (super.getBoolValue(array)) {
                daoEmployee.addEmployee(getOverFieldEmployee( ));
                super.nullSetTxtField(addListTxtField( ));
                super.nullSetChcBox(addListChcBox( ));
                super.nullSetDtPicker(addListDtpick( ));
                chkMainCurrent.setSelected(false);
            }
        }
        else {
                if (super.getBoolValue(array)) {
                    Long id = GUIEmployee.getEmployeeRecord( ).getId( );
                    daoEmployee.updateEmployees(id, getOverFieldEmployee());
                    TableView<Employee> tblEmplyee = GUIEmployee.getGUIEmployees().getTableEmployee();
                    List<Employee> employeeList = daoEmployee.listEmployees();
                    ObservableList<Employee> observableList = FXCollections.observableArrayList();
                    observableList.addAll(employeeList);
                    tblEmplyee.setItems(observableList);
                    GUIEmployee.getGUIEmployees().getStage().close();
                }
            }


    }

    public Employee getOverFieldEmployee() {
        Employee employee = new Employee();
        employee.setFullName(txtFieldFIO.getText());
        Map<Long, CurrentPositionFRMR> choice = GUIEmployee.getMapChcBoxCurrent();
        CurrentPositionFRMR currentPositionFRMR = null;
        String namePosition = chcBoxCurPosition.getValue();
        for(Map.Entry<Long, CurrentPositionFRMR> map : choice.entrySet()) {
            CurrentPositionFRMR positionFRMR = map.getValue();
            if (namePosition.equals(positionFRMR.getWorkPosition())) {
                currentPositionFRMR = positionFRMR;
            }
        }
        employee.setCurrentPosition(currentPositionFRMR);
        if (dpStartCert.getValue() == null && dpEndCert.getValue() == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd.MM.yyyy" );
            try {
                Date dateStart = simpleDateFormat.parse(startCert);
                Date dateEnd = simpleDateFormat.parse(endCert);
                employee.setBeginningSignature(dateStart);
                employee.setEndSignature(dateEnd);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            Date dateStart = Date.from(dpStartCert.getValue( ).atStartOfDay(ZoneId.systemDefault( )).toInstant( ));
            employee.setBeginningSignature(dateStart);
            Date dateEnd = Date.from(dpEndCert.getValue( ).atStartOfDay(ZoneId.systemDefault( )).toInstant( ));
            employee.setEndSignature(dateEnd);
        }
        employee.setTypePosition(chcBoxTypePosition.getValue());
        employee.setMainPosition(chkMainCurrent.isSelected());
        employee.setInn(txtFieldInn.getText());
        employee.setSnils(txtFieldSNILS.getText());
        employee.setFon(txtFieldPhone.getText());
        return employee;
    }




    @Override
    public List<TextField> addListTxtField() {
        List<TextField> list = new ArrayList<>();
        list.add(txtFieldFIO);
        return  list;

    }

    @Override
    public List<ChoiceBox<String>> addListChcBox() {
        List<ChoiceBox<String>> choiceBoxList = new ArrayList<>(  );
        choiceBoxList.add(chcBoxCurPosition);
        choiceBoxList.add(chcBoxTypePosition);
       return choiceBoxList;
    }

    @Override
    public List<DatePicker> addListDtpick() {
        List<DatePicker> datePickers = new ArrayList<>(  );
        datePickers.add(dpStartCert);
        datePickers.add(dpEndCert);
        return datePickers;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
