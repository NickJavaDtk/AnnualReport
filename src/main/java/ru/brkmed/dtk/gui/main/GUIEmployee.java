package ru.brkmed.dtk.gui.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import ru.brkmed.dtk.dao.mainClasses.entityes.AbstractEntity;
import ru.brkmed.dtk.dao.mainClasses.entityes.Connection;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoConnection;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoEmployee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoPositionFRMR;
import ru.brkmed.dtk.gui.model.ListNodes;

import javax.security.auth.callback.ChoiceCallback;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIEmployee extends AbstractGUIControler{

    private  TableView<Employee> tableEmployee;

    private ObservableList<Employee> obsEmployee;

    private static Employee employeeRecord;

    private static Map<Long, CurrentPositionFRMR> mapChcBoxCurrent;

    private static GUIEmployee GUIEmployees;

    private List<Button> buttons;

    private Stage stage;

    private final String load = "Загрузить";


    public GUIEmployee() {
    }

    public GUIEmployee(List<Button> buttons, TableView<Employee> tableEmployee, Stage stage) {
        this.tableEmployee = tableEmployee;
        this.buttons = buttons;
        this.stage = stage;
    }

    @Override
    public ObservableList<? extends AbstractEntity> getObservableList() {
        ObservableList<Employee> observableList = FXCollections.observableArrayList();
        observableList.sorted();
        List<Employee> employeeList =  new ControlerDaoEmployee().listEmployees();
        observableList.addAll(employeeList);
        return observableList;
    }

    @Override
    public void setValueTableView(TableView<? extends AbstractEntity> tableView) {
        tableEmployee = (TableView<Employee>) super.getTableView();
        TableColumn<Employee, Long> idEmployee = new TableColumn<>("ID Сотрудника");
        idEmployee.setPrefWidth(45.0);
        idEmployee.setCellValueFactory(new PropertyValueFactory<Employee, Long>("Id"));
        TableColumn<Employee, String> name = new TableColumn<>("ФИО сотрудника");
        name.setPrefWidth(275.0);
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("fullName"));
        TableColumn<Employee, String> current = new TableColumn<>("Должность");
        current.setPrefWidth(275.0);
        current.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> employeeCurrent) {
                return new ReadOnlyObjectWrapper<>( employeeCurrent.getValue().getCurrentPosition().getWorkPosition() );
            }
        });
        // nameBuilding.setCellValueFactory(new PropertyValueFactory<Connection, Building>("build.nameBuilding"));
        TableColumn<Employee, String> startDate = new TableColumn<>( "Начало сертификата" );
        startDate.setPrefWidth(175.0);
        startDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> employeeSDate) {
                String inputStart = String.valueOf(employeeSDate.getValue().getBeginningSignature());
                return new ReadOnlyObjectWrapper<>( getStringDate(inputStart));
            }
        });
        TableColumn<Employee, String> endDate = new TableColumn<>( "Окончание сертификата" );
        endDate.setPrefWidth(175.0);
        endDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> employeeEDate) {
                String inputEnd = String.valueOf(employeeEDate.getValue().getEndSignature());
                return new ReadOnlyObjectWrapper<>( getStringDate(inputEnd));
            }
        });
        TableColumn<Employee, String> typeEmployee = new TableColumn<>("Категория персонала");
        typeEmployee.setPrefWidth(475.0);
        typeEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("typePosition"));
        TableColumn<Employee, Boolean> mainCurrent = new TableColumn<>( "Основная должность");
        mainCurrent.setPrefWidth(275);
        //mainCurrent.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("mainPosition"));
        mainCurrent.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, Boolean>, ObservableValue<Boolean>>( ) {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Employee, Boolean> employeeBoolean) {
                Employee employee = employeeBoolean.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty( employee.getMainPosition() );
                return booleanProperty;
            }
        });
        mainCurrent.setCellFactory(new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>( ) {
            @Override
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> employeeTableColumn) {
                CheckBoxTableCell<Employee, Boolean> cell = new CheckBoxTableCell<>(  );
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });


        TableColumn<Employee, String> inn = new TableColumn<>("ИНН");
        inn.setPrefWidth(275.0);
        inn.setCellValueFactory(new PropertyValueFactory<Employee, String>("inn"));
        TableColumn<Employee, String> snils = new TableColumn<>("СНИЛС");
        snils.setPrefWidth(275.0);
        snils.setCellValueFactory(new PropertyValueFactory<Employee, String>("snils"));
        TableColumn<Employee, String> fon = new TableColumn<>("Номер телефона");
        fon.setPrefWidth(275.0);
        fon.setCellValueFactory(new PropertyValueFactory<Employee, String>("fon"));
        tableEmployee.getColumns().addAll(idEmployee, name, current, startDate, endDate, typeEmployee, mainCurrent, inn, snils, fon);
        obsEmployee = (ObservableList<Employee>) getObservableList();
        TextField find = super.getTxtFieldFind();
        SortedList<Employee> sortedList = (SortedList<Employee>) findValueRecord(tableEmployee, obsEmployee, find);
        tableEmployee.setItems(sortedList);
        tableEmployee.getSelectionModel().select(0);

    }

    @Override
    public void fillValuesCreateWindow(Parent parent) {
        Employee employee = new Employee(  );
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        TextField txtInn = (TextField) listNodes.get(3);
        //getIntegerValue12Char(txtInn);
        getIntegerValue12Char(txtInn);
        TextField txtPhone = (TextField) listNodes.get(5);
        getIntegerValue11Char(txtPhone);
        TextField snils = (TextField) listNodes.get(17);
        getIntegerValue11Char(snils);
        ChoiceBox<String> chbCurrent = (ChoiceBox<String>) listNodes.get(7);
        ChoiceBox<String> chbPosition = (ChoiceBox<String>) listNodes.get(9);
        CheckBox chkMainPosition = (CheckBox) listNodes.get(21);
        chbCurrent.getItems().addAll(getObsCurrentEmployee());
        chbPosition.getItems().addAll(employee.getObsTypePosition());
        chkMainPosition.setSelected(employee.getMainPosition());

    }

    @Override
    public void fillValuesEditWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        TextField txtFio = (TextField) listNodes.get(0);
        TextField txtInn = (TextField) listNodes.get(3);
        TextField txtPhone = (TextField) listNodes.get(5);
        ChoiceBox<String> curPosition = (ChoiceBox<String>) listNodes.get(7);
        ChoiceBox<String> typePosition = (ChoiceBox<String>) listNodes.get(9);
        DatePicker start = (DatePicker) listNodes.get(13);
        DatePicker end = (DatePicker) listNodes.get(15);
        TextField snils = (TextField) listNodes.get(17);
        CheckBox mainCur = (CheckBox) listNodes.get(21);
        employeeRecord = (Employee) super.getRecordTableView();
        txtFio.setText(employeeRecord.getFullName( ));
        getIntegerValue12Char(txtInn);
        txtInn.setText(employeeRecord.getInn( ));
        txtPhone.setText(employeeRecord.getFon( ));
        getIntegerValue11Char(txtPhone);
        curPosition.setValue(employeeRecord.getCurrentPosition().getWorkPosition());
        curPosition.getItems().addAll(getObsCurrentEmployee());
        typePosition.setValue(employeeRecord.getTypePosition( ));
        typePosition.getItems().addAll(employeeRecord.getObsTypePosition());
        Instant instantStart = employeeRecord.getBeginningSignature().toInstant();
        start.setValue(instantStart.atZone(ZoneId.systemDefault( )).toLocalDate( ));
        start.getEditor().setDisable(true);
        start.getEditor().setOpacity(1);
        Instant instantEnd = employeeRecord.getEndSignature().toInstant();
        end.setValue(instantEnd.atZone(ZoneId.systemDefault()).toLocalDate());
        end.getEditor().setDisable(true);
        end.getEditor().setOpacity(1);
        getIntegerValue11Char(snils);
        snils.setText(employeeRecord.getSnils( ));
        mainCur.setSelected(employeeRecord.getMainPosition( ));



    }

    @Override
    public void addEventStage() {
        super.getStage( ).addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
                tableEmployee.setItems((ObservableList<Employee>) getObservableList( ));
            }
        });

    }

    @Override
    public AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage) {
        buttonList.add(button);
        tableEmployee.setItems((ObservableList<Employee>) getObservableList( ));
        GUIEmployees = new GUIEmployee( buttonList, tableEmployee, stage );
        return GUIEmployees;

    }

    @Override
    public SortedList<? extends AbstractEntity> findValueRecord(TableView<? extends AbstractEntity> tableView, ObservableList<? extends AbstractEntity> observableList, TextField txtFieldFind) {
        FilteredList<Employee> filterData = new FilteredList<>((ObservableList<Employee>) observableList, b -> true);
        txtFieldFind.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(employee.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getCurrentPosition().getWorkPosition().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if ((getStringDate(String.valueOf(employee.getBeginningSignature())).toLowerCase().indexOf(lowerCaseFilter) != -1)) {
                    return true;
                } else if ((getStringDate(String.valueOf(employee.getEndSignature())).toLowerCase().indexOf(lowerCaseFilter) != -1)) {
                    return true;
                } else if (employee.getTypePosition().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getInn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getSnils().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getFon().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });


        });
        SortedList<Employee> sortedData = new SortedList<>(filterData);
        TableView<Employee> tableConnection = (TableView<Employee>) tableView;
        sortedData.comparatorProperty().bind(tableConnection.comparatorProperty());
        return sortedData;
    }

    @Override
    public void resetFindTextField(ContextMenu contextMenu) {
        MenuItem menuItem = contextMenu.getItems( ).get(0);
        menuItem.setOnAction(actionEvent -> {
            ObservableList<Employee> observableList = FXCollections.observableArrayList();
            List<Employee> listEmployees = new ControlerDaoEmployee().listEmployees();
            observableList.addAll(listEmployees);
            SortedList<Employee> sortedListField = (SortedList<Employee>) findValueRecord(tableEmployee, observableList, getTxtFieldFind());
            tableEmployee.setItems(sortedListField);
        });
    }

    @Override
    public void deleteRecord() {
        Employee employee = (Employee) super.getRecordTableView();
        ControlerDaoEmployee controlerDaoEmployee = new ControlerDaoEmployee();
        controlerDaoEmployee.deleteEmployee(employee.getId());
        tableEmployee.setItems((ObservableList<Employee>) getObservableList( ));

    }



    public void alternativeTab(TabPane tabPane) {
        super.basicWindowTab(tabPane, "Сотрудники");
        Button create = super.getCreateButton();
        super.getNewDialogWindow(create,
                "/EmployeeCreateEditRecord.fxml", "Добавить сотрудика");
        Button edit = super.getEditButton();
        super.getNewDialogWindow(edit,
                "/EmployeeCreateEditRecord.fxml", "Изменить сотрудника");
        // setButton(super.getPresButton());
        Button delete = super.getDeleteButton();
        getNewDialogWindowDelete(delete);
        Button load = super.getLoadButton();
        super.getNewDialogWindow(load, "/LoadEmployee.fxml", "Загрузить сотрудников");


    }

    public ObservableList<String> getObsCurrentEmployee() {
        ObservableList<String> obsString = FXCollections.observableArrayList();
        ControlerDaoPositionFRMR daoPositionFRMR = new ControlerDaoPositionFRMR();
        List<CurrentPositionFRMR> listPositionFRMF = daoPositionFRMR.listCurrentIsVisible();
        mapChcBoxCurrent = new HashMap<>();
        for(CurrentPositionFRMR listCurrent : listPositionFRMF) {
            mapChcBoxCurrent.put(listCurrent.getId(), listCurrent);
            obsString.add(listCurrent.getWorkPosition());
        }
        return obsString;
    }

    public String getLoad() {
        return load;
    }

    public static Map<Long, CurrentPositionFRMR> getMapChcBoxCurrent() {
        return mapChcBoxCurrent;
    }

    public static GUIEmployee getGUIEmployees() {
        return GUIEmployees;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public static Employee getEmployeeRecord() {
        return employeeRecord;
    }

    public TableView<Employee> getTableEmployee() {
        return tableEmployee;
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
