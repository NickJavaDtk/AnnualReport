package ru.brkmed.dtk.gui.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.w3c.dom.ls.LSException;
import ru.brkmed.dtk.dao.mainClasses.entityes.*;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDepartment;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDevice;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoEmployee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoUnit;
import ru.brkmed.dtk.gui.main.*;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainGUIControler implements Initializable {
    @FXML
    private Button createCabinet;

    @FXML
    private Button editCabinet;

    @FXML
    private Button loadCabinet;

    @FXML
    private Label lblCountRecord;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private MenuItem menuItemBuilding;

    @FXML
    private MenuItem menuItemConnections;

    @FXML
    private MenuItem menuItemEmploee;

    @FXML
    private MenuItem menuItemNCI;

    @FXML
    private MenuItem menuItemPM;

    @FXML
    private MenuItem menuItemPrinter;

    @FXML
    private MenuItem menuItemUnit;

    @FXML
    private Tab tabCabinet;


    @FXML
    private TableView<Unit> tblViewUnit;

    private static TableView<Unit> tableUnit;

    private ObservableList<Unit> obsUnit;

    private static List<Button> buttonList;

   private Parent parentButton;

    private ControlerDaoDepartment daoDepartment = new ControlerDaoDepartment();
    private ControlerDaoEmployee daoEmployee = new ControlerDaoEmployee();
    private ControlerDaoDevice daoDevice = new ControlerDaoDevice();

    private GUIDevice guiDevice = new GUIDevice(  );

    private GUIEmployee guiEmployee = new GUIEmployee(  );

    private static final String titleInformation = "Предупреждение";
    private static final String textInformation = "Выберите запись для добавления";
    private static final String textInformation1 = "Нельзя добавить дубликат";
    private static final String textInformationDelete = "Выберите запись для удаления";
    private static final String titleError = "Ошибка";

    private static Unit recordUnit;

    private static Stage parentStage;









    @FXML
    void createBuilding(ActionEvent event) {
        GUIBuilding guiBuilding = new GUIBuilding();
        //guiBuilding.createAddTab(mainTabPane);
       // ControlerGUIBuilding gdd = new AbstractGUIControler(  )
        guiBuilding.alternativeTab(mainTabPane);


    }
    @FXML
    public void createConnections(ActionEvent actionEvent) {
        GUIConnections guiConnections = new GUIConnections();
       // guiConnections.createAddTab(mainTabPane);
        guiConnections.alternativeTab(mainTabPane);
    }

    @FXML
    void createEmploee(ActionEvent event) {
        GUIEmployee guiEmployee = new GUIEmployee(  );
        guiEmployee.alternativeTab(mainTabPane);

    }

    @FXML
    void createNCI(ActionEvent event) {
//        ControlerLoadPositionFRMR controler = new ControlerLoadPositionFRMR();
//        controler.startLoadWindow();
        GUICurrentPositionFRMR current = new GUICurrentPositionFRMR();
        current.alternativeTab(mainTabPane);

    }

    @FXML
    void createEquip(ActionEvent event) {
        GUIDevice device = new GUIDevice();
        device.alternativeTab(mainTabPane);

    }

    @FXML
    void createPrinter(ActionEvent event) {

    }

    @FXML
    void createUnit(ActionEvent event) {
        GUIDepartment department = new GUIDepartment();
        department.alternativeTab(mainTabPane);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableUnit = setValueTableView(tblViewUnit);
        obsUnit = getObsUnit();
        tableUnit.getItems().addAll(obsUnit);
        lblCountRecord.setText(String.valueOf(tableUnit.getItems().size()));
    }

    @FXML
    void btnCreate(ActionEvent event) {
        buttonList = new ArrayList<>();
        buttonList.add(createCabinet);
        FXMLLoader loader = new FXMLLoader(  );
        loader.setLocation(getClass().getResource("/UnitCreateEditRecord.fxml"));
        try{
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage(  );
        stage.setTitle("Добавить кабинет");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        fillValuesCreateWindow(root);


        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
                tblViewUnit.setItems(getObsUnit());

            }
        });
        stage.showAndWait();

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnEdit(ActionEvent event) {
        buttonList = new ArrayList<>();
        buttonList.add(editCabinet);
        FXMLLoader loader = new FXMLLoader(  );
        loader.setLocation(getClass().getResource("/UnitCreateEditRecord.fxml"));
        try{
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        parentStage = new Stage(  );
        parentStage.setTitle("Изменить кабинет");
        parentStage.initModality(Modality.APPLICATION_MODAL);
        parentStage.setScene(new Scene(root));
        fillValuesEditWindow(root);


        parentStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
                tblViewUnit.setItems(getObsUnit());

            }
        });
        parentStage.showAndWait();
    }





    public TableView<Unit> setValueTableView(TableView<Unit> tableView) {
        TableColumn<Unit, Long> id = new TableColumn<>("Id Кабинета");
        id.setPrefWidth(75.0);
        id.setCellValueFactory(new PropertyValueFactory<Unit, Long>("Id"));
        TableColumn<Unit, String> nameUnit = new TableColumn<>("Кабинет");
        nameUnit.setPrefWidth(185.0);
        nameUnit.setCellValueFactory(new PropertyValueFactory<Unit, String>("nameUnit"));
        TableColumn<Unit, String> department = new TableColumn<>("Подразделение");
        department.setPrefWidth(185.0);
        department.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Unit, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Unit, String> unitString) {
                String nameDepartment = unitString.getValue().getDepartment().getNameDepartment();
                return new ReadOnlyObjectWrapper<>( nameDepartment );
            }
        });
        TableColumn<Unit, Boolean> helpPolic = new TableColumn<>( "Амбулаторная помощь" );
        helpPolic.setPrefWidth(135);
        helpPolic.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("helpPolic"));
        TableColumn<Unit, Boolean> helpHosp = new TableColumn<>( "Стационарная помощь" );
        helpHosp.setPrefWidth(135);
        helpHosp.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("helpHosp"));
        TableColumn<Unit, Boolean> help = new TableColumn<>( "Медицинская деятельность" );
        help.setPrefWidth(155);
        help.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("help"));
        TableColumn<Unit, Boolean> task = new TableColumn<>( "Хозяйственная деятельность" );
        task.setPrefWidth(155);
        task.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("task"));
        TableColumn<Unit, List<String>> employees = new TableColumn<>( "Сотрудники" );
        employees.setPrefWidth(185.0);
        employees.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Unit, List<String>>, ObservableValue<List<String>>>( ) {
            @Override
            public ObservableValue<List<String>> call(TableColumn.CellDataFeatures<Unit, List<String>> unitListCell) {
                //unitListCell.getValue().
                List<Employee> employeeList = (List<Employee>) unitListCell.getValue().getEmployees();
                if (employeeList.size() != 0) {
                    List<String> list = employeeList.stream( ).map(Employee::getFullName).collect(Collectors.toList( ));
                    return new ReadOnlyObjectWrapper<>(list);
                } else {
                    List<String> list = new ArrayList<>(  );
                    list.add("");
                    return new ReadOnlyObjectWrapper<>( list );
                }
            }
        });
        TableColumn<Unit, List<String>> devices = new TableColumn<>( "Оборудование" );
        devices.setPrefWidth(185.0);
        devices.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Unit, List<String>>, ObservableValue<List<String>>>( ) {
            @Override
            public ObservableValue<List<String>> call(TableColumn.CellDataFeatures<Unit, List<String>> unitListCell) {
                List<Device> deviceList = (List<Device>) unitListCell.getValue().getDevices();
                if (deviceList.size() != 0) {
                    List<String> list = deviceList.stream().map(Device::getBrand).collect(Collectors.toList());
                    return new ReadOnlyObjectWrapper<>( list );
                } else {
                    List<String> list = new ArrayList<>(  );
                    list.add("");
                    return new ReadOnlyObjectWrapper<>( list );
                }
            }
        });

        tableView.getColumns().addAll(id, nameUnit, department, helpPolic, helpHosp, help, task, employees, devices);

        return tableView;

    }

    public ObservableList<Unit> getObsUnit() {
        ObservableList<Unit> observableList = FXCollections.observableArrayList();
        List<Unit> unitList = new ControlerDaoUnit().listUnits();
        observableList.addAll(unitList);
        return observableList;
    }



    public TabPane getMainTabPane() {
        return mainTabPane;
    }


    public List<String> getListDepartment(List<Department> department) {
       // Stream<String> stream = department.stream().map(Department :: getNameDepartment);
        List<String> list = department.stream().map(Department :: getNameDepartment).collect(Collectors.toList());
        return list;
    }

    public static List<Button> getButtonList() {
        return buttonList;
    }

    public static TableView<Unit> getTableUnit() {
        return tableUnit;
    }

    public Parent getParentButton() {
        return parentButton;
    }

    public void fillValuesCreateWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);

        TabPane tabPane = (TabPane) listNodes.get(0);
        Tab unit = tabPane.getTabs( ).get(0 );
        List<Node> nodeUnit = getAllNodes(unit);
        ChoiceBox<String> department = (ChoiceBox<String>) nodeUnit.get(3);
        CheckBox helpPolic = (CheckBox) nodeUnit.get(4);
        CheckBox helpHosp = (CheckBox) nodeUnit.get(5);
        CheckBox help = (CheckBox) nodeUnit.get(6);
        CheckBox task = (CheckBox) nodeUnit.get(7);

        setDepartmentChoiceBox(department, helpPolic, helpHosp, help, task);


        Tab employees = tabPane.getTabs( ).get(1 );
        List<Node> nodeEmployee = getAllNodes(employees);
        Button addEmployee = (Button) nodeEmployee.get(0);
        TableView<Employee> tableViewEmployee = (TableView<Employee>) nodeEmployee.get(2);
        Button delEmployee = (Button) nodeEmployee.get(1);
        TableView<Employee> tableViewAddEmp  = (TableView<Employee>) nodeEmployee.get(3);

        getColumnTableViewEmployee(tableViewEmployee);
        getColumnTableViewEmployee(tableViewAddEmp);

        List<Employee> employeeList = daoEmployee.listEmployees();
        ObservableList<Employee> obsEmployee = FXCollections.observableArrayList();
        obsEmployee.addAll(employeeList);
        tableViewEmployee.getItems().addAll(obsEmployee);

        setListenerButtonEmployees(addEmployee, delEmployee, tableViewEmployee, tableViewAddEmp);


        Tab devices = tabPane.getTabs( ).get(2 );
        List<Node> nodeDevice = getAllNodes(devices);
        Button addDevice = (Button) nodeDevice.get(0);
        Button delDevice = (Button) nodeDevice.get(1);
        Button saveUnit = (Button) nodeDevice.get(2);
        TableView<Device> tableDevice = (TableView<Device>) nodeDevice.get(4);
        TableView<Device> tableAddDevice = (TableView<Device>) nodeDevice.get(3);

        getColumnTableViewDevice(tableDevice);
        getColumnTableViewDevice(tableAddDevice);

        List<Device> deviceList = daoDevice.listDevicesUnit();
        ObservableList<Device> deviceObservableList = FXCollections.observableArrayList( deviceList );
        tableDevice.getItems().addAll(deviceObservableList);


        setListenerButtonDevices(addDevice, delDevice, tableDevice, tableAddDevice);


    }

    public void fillValuesEditWindow(Parent parent) {
         recordUnit = getRecordTableView();

        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);

        TabPane tabPane = (TabPane) listNodes.get(0);
        Tab unit = tabPane.getTabs( ).get(0 );
        List<Node> nodeUnit = getAllNodes(unit);
        TextField nameUnit = (TextField) nodeUnit.get(1);
        ChoiceBox<String> department = (ChoiceBox<String>) nodeUnit.get(3);
        CheckBox helpPolic = (CheckBox) nodeUnit.get(4);
        CheckBox helpHosp = (CheckBox) nodeUnit.get(5);
        CheckBox help = (CheckBox) nodeUnit.get(6);
        CheckBox task = (CheckBox) nodeUnit.get(7);

        setDepartmentChoiceBox(department, helpPolic, helpHosp, help, task);


        nameUnit.setText(recordUnit.getNameUnit());
        department.setValue(recordUnit.getDepartment().getNameDepartment());
        helpPolic.setSelected(recordUnit.isHelpPolic( ));
        helpHosp.setSelected(recordUnit.isHelpHosp( ));
        help.setSelected(recordUnit.isHelp( ));
        task.setSelected(recordUnit.isTask( ));


        Tab employees = tabPane.getTabs( ).get(1 );
        List<Node> nodeEmployee = getAllNodes(employees);
        Button addEmployee = (Button) nodeEmployee.get(0);
        TableView<Employee> tableViewEmployee = (TableView<Employee>) nodeEmployee.get(2);
        Button delEmployee = (Button) nodeEmployee.get(1);
        TableView<Employee> tableViewAddEmp  = (TableView<Employee>) nodeEmployee.get(3);

        getColumnTableViewEmployee(tableViewEmployee);
        getColumnTableViewEmployee(tableViewAddEmp);

        List<Employee> employeeList = daoEmployee.listEmployees();
        ObservableList<Employee> obsEmployee = FXCollections.observableArrayList();
        obsEmployee.addAll(employeeList);
        tableViewEmployee.getItems().addAll(obsEmployee);
        ObservableList<Employee> obsEditEmployee = FXCollections.observableArrayList( recordUnit.getEmployees() );
        tableViewAddEmp.setItems(obsEditEmployee);
        setListenerButtonEmployees(addEmployee, delEmployee, tableViewEmployee, tableViewAddEmp);




        Tab devices = tabPane.getTabs( ).get(2 );
        List<Node> nodeDevice = getAllNodes(devices);
        Button addDevice = (Button) nodeDevice.get(0);
        Button delDevice = (Button) nodeDevice.get(1);
        Button saveUnit = (Button) nodeDevice.get(2);
        TableView<Device> tableDevice = (TableView<Device>) nodeDevice.get(4);
        TableView<Device> tableAddDevice = (TableView<Device>) nodeDevice.get(3);

        getColumnTableViewDevice(tableDevice);
        getColumnTableViewDevice(tableAddDevice);

        List<Device> deviceList = daoDevice.listDevicesUnit();
        ObservableList<Device> deviceObservableList = FXCollections.observableArrayList( deviceList );
        tableDevice.getItems().addAll(deviceObservableList);
        ObservableList<Device> obsEditDevice = FXCollections.observableArrayList( recordUnit.getDevices() );
        tableAddDevice.setItems(obsEditDevice);
        setListenerButtonDevices(addDevice, delDevice, tableDevice, tableAddDevice);



    }

    public ArrayList<Node> getAllNodes(Tab tab) {
        ArrayList<Node> nodes = new ArrayList<>(  );
        AnchorPane anchorPane = (AnchorPane) tab.getContent();
        addAllDescendents(anchorPane.getChildren(), nodes);
        return nodes;
    }

    private static void addAllDescendents(ObservableList<Node> nodeObservableList , ArrayList<Node> nodes) {
        for (Node node : nodeObservableList) {
            nodes.add(node);
//            if (node instanceof Parent)
//                addAllDescendents((Parent)node, nodes);
        }
    }

    public ObservableList<String> getDepartmentString() {
        List<Department> departments = daoDepartment.listDepartments();
        List<String> list = departments.stream().map(Department::getNameDepartment).collect(Collectors.toList());
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        return observableList;
    }

    public void getColumnTableViewEmployee(TableView<Employee> tableEmployee) {
        TableColumn<Employee, Long> idEmployee = new TableColumn<>("ID Сотрудника");
        idEmployee.setPrefWidth(45.0);
        idEmployee.setCellValueFactory(new PropertyValueFactory<Employee, Long>("Id"));
        TableColumn<Employee, String> name = new TableColumn<>("ФИО сотрудника");
        name.setPrefWidth(235.0);
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("fullName"));
        TableColumn<Employee, String> current = new TableColumn<>("Должность");
        current.setPrefWidth(235.0);
        current.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> employeeCurrent) {
                return new ReadOnlyObjectWrapper<>( employeeCurrent.getValue().getCurrentPosition().getWorkPosition() );
            }
        });
        // nameBuilding.setCellValueFactory(new PropertyValueFactory<Connection, Building>("build.nameBuilding"));
        TableColumn<Employee, String> startDate = new TableColumn<>( "Начало сертификата" );
        startDate.setPrefWidth(145.0);
        startDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> employeeSDate) {
                String inputStart = String.valueOf(employeeSDate.getValue().getBeginningSignature());
                return new ReadOnlyObjectWrapper<>( getStringDate(inputStart));
            }
        });
        TableColumn<Employee, String> endDate = new TableColumn<>( "Окончание сертификата" );
        endDate.setPrefWidth(145.0);
        endDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> employeeEDate) {
                String inputEnd = String.valueOf(employeeEDate.getValue().getEndSignature());
                return new ReadOnlyObjectWrapper<>( getStringDate(inputEnd));
            }
        });
        TableColumn<Employee, String> typeEmployee = new TableColumn<>("Категория персонала");
        typeEmployee.setPrefWidth(275.0);
        typeEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("typePosition"));
        TableColumn<Employee, Boolean> mainCurrent = new TableColumn<>( "Основная должность");
        mainCurrent.setPrefWidth(175);
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
        inn.setPrefWidth(175.0);
        inn.setCellValueFactory(new PropertyValueFactory<Employee, String>("inn"));
        TableColumn<Employee, String> snils = new TableColumn<>("СНИЛС");
        snils.setPrefWidth(175.0);
        snils.setCellValueFactory(new PropertyValueFactory<Employee, String>("snils"));
        TableColumn<Employee, String> fon = new TableColumn<>("Номер телефона");
        fon.setPrefWidth(175.0);
        fon.setCellValueFactory(new PropertyValueFactory<Employee, String>("fon"));
        tableEmployee.getColumns().addAll(idEmployee, name, current, startDate, endDate, typeEmployee, mainCurrent, inn, snils, fon);
    }

    public void getColumnTableViewDevice(TableView<Device> deviceTableView) {
        TableColumn<Device, Long> idDevice = new TableColumn<>("ID оборудования");
        idDevice.setPrefWidth(75.0);
        idDevice.setCellValueFactory(new PropertyValueFactory<Device, Long>("Id"));
        TableColumn<Device, String> typeDevice = new TableColumn<>( "Тип устройства" );
        typeDevice.setPrefWidth(275.0);
        typeDevice.setCellValueFactory(new PropertyValueFactory<Device, String>("typeDevice"));
        TableColumn<Device, String> number = new TableColumn<>( "Инвентарный номер" );
        number.setPrefWidth(275.0);
        number.setCellValueFactory(new PropertyValueFactory<Device, String>("number"));
        TableColumn<Device, String> brand = new TableColumn<>( "Марка" );
        brand.setPrefWidth(375.0);
        brand.setCellValueFactory(new PropertyValueFactory<Device, String>("brand"));
        TableColumn<Device, String> typeOC = new TableColumn<>( "Операционная система" );
        typeOC.setPrefWidth(205.0);
        typeOC.setCellValueFactory(new PropertyValueFactory<Device, String>("typeOC"));
        TableColumn<Device, Integer> term = new TableColumn<>( "Срок эксплуатации" );
        term.setPrefWidth(75.0);
        term.setCellValueFactory(new PropertyValueFactory<Device, Integer>("term"));

        TableColumn<Device, Boolean> connect = new TableColumn<>( "Подключено к ЗСПД");
        connect.setPrefWidth(275);
        connect.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>( ) {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Device, Boolean> deviceBoolean) {
                Device device = deviceBoolean.getValue();
//                String boolValue = String.valueOf(device.isConnect());
//                if (boolValue.equals("null")) {
//                    SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(false );
//                    return booleanProperty;
//                } else {
                    SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(device.isConnect( ));
                    return booleanProperty;
              //  }
            }
        });
        connect.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>( ) {
            @Override
            public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> deviceTableColumn) {
                CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<>(  );
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });


        deviceTableView.getColumns().addAll(idDevice, typeDevice, number, brand, typeOC, term, connect);

    }



    public String getStringDate(String input) {
        SimpleDateFormat parserDao = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = null;
        try {
            boolean b = Pattern.matches("\\d{4}-\\d{2}-\\d{2}", input);
            if (!b) {
                Date date = parserDao.parse(input);
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                formattedDate = formatter.format(date);
            } else {
                Date date = parser.parse(input);
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                formattedDate = formatter.format(date);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public  Unit getRecordTableView() {
        Unit unit = null;
        try {
            TablePosition<Unit, Long> tablePosition = tableUnit.getSelectionModel( ).getSelectedCells( ).get(0);
            int rowID = tablePosition.getRow( );
            unit = tableUnit.getItems( ).get(rowID);
            return unit;
        } catch (IndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Для редактирования необходимо выделить запись");
            alert.showAndWait();
        }
        return unit;
    }

    public void setDepartmentChoiceBox(ChoiceBox<String > department, CheckBox helpPolic, CheckBox helpHosp, CheckBox help, CheckBox task ) {
        department.getItems( ).addAll(getDepartmentString( ));
        department.getSelectionModel( ).selectedIndexProperty( ).addListener(new ChangeListener<Number>( ) {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                ObservableList<String> obs = getDepartmentString( );
                String value = obs.get(newValue.intValue( ));
                List<Department> departmentList = daoDepartment.listDepartments( );
                Optional<Department> optional = departmentList.stream( ).filter(d -> value.equals(d.getNameDepartment( ))).findAny( );
                boolean typeHelpBool = optional.get( ).getTypeHelp( ).equals("Амбулаторно-поликлиническая");
                boolean typeHospBool = optional.get( ).getTypeHelp( ).equals("Стационарная");
                boolean helpBool = optional.get( ).getTypeTask( ).equals("Автоматизация лечебного процесса");
                boolean taskBool = optional.get( ).getTypeTask( ).equals("Административно-хозяйственная деятельность");

                helpPolic.setSelected(typeHelpBool);
                helpHosp.setSelected(typeHospBool);
                help.setSelected(helpBool);
                task.setSelected(taskBool);


            }
        });
    }

    public void setListenerButtonEmployees(Button addEmployee, Button delEmployee, TableView<Employee> tableViewEmployee,
                                         TableView<Employee> tableViewAddEmp) {
        List<Employee> listEmployee = new ArrayList<>(  );
        addEmployee.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();
                    if (employee == null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(titleInformation);
                        alert.setHeaderText(null);
                        alert.setContentText(textInformation);
                        alert.showAndWait();
                    } else {

                        if (!listEmployee.contains(employee) && !tableViewAddEmp.getItems().contains(employee)) {
                            if (tableViewAddEmp.getItems().size() != 0 ) {
                                listEmployee.clear();
                                listEmployee.addAll(tableViewAddEmp.getItems());
                                listEmployee.add(employee);
                                ObservableList<Employee> observableList = FXCollections.observableArrayList( );
                                observableList.addAll(listEmployee);
                                tableViewAddEmp.setItems(observableList);
                            } else {
                                listEmployee.add(employee);
                                ObservableList<Employee> observableList = FXCollections.observableArrayList( );
                                observableList.addAll(listEmployee);
                                tableViewAddEmp.setItems(observableList);
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(titleInformation);
                            alert.setHeaderText(null);
                            alert.setContentText(textInformation1);
                            alert.showAndWait();
                        }


                    }

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(titleError);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }


            }
        });
        delEmployee.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Employee employee = tableViewAddEmp.getSelectionModel().getSelectedItem();
                    if (employee == null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(titleInformation);
                        alert.setHeaderText(null);
                        alert.setContentText(textInformationDelete);
                        alert.showAndWait();
                    } else {
                        ObservableList<Employee> observableList = tableViewAddEmp.getItems();
                        List<Employee> obs = observableList.stream( ).filter(emp -> emp.getId() != employee.getId()).collect(Collectors.toList());
                        ObservableList<Employee> obsEmp = FXCollections.observableArrayList( obs );
                        tableViewAddEmp.setItems(obsEmp);
                        listEmployee.clear();

                    }

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(titleError);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }
        });
    }

    public void setListenerButtonDevices(Button addDevice, Button delDevice, TableView<Device> tableDevice,
                                           TableView<Device> tableAddDevice) {
        List<Device> listDevice = new ArrayList<>(  );
        addDevice.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Device device = tableDevice.getSelectionModel().getSelectedItem();
                    if (device == null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(titleInformation);
                        alert.setHeaderText(null);
                        alert.setContentText(textInformation);
                        alert.showAndWait();
                    } else {
                        if (!listDevice.contains(device) && !tableAddDevice.getItems().contains(device)) {
                            if (tableDevice.getItems( ).size( ) != 0) {
                                listDevice.clear();
                                listDevice.addAll(tableAddDevice.getItems( ));
                                listDevice.add(device);
                                ObservableList<Device> observableList = FXCollections.observableArrayList( );
                                observableList.addAll(listDevice);
                                tableAddDevice.setItems(observableList);
                            } else {
                                listDevice.add(device);
                                ObservableList<Device> observableList = FXCollections.observableArrayList( );
                                observableList.addAll(listDevice);
                                tableAddDevice.setItems(observableList);
                            }

                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(titleInformation);
                            alert.setHeaderText(null);
                            alert.setContentText(textInformation1);
                            alert.showAndWait();
                        }


                    }

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(titleError);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }


            }
        });


        delDevice.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Device device = tableAddDevice.getSelectionModel().getSelectedItem();
                    if (device == null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(titleInformation);
                        alert.setHeaderText(null);
                        alert.setContentText(textInformationDelete);
                        alert.showAndWait();
                    } else {
                        ObservableList<Device> observableList = tableAddDevice.getItems();
                        List<Device> obs = observableList.stream( ).filter(dev -> dev.getId() != device.getId()).collect(Collectors.toList());
                        ObservableList<Device> obsDev = FXCollections.observableArrayList( obs );
                        tableAddDevice.setItems(obsDev);
                        listDevice.clear();

                    }

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(titleError);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }
        });
    }


    public GUIDevice getGuiDevice() {
        return guiDevice;
    }

    public void setGuiDevice(GUIDevice guiDevice) {
        this.guiDevice = guiDevice;
    }

    public GUIEmployee getGuiEmployee() {
        return guiEmployee;
    }

    public void setGuiEmployee(GUIEmployee guiEmployee) {
        this.guiEmployee = guiEmployee;
    }

    public static Unit getRecordUnit() {
        return recordUnit;
    }

    public static Stage getParentStage() {
        return parentStage;
    }


}
