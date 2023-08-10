package ru.brkmed.dtk.gui.main;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.formula.functions.T;
import ru.brkmed.dtk.dao.mainClasses.entityes.AbstractEntity;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Department;
import ru.brkmed.dtk.dao.mainClasses.entityes.Device;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDepartment;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDevice;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class GUIDevice extends AbstractGUIControler{

    private  TableView<Device> tableDevice;

    private ObservableList<Device> obsDevice;

    private static Device deviceRecord;

    private static GUIDevice GUIDevice;

    private List<Button> buttons;

    private Stage stage;



    public GUIDevice() {
    }

    public GUIDevice(List<Button> buttons, TableView<Device> tableDevice, Stage stage) {
        this.buttons = buttons;
        this.tableDevice = tableDevice;
        this.stage = stage;

    }

    @Override
    public ObservableList<? extends AbstractEntity> getObservableList() {
        ObservableList<Device> observableList = FXCollections.observableArrayList();
        observableList.sorted();
        List<Device> deviceList =  new ControlerDaoDevice().listDevices();
        observableList.addAll(deviceList);
        return observableList;
    }

    @Override
    public void setValueTableView(TableView<? extends AbstractEntity> tableView) {
        tableDevice = (TableView<Device>) super.getTableView();
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
        TableColumn<Device, Boolean> connect = new TableColumn<>( "Подключено к ЗСПД" );
        connect.setPrefWidth(275);
        connect.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>( ) {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Device, Boolean> deviceBoolean) {
                Device device = deviceBoolean.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty( device.isConnect() );
                return booleanProperty;
            }
        });
        connect.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>( ) {
            @Override
            public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> deviceBooleanTableColumn) {
                CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<>(  );
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        tableDevice.getColumns().addAll(idDevice, typeDevice, number, brand, typeOC, term, connect);
        obsDevice = (ObservableList<Device>) getObservableList();
        TextField find = super.getTxtFieldFind();
        SortedList<Device> sortedList = (SortedList<Device>) findValueRecord(tableDevice, obsDevice, find);
        tableDevice.setItems(sortedList);
        tableDevice.getSelectionModel().select(0);


    }

    @Override
    public void fillValuesCreateWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        ChoiceBox<String> typeDevice = (ChoiceBox<String>) listNodes.get(0);
        typeDevice.getItems().addAll(getObsTypeDevice());
        ChoiceBox<String> typeOC = (ChoiceBox<String>) listNodes.get(7);
        TextField term = (TextField) listNodes.get(10);
        getIntegerValue12Char(term);
        CheckBox connect = (CheckBox) listNodes.get(12);
        connect.setDisable(true);

        typeDevice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>( ) {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNumber, Number newNumber) {
                Integer choice = newNumber.intValue( );
                ObservableList<String> observableList = typeOC.getItems( );
                if (choice == 0 || choice == 3) {
                    if (observableList.size() == 0) {
                        typeOC.getItems( ).addAll(getObsTypeOC( ));
                        connect.setDisable(false);
                    }
                }  else if (observableList.size() != 0 || connect.isSelected()) {
                    ObservableList<String> obs = FXCollections.observableArrayList();
                    typeOC.setItems(obs);
                    connect.setSelected(false);
                    connect.setDisable(true);
                }
            }
        });

    }

    @Override
    public void fillValuesEditWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        ChoiceBox<String> typeDevice = (ChoiceBox<String>) listNodes.get(0);
        TextField brand = (TextField) listNodes.get(3);
        TextField number = (TextField) listNodes.get(5);
        ChoiceBox<String> typeOC = (ChoiceBox<String>) listNodes.get(7);
        TextField term = (TextField) listNodes.get(10);
        CheckBox connect = (CheckBox) listNodes.get(12);
        connect.setDisable(true);

        typeDevice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>( ) {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNumber, Number newNumber) {
                Integer choice = newNumber.intValue( );
                ObservableList<String> observableList = typeOC.getItems( );
                if (choice == 0 || choice == 3) {
                    if (observableList.size() == 0) {
                        typeOC.getItems( ).addAll(getObsTypeOC( ));
                        connect.setDisable(false);
                    }
                } else if (observableList.size() != 0 || connect.isSelected()) {
                    ObservableList<String> obs = FXCollections.observableArrayList();
                    typeOC.setItems(obs);
                    connect.setSelected(false);
                    connect.setDisable(true);

                }
            }
        });

        deviceRecord = (Device) super.getRecordTableView();
        typeDevice.setValue(deviceRecord.getTypeDevice());
        typeDevice.getItems().addAll(getObsTypeDevice());
        brand.setText(deviceRecord.getBrand( ));
        number.setText(deviceRecord.getNumber());
        typeOC.setValue(deviceRecord.getTypeOC());
        //typeOC.getItems().addAll(getObsTypeOC());
        term.setText(String.valueOf(deviceRecord.getTerm()));
        getIntegerValue12Char(term);
        connect.setSelected(deviceRecord.isConnect( ));


    }

    @Override
    public void addEventStage() {
        super.getStage( ).addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
                tableDevice.setItems((ObservableList<Device>) getObservableList( ));
                getCountRecordLabel().setText(String.valueOf(tableDevice.getItems().size()));
            }
        });
    }

    @Override
    public AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage) {
        buttonList.add(button);
        tableDevice.setItems((ObservableList<Device>) getObservableList( ));
        GUIDevice = new GUIDevice( buttonList, tableDevice, stage );
        return GUIDevice;

    }

    @Override
    public SortedList<? extends AbstractEntity> findValueRecord(TableView<? extends AbstractEntity> tableView, ObservableList<? extends AbstractEntity> observableList, TextField txtFieldFind) {
        FilteredList<Device> filterData = new FilteredList<>((ObservableList<Device>) observableList, b -> true);
        txtFieldFind.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(device -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(device.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (device.getTypeDevice().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (device.getNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (device.getBrand().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (device.getTypeOC().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(device.getTerm()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });


        });
        SortedList<Device> sortedData = new SortedList<>(filterData);
        TableView<Device> deviceTableView = (TableView<Device>) tableView;
        sortedData.comparatorProperty().bind(deviceTableView.comparatorProperty());
        return sortedData;
    }

    @Override
    public void resetFindTextField(ContextMenu contextMenu) {
        MenuItem menuItem = contextMenu.getItems( ).get(0);
        menuItem.setOnAction(actionEvent -> {
            ObservableList<Device> observableList = FXCollections.observableArrayList();
            List<Device> listDevice = new ControlerDaoDevice().listDevices();
            observableList.addAll(listDevice);
            SortedList<Device> sortedListField = (SortedList<Device>) findValueRecord(tableDevice, observableList, getTxtFieldFind());
            tableDevice.setItems(sortedListField);
        });

    }

    @Override
    public void deleteRecord() {
        Device device = (Device) super.getRecordTableView();
        ControlerDaoDevice controlerDaoDevice = new ControlerDaoDevice();
        controlerDaoDevice.deleteDevice(device.getId());
        tableDevice.setItems((ObservableList<Device>) getObservableList( ));
        getCountRecordLabel().setText(String.valueOf(tableDevice.getItems().size()));
    }

    @Override
    public void getIntegerValue12Char(TextField textField) {
        DecimalFormat format = new DecimalFormat( "###" );
        textField.setTextFormatter(new TextFormatter<>(c -> {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else {
                String ob = String.valueOf(object);
                if (Pattern.matches("\\d{3}?", ob)) {
                    // String[] strings = textField.getText( ).split(".");

                    //c.getControlNewText().
                    return null;
                }
                else {
                    return c;
                }
            }

        }));
    }

    public void alternativeTab(TabPane tabPane) {
        super.basicWindowTab(tabPane, "Оборудование");
        Button create = super.getCreateButton();
        super.getNewDialogWindow(create,
                "/DeviceCreateEditRecord.fxml", "Добавить оборудование");
        Button edit = super.getEditButton();
        super.getNewDialogWindow(edit,
                "/DeviceCreateEditRecord.fxml", "Изменить оборудование");
        Button delete = super.getDeleteButton();
        getNewDialogWindowDelete(delete);


    }

    public String[] getObsTypeDevice() {
        String[] array = new String[] {"Персональные компьютеры", "Серверное оборудование", "Печатающие устройства и МФУ", "Мобильные АРМ (планшеты)",
                "VipNet Coordinator HW50", "VipNet Coordinator HW100", "VipNet Coordinator HW1000"};
        return array;
    }

    public String[] getObsTypeOC() {
        String[] array = new String[] {"", "ОС семейства Windows", "ОС отечественной разработки", "иные ОС"};
        return array;
    }

    public TableView<Device> getTableDevice() {
        return tableDevice;
    }

    public static Device getDeviceRecord() {
        return deviceRecord;
    }

    public static ru.brkmed.dtk.gui.main.GUIDevice getGUIDevice() {
        return GUIDevice;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
