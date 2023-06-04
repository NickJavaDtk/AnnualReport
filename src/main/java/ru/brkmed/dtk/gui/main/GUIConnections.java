package ru.brkmed.dtk.gui.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import ru.brkmed.dtk.dao.mainClasses.entityes.AbstractEntity;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Connection;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoBuilding;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoConnection;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

public class GUIConnections extends AbstractGUIControler {
//    ObservableList<Connection> observableList = null;
//
//    List<Connection> listIluminatedConnection = null;
//
//    static TableView<Connection> getTableConnections;
//
//    private List<Button> buttonList;
//
//    private Long idConnect;


    private static Map<Long, Building> mapChcBoxBuild;

    //____________________________________

    private TableView<Connection> tableConnection;

    private ObservableList<Connection> obsConnection;

    private static Connection connectRecord;

    private static GUIConnections GUIConnections;

    public final Connection connection = new Connection( );

    private List<Button> buttons;

    private Stage stage;


    public GUIConnections() {
    }

    public GUIConnections(List<Button> buttons, TableView<Connection> tableConnection, Stage stage) {
        this.buttons = buttons;
        this.tableConnection = tableConnection;
        this.stage = stage;
    }


    @Override
    public ObservableList<? extends AbstractEntity> getObservableList() {
        ObservableList<Connection> observableList = FXCollections.observableArrayList( );
        observableList.sorted( );
        List<Connection> connectionList = new ControlerDaoConnection( ).listConnections( );
        observableList.addAll(connectionList);
        return observableList;
    }

    @Override
    public void setValueTableView(TableView<? extends AbstractEntity> tableView) {
        tableConnection = (TableView<Connection>) super.getTableView( );
        TableColumn<Connection, Long> idConnect = new TableColumn<>("ID Подключения");
        idConnect.setPrefWidth(45.0);
        idConnect.setCellValueFactory(new PropertyValueFactory<Connection, Long>("Id"));
        TableColumn<Connection, String> nameConnect = new TableColumn<>("Имя подключения");
        nameConnect.setPrefWidth(275.0);
        nameConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("nameConnection"));
        TableColumn<Connection, String> nameBuilding = new TableColumn<>("Здание");
        nameBuilding.setPrefWidth(275.0);
        nameBuilding.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Connection, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Connection, String> connectionBuildString) {
                return new ReadOnlyObjectWrapper<>(connectionBuildString.getValue( ).getBuild( ).getNameBuilding( ));
            }
        });
        // nameBuilding.setCellValueFactory(new PropertyValueFactory<Connection, Building>("build.nameBuilding"));
        TableColumn<Connection, String> connectDate = new TableColumn<>("Дата подключения");
        connectDate.setPrefWidth(275.0);
        connectDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Connection, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Connection, String> connectionStringCellDataFeatures) {
                String input = String.valueOf(connectionStringCellDataFeatures.getValue( ).getDateConnection( ));
                return new ReadOnlyObjectWrapper<>(getStringDate(input));
            }
        });
        //connectDate.setCellValueFactory(new PropertyValueFactory<Connection, Date>("dateConnection"));
        TableColumn<Connection, String> suplierConnect = new TableColumn<>("Провайдер");
        suplierConnect.setPrefWidth(275.0);
        suplierConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("suplier"));
        TableColumn<Connection, String> typeConnect = new TableColumn<>("Тип подключения");
        typeConnect.setPrefWidth(275.0);
        typeConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("typeConnection"));
        TableColumn<Connection, Double> speedConnect = new TableColumn<>("Скорость Мб/с");
        speedConnect.setPrefWidth(275.0);
        speedConnect.setCellValueFactory(new PropertyValueFactory<Connection, Double>("speedConnection"));
        TableColumn<Connection, Double> taxConnect = new TableColumn<>("Абонентская плата");
        taxConnect.setPrefWidth(275.0);
        taxConnect.setCellValueFactory(new PropertyValueFactory<Connection, Double>("tax"));
        TableColumn<Connection, Double> taxStartConnect = new TableColumn<>("Стоимость подключения");
        taxStartConnect.setPrefWidth(275.0);
        taxStartConnect.setCellValueFactory(new PropertyValueFactory<Connection, Double>("startTax"));
        TableColumn<Connection, String> typeTaxConnect = new TableColumn<>("Тип оплаты");
        typeTaxConnect.setPrefWidth(275.0);
        typeTaxConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("typeTax"));

        tableConnection.getColumns( ).addAll(idConnect, nameConnect, nameBuilding, connectDate, suplierConnect,
                typeConnect, speedConnect, taxConnect, taxStartConnect, typeTaxConnect);

        obsConnection = (ObservableList<Connection>) getObservableList( );
        TextField find = super.getTxtFieldFind( );
        SortedList<Connection> sortedList = (SortedList<Connection>) findValueRecord(tableConnection, obsConnection, find);
        tableConnection.setItems(sortedList);
        tableConnection.getSelectionModel( ).select(0);

    }

    @Override
    public void fillValuesCreateWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        ChoiceBox<String> choiceBoxBuild = (ChoiceBox<String>) listNodes.get(4);
        choiceBoxBuild.getItems( ).addAll(getObsBuildConnect( ));
        DatePicker datePicker = (DatePicker) listNodes.get(8);
        // setDataDatePicker(datePicker);
        // choiceBoxBuild.setValue("Выберите здание");
        ChoiceBox<String> choiceBoxTypeConnect = (ChoiceBox<String>) listNodes.get(12);
        choiceBoxTypeConnect.getItems( ).addAll(connection.getObsTypeConnect( ));
        // choiceBoxTypeConnect.setValue("Выберите тип подключения");
        ChoiceBox<String> choiceBoxTypeTax = (ChoiceBox<String>) listNodes.get(21);
        choiceBoxTypeTax.getItems( ).addAll(connection.getObsTypeTax( ));
        TextField speedConnect = (TextField) listNodes.get(14);
        getDoubleValue(speedConnect);
        TextField tax = (TextField) listNodes.get(17);
        getDoubleValue(tax);
        TextField taxStart = (TextField) listNodes.get(18);
        getDoubleValue(taxStart);
    }

    @Override
    public void fillValuesEditWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        TextField nameConnection = (TextField) listNodes.get(3);
        ChoiceBox<String> buildConnect = (ChoiceBox<String>) listNodes.get(4);
        Button saveOrEdit = (Button) listNodes.get(6);
        DatePicker datePicker = (DatePicker) listNodes.get(8);
        TextField suplierConnection = (TextField) listNodes.get(10);
        ChoiceBox<String> typeConnection = (ChoiceBox<String>) listNodes.get(12);
        TextField speedConnection = (TextField) listNodes.get(14);
        TextField tax = (TextField) listNodes.get(17);
        TextField connectionTax = (TextField) listNodes.get(18);
        ChoiceBox<String> typeTaxConnection = (ChoiceBox<String>) listNodes.get(21);

        connectRecord = (Connection) super.getRecordTableView( );
        nameConnection.setText(connectRecord.getNameConnection( ));
        buildConnect.setValue(connectRecord.getBuild( ).getNameBuilding( ));
        buildConnect.getItems( ).addAll(getObsBuildConnect( ));
        Instant instant = connectRecord.getDateConnection( ).toInstant( );
        datePicker.setValue(instant.atZone(ZoneId.systemDefault( )).toLocalDate( ));
        datePicker.getEditor( ).setDisable(true);
        datePicker.getEditor( ).setOpacity(1);
        // setDataDatePicker(datePicker.getEditor());
        suplierConnection.setText(connectRecord.getSuplier( ));
        typeConnection.setValue(connectRecord.getTypeConnection( ));
        typeConnection.getItems( ).addAll(connection.getObsTypeConnect( ));
        speedConnection.setText(String.valueOf(connectRecord.getSpeedConnection( )));
        speedConnection.textProperty( ).addListener((observable, oldvalue, newvalue) -> {

            getDoubleValue(speedConnection);
        });
        tax.setText(String.valueOf(connectRecord.getTax( )));
        tax.textProperty( ).addListener((observable, oldvalue, newvalue) -> {
            getDoubleValue(tax);
        });
        connectionTax.setText(String.valueOf(connectRecord.getStartTax( )));
        connectionTax.textProperty( ).addListener((observable, oldvalue, newvalue) -> {
            getDoubleValue(connectionTax);
        });
        typeTaxConnection.setValue(connectRecord.getTypeTax( ));
        typeTaxConnection.getItems( ).addAll(connection.getObsTypeTax( ));

//            getStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
//                @Override
//                public void handle(WindowEvent windowEvent) {
//
//                    observableList = getObsConnection();
//                    listConnections.setItems(observableList);
//
//                }
//            });


    }

    @Override
    public void addEventStage() {
        super.getStage( ).addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
                tableConnection.setItems((ObservableList<Connection>) getObservableList( ));
            }
        });

    }

    @Override
    public AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage) {
        buttonList.add(button);
        tableConnection.setItems((ObservableList<Connection>) getObservableList( ));
        GUIConnections = new GUIConnections(buttonList, tableConnection, stage);
        return GUIConnections;

    }

    @Override
    public SortedList<? extends AbstractEntity> findValueRecord(TableView<? extends AbstractEntity> tableView, ObservableList<? extends AbstractEntity> observableList, TextField txtFieldFind) {
        FilteredList<Connection> filterData = new FilteredList<>((ObservableList<Connection>) observableList, b -> true);
        txtFieldFind.textProperty( ).addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(connection -> {
                if (newValue == null || newValue.isEmpty( )) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase( );
                if (String.valueOf(connection.getId( )).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (connection.getNameConnection( ).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (connection.getBuild( ).getNameBuilding( ).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if ((getStringDate(String.valueOf(connection.getDateConnection( ))).toLowerCase( ).indexOf(lowerCaseFilter) != -1)) {
                    return true;
                } else if (connection.getSuplier( ).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (connection.getTypeConnection( ).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(connection.getSpeedConnection( )).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(connection.getTax( )).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(connection.getStartTax( )).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (connection.getTypeTax( ).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });


        });
        SortedList<Connection> sortedData = new SortedList<>(filterData);
        TableView<Connection> tableConnection = (TableView<Connection>) tableView;
        sortedData.comparatorProperty( ).bind(tableConnection.comparatorProperty( ));
        return sortedData;
    }

    @Override
    public void resetFindTextField(ContextMenu contextMenu) {
        MenuItem menuItem = contextMenu.getItems( ).get(0);
        menuItem.setOnAction(actionEvent -> {
            ObservableList<Connection> observableList = FXCollections.observableArrayList();
            List<Connection> listConnections = new ControlerDaoConnection().listConnections();
            observableList.addAll(listConnections);
            SortedList<Connection> sortedListField = (SortedList<Connection>) findValueRecord(tableConnection, observableList, getTxtFieldFind());
            tableConnection.setItems(sortedListField);
        });
    }

    @Override
    public void deleteRecord() {
        Connection connect = (Connection) super.getRecordTableView( );
        ControlerDaoConnection controlerDaoConnection = new ControlerDaoConnection( );
        controlerDaoConnection.deleteConnection(connect.getId( ));
        tableConnection.setItems((ObservableList<Connection>) getObservableList( ));
    }




    public void alternativeTab(TabPane tabPane) {
        super.basicWindowTab(tabPane, "Новый путь подключений");
        Button create = super.getCreateButton( );
        super.getNewDialogWindow(create,
                "/ConnectionsCreateEditRecord.fxml", "Создать подключение по новому пути");
        Button edit = super.getEditButton( );
        super.getNewDialogWindow(edit,
                "/ConnectionsCreateEditRecord.fxml", "Изменить подключение по новому пути");
        // setButton(super.getPresButton());
        Button delete = super.getDeleteButton( );
        getNewDialogWindowDelete(delete);


    }

    public ObservableList<String> getObsBuildConnect() {
        AbstractGUIControler absGUI = new GUIBuilding( );
        ObservableList<Building> obsBuild = (ObservableList<Building>) absGUI.getObservableList( );
        mapChcBoxBuild = new HashMap<>( );
        List<String> listNameBuild = new ArrayList<>( );
        ObservableList<String> obsListNameBuild = FXCollections.observableArrayList( );
        for (Building build : obsBuild) {
            mapChcBoxBuild.put(build.getId( ), build);
            listNameBuild.add(build.getNameBuilding( ));
        }
        obsListNameBuild.addAll(listNameBuild);
        return obsListNameBuild;
    }

    public static GUIConnections getControlerGUIConnections() {
        return GUIConnections;
    }


    public List<Button> getButtonList() {
        return buttons;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public static Connection getConnectRecord() {
        return connectRecord;
    }

    public TableView<Connection> getTableConnection() {
        return tableConnection;
    }

    public static Map<Long, Building> getMapChcBoxBuild() {
        return mapChcBoxBuild;
    }

    /*  public void createAddTab(TabPane tabPane) {
        Tab connectionTab = new Tab( "Подключения" );
        connectionTab.setClosable(true);
        AnchorPane externAnchorPane = new AnchorPane();
        externAnchorPane.setPrefWidth(747.0);
        externAnchorPane.setPrefHeight(507.0);
        externAnchorPane.setMaxHeight(Region.USE_COMPUTED_SIZE);
        externAnchorPane.setMinHeight(Region.USE_COMPUTED_SIZE);
        externAnchorPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
        externAnchorPane.setMinWidth(Region.USE_COMPUTED_SIZE);
        externAnchorPane.minHeight(0.0);
        externAnchorPane.minWidth(0.0);

        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setLayoutX(6.0);
        innerAnchorPane.prefHeight(44.0);
        innerAnchorPane.prefWidth(264.0);
        externAnchorPane.setLeftAnchor(innerAnchorPane, 6.0);
        externAnchorPane.setTopAnchor(innerAnchorPane, 2.0);

        Button btnCreateConnection = new Button( "Создать" );
        btnCreateConnection.setLayoutY(2.0);
        btnCreateConnection.setPadding(new Insets(10));
        innerAnchorPane.setLeftAnchor(btnCreateConnection, 0.0);
        innerAnchorPane.setTopAnchor(btnCreateConnection, 2.0);


        Button btnEditConnection = new Button( "Изменить" );
        btnEditConnection.setLayoutY(2.0);
        btnEditConnection.setLayoutX(71.0);
        btnEditConnection.setPadding(new Insets(10));
        innerAnchorPane.setLeftAnchor(btnEditConnection, 0.0);
        innerAnchorPane.setTopAnchor(btnEditConnection, 2.0);

        Group groupInnnerPane = new Group( btnCreateConnection, btnEditConnection);
        innerAnchorPane.getChildren().add(groupInnnerPane);

        TableView<Connection> listConnections = getCurrentTableView(externAnchorPane);
        getTableConnections = listConnections;

        getCreateButton(btnCreateConnection, listConnections);
        getEditButton(btnEditConnection, listConnections);

//
//
//        btnEditConnection.setOnAction(event -> {
//            buttonList = new ArrayList<>();
//            buttonList.add(btnEditConnection);
//            setButtonList(buttonList);
//            FXMLLoader loader = new FXMLLoader(  );
//            loader.setLocation(getClass().getResource("/ConnectionsCreateEditRecord.fxml"));
//            try{
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent root = loader.getRoot();
//            getStage = new Stage(  );
//            getStage.setTitle("Редактировать подключение");
//            getStage.initModality(Modality.APPLICATION_MODAL);
//            getStage.setScene(new Scene(root));
//            ArrayList<Node> listNodes = ListNodes.getAllNodes(root );
//            TextField nameConnection = (TextField) listNodes.get(3);
//            ChoiceBox<Building> buildConnect = (ChoiceBox<Building>) listNodes.get(4);
//            Button saveOrEdit = (Button) listNodes.get(6);
//            Connection getConnections = getRecordConnection(listConnections);
//            nameConnection.setText(getConnections.getNameConnection());
//            buildConnect.getItems().add(buildConnect.getValue());
//
//            getStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
//                @Override
//                public void handle(WindowEvent windowEvent) {
//
//                    observableList = getObsConnection();
//                    listConnections.setItems(observableList);
//
//                }
//            });
//            getStage.showAndWait();
//
//        });



        Label countRecordConnect = new Label( "Количество записей" );
        countRecordConnect.setLayoutX(14.0);
        countRecordConnect.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(countRecordConnect, 5.0);
        externAnchorPane.setLeftAnchor(countRecordConnect, 14.0);
        externAnchorPane.getChildren().add(countRecordConnect);
        String countRecord = String.valueOf(listConnections.getItems().size());
        Label resultBuilding = new Label(countRecord);
        resultBuilding.setLayoutX(138.0);
        resultBuilding.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(resultBuilding, 5.0);
        externAnchorPane.getChildren().add(resultBuilding);




        externAnchorPane.getChildren().add(listConnections);
        externAnchorPane.getChildren().add(innerAnchorPane);


        tabPane.getTabs().add(connectionTab);
        connectionTab.setContent(externAnchorPane);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void getCreateButton(Button btnCreateConnection, TableView<Connection> listConnections) {

        btnCreateConnection.setOnAction(event -> {
            buttonList = new ArrayList<>();
            buttonList.add(btnCreateConnection);
            setButtonList(buttonList);
            FXMLLoader loader = new FXMLLoader(  );
            loader.setLocation(getClass().getResource("/ConnectionsCreateEditRecord.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage(  );
            stage.setTitle("Добавить подключение");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            ArrayList<Node> listNodes = ListNodes.getAllNodes(root );
            ChoiceBox<String> choiceBoxBuild = (ChoiceBox<String>) listNodes.get(4);
            choiceBoxBuild.getItems().addAll(getObsBuildConnect());
            // choiceBoxBuild.setValue("Выберите здание");
            ChoiceBox<String> choiceBoxTypeConnect = (ChoiceBox<String>) listNodes.get(12);
            choiceBoxTypeConnect.getItems().addAll(getObsTypeConnect());
            // choiceBoxTypeConnect.setValue("Выберите тип подключения");
            ChoiceBox<String> choiceBoxTypeTax = (ChoiceBox<String>) listNodes.get(21);
            choiceBoxTypeTax.getItems().addAll(getObsTypeTax());
            TextField speedConnect = (TextField) listNodes.get(14 );
            getDoubleValue(speedConnect);
            TextField tax = (TextField) listNodes.get(17);
            getDoubleValue(tax);
            TextField taxStart = (TextField) listNodes.get(18);
            getDoubleValue(taxStart);




//            choiceBoxBuild.setOnMouseClicked(new EventHandler<MouseEvent>( ) {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                    choiceBoxBuild.show();
//
//                }
//            });

            //   choiceBoxBuild.getItems().addAll(getObsBuildConnect());
            //   choiceBoxBuild.show();

            stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
                @Override
                public void handle(WindowEvent windowEvent) {

                    observableList = getObsConnection();
                    listConnections.setItems(observableList);

                }
            });
            stage.showAndWait();


        });

    }

    public void getEditButton(Button btnEditConnection, TableView<Connection> listConnections) {
        btnEditConnection.setOnAction(event -> {
            buttonList = new ArrayList<>();
            buttonList.add(btnEditConnection);
            setButtonList(buttonList);
            FXMLLoader loader = new FXMLLoader(  );
            loader.setLocation(getClass().getResource("/ConnectionsCreateEditRecord.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            getStage = new Stage(  );
            getStage.setTitle("Редактировать подключение");
            getStage.initModality(Modality.APPLICATION_MODAL);
            getStage.setScene(new Scene(root));
            ArrayList<Node> listNodes = ListNodes.getAllNodes(root );
            TextField nameConnection = (TextField) listNodes.get(3);
            ChoiceBox<String> buildConnect = (ChoiceBox<String>) listNodes.get(4);
            Button saveOrEdit = (Button) listNodes.get(6);
            DatePicker datePicker = (DatePicker) listNodes.get(8);
            TextField suplierConnection = (TextField) listNodes.get(10);
            ChoiceBox<String> typeConnection = (ChoiceBox<String>) listNodes.get(12);
            TextField speedConnection = (TextField) listNodes.get(14);
            TextField tax = (TextField) listNodes.get(17);
            TextField connectionTax = (TextField) listNodes.get(18);
            ChoiceBox<String> typeTaxConnetction = (ChoiceBox<String>) listNodes.get(21);

            Connection getConnections = getRecordConnection(listConnections);
            nameConnection.setText(getConnections.getNameConnection());
            buildConnect.setValue(getConnections.getBuild().getNameBuilding());
            buildConnect.getItems().addAll(getObsBuildConnect());
            Instant instant = getConnections.getDateConnection().toInstant();
            datePicker.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
            datePicker.getEditor().setDisable(true);
            datePicker.getEditor().setOpacity(1);
           // setDataDatePicker(datePicker.getEditor());
            suplierConnection.setText(getConnections.getSuplier());
            typeConnection.setValue(getConnections.getTypeConnection());
            typeConnection.getItems().addAll(getObsTypeConnect());
            speedConnection.setText(String.valueOf(getConnections.getSpeedConnection()));
            speedConnection.textProperty().addListener((observable, oldvalue, newvalue) -> {

                getDoubleValue(speedConnection);
            });
            tax.setText(String.valueOf(getConnections.getTax()));
            tax.textProperty().addListener((observable, oldvalue, newvalue) -> {
                getDoubleValue(tax);
            } );
            connectionTax.setText(String.valueOf(getConnections.getStartTax()));
            connectionTax.textProperty().addListener((observable, oldvalue, newvalue) -> {
                getDoubleValue(connectionTax);
            });
            typeTaxConnetction.setValue(getConnections.getTypeTax());
            typeTaxConnetction.getItems().addAll(getObsTypeTax());

//            getStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
//                @Override
//                public void handle(WindowEvent windowEvent) {
//
//                    observableList = getObsConnection();
//                    listConnections.setItems(observableList);
//
//                }
//            });
            getStage.showAndWait();

        });
    }
    public TableView<Connection> getCurrentTableView(AnchorPane externAnchorPane) {
        TableView<Connection> listConnection = new TableView<>( );
        listConnection.setLayoutY(46.0);
        listConnection.prefHeight(443.0);
        listConnection.prefWidth(721.0);
        listConnection.setMaxHeight(Region.USE_COMPUTED_SIZE);
        listConnection.setMaxWidth(Region.USE_COMPUTED_SIZE);
        listConnection.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        externAnchorPane.setLeftAnchor(listConnection, 0.0);
        externAnchorPane.setRightAnchor(listConnection, 0.0);
        externAnchorPane.setTopAnchor(listConnection, 46.0);
        externAnchorPane.setBottomAnchor(listConnection, 18.0);


        TableColumn<Connection, Long> idConnect = new TableColumn<>("ID Подключения");
        idConnect.setPrefWidth(45.0);
        idConnect.setCellValueFactory(new PropertyValueFactory<Connection, Long>("Id"));
        TableColumn<Connection, String> nameConnect = new TableColumn<>("Имя подключения");
        nameConnect.setPrefWidth(275.0);
        nameConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("nameConnection"));
        TableColumn<Connection, String> nameBuilding = new TableColumn<>("Здание");
        nameBuilding.setPrefWidth(275.0);
        nameBuilding.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Connection, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Connection, String> connectionBuildString) {
                return new ReadOnlyObjectWrapper<>( connectionBuildString.getValue().getBuild().nameBuilding );
            }
        });
       // nameBuilding.setCellValueFactory(new PropertyValueFactory<Connection, Building>("build.nameBuilding"));
        TableColumn<Connection, String> connectDate = new TableColumn<>( "Дата подключения" );
        connectDate.setPrefWidth(275.0);
        connectDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Connection, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Connection, String> connectionStringCellDataFeatures) {
                String input = String.valueOf(connectionStringCellDataFeatures.getValue().getDateConnection());
                return new ReadOnlyObjectWrapper<>( getStringDate(input));
            }
        });
        //connectDate.setCellValueFactory(new PropertyValueFactory<Connection, Date>("dateConnection"));
        TableColumn<Connection, String> suplierConnect = new TableColumn<>("Провайдер");
        suplierConnect.setPrefWidth(275.0);
        suplierConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("suplier"));
        TableColumn<Connection, String> typeConnect = new TableColumn<>("Тип подключения");
        typeConnect.setPrefWidth(275.0);
        typeConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("typeConnection"));
        TableColumn<Connection, Double> speedConnect = new TableColumn<>("Скорость Мб/с");
        speedConnect.setPrefWidth(275.0);
        speedConnect.setCellValueFactory(new PropertyValueFactory<Connection, Double>("speedConnection"));
        TableColumn<Connection, Double> taxConnect = new TableColumn<>("Абонентская плата");
        taxConnect.setPrefWidth(275.0);
        taxConnect.setCellValueFactory(new PropertyValueFactory<Connection, Double>("tax"));
        TableColumn<Connection, Double> taxStartConnect = new TableColumn<>("Стоимость подключения");
        taxStartConnect.setPrefWidth(275.0);
        taxStartConnect.setCellValueFactory(new PropertyValueFactory<Connection, Double>("startTax"));
        TableColumn<Connection, String> typeTaxConnect = new TableColumn<>("Тип оплаты");
        typeTaxConnect.setPrefWidth(275.0);
        typeTaxConnect.setCellValueFactory(new PropertyValueFactory<Connection, String>("typeTax"));

        listConnection.getColumns().addAll(idConnect, nameConnect, nameBuilding, connectDate, suplierConnect,
                typeConnect, speedConnect, taxConnect, taxStartConnect, typeTaxConnect);

        observableList = getObsConnection();
        listConnection.setItems(observableList);
        listConnection.getSelectionModel().select(0);
        //listConnection.setFocusModel(listConnection.setSelectionModel().setSelectedCell().set(0));
        return listConnection;
    }

    public ObservableList<Connection> getObsConnection() {
        ObservableList<Connection> observableList = FXCollections.observableArrayList();
        observableList.sorted();
        List<Connection> connectionList = new ControlerDaoConnection().listConnections();
        for(Connection connect : connectionList) {
            observableList.add(connect);
        }
        return observableList;
    }

    public Connection getRecordConnection(TableView tableView) {
//        TableView.TableViewSelectionModel<Building> selectionModel = tableView.getSelectionModel();
//        final List<Building> build = null;
//        selectionModel.selectedItemProperty().addListener(new ChangeListener<Building>( ) {
//            @Override
//            public void changed(ObservableValue<? extends Building> observableValue, Building building, Building t1) {
//                if (t1 != null) {
//                    build.add(t1)  ;               }
//            }
//        });
//        return build;
        TablePosition<Connection, Long> tbConnectionID = (TablePosition<Connection, Long>) tableView.getSelectionModel().getSelectedCells( ).get(0 );
        int rowID = tbConnectionID.getRow( );
        Connection connectionID = (Connection) tableView.getItems().get(rowID);
        idConnect = connectionID.getId();
//        TableColumn<Building, Long> colID = tbBuildingID.getTableColumn();
//        Long lgID = colID.getCellObservableValue(buildingID).getValue();
//
//        TablePosition<Building, String> tbBuildingName = (TablePosition<Building, String>) tableView.getSelectionModel().getSelectedCells( ).get(1 );
//        int rowName = tbBuildingName.getRow( );
//        Building buildingName = (Building) tableView.getItems().get(rowName);
//        TableColumn<Building, String> colNm = tbBuildingName.getTableColumn();
//        String srNm = colNm.getCellObservableValue(buildingName).getValue();
//
//        TablePosition<Building, String> tbBuildingAdress = (TablePosition<Building, String>) tableView.getSelectionModel().getSelectedCells( ).get(2 );
//        int rowAd = tbBuildingAdress.getRow( );
//        Building buildingAd = (Building) tableView.getItems().get(rowAd);
//        TableColumn<Building, String> colAd = tbBuildingAdress.getTableColumn();
//        String srAd = colAd.getCellObservableValue(buildingAd).getValue();
//


        return connectionID;
    }

    public List<Connection> getListIluminatedConnection() {
        return listIluminatedConnection;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }

    public ObservableList<String> getObsBuildConnect() {
      //  ObservableList<Building> obsBuild = new ControlerGUIBuilding().getObsBuild();
        AbstractGUIControler absGUI = new ControlerGUIBuilding(  );
        ObservableList<Building> obsBuild = (ObservableList<Building>) absGUI.getObservableList();
        mapChcBoxBuild = new HashMap<>();
        List<String> listNameBuild = new ArrayList<>();
        ObservableList<String> obsListNameBuild = FXCollections.observableArrayList();
        for(Building build : obsBuild) {
            mapChcBoxBuild.put(build.getId(), build);
            listNameBuild.add(build.getNameBuilding());
        }
        obsListNameBuild.addAll(listNameBuild);
      return obsListNameBuild;
    }

    public String[] getObsTypeConnect() {
        String[] obsType = new String[]{"Модем", "DSL", "Оптоволокно", "Радиодоступ", "Спутниковый канал", "VPN" };
        return obsType;
    }

    public String[] getObsTypeTax() {
        String[] obsType = new String[]{"Оператор связи", "Региональный бюджет", "Собственные средства МО", "Федеральный бюджет", "Фонд ОМС", "Не указано" };
        return obsType;
    }

    public void getDoubleValue(TextField textField) {
//        textField.textProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    if (!newValue.matches("(?<!\\.)\\d+(?!\\.\\d+)") ) {
//                            //Double number = Double.parseDouble(newValue);
//                        textField.setText(newValue.replaceAll("(?<!\\.)\\d+(?!\\.\\d+)", ""));
//
//                    }
////                    else {
////                        textField.setText(oldValue);
////                        textField.positionCaret(textField.getLength());
////                    }
//
//                });

        DecimalFormat format = new DecimalFormat( "#.##" );
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
                Pattern pattern = Pattern.compile("\\d*\\.\\d{2}?");
                if (Pattern.matches("\\d*\\.\\d{3}?", ob)) {
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

    public void setDataDatePicker(DatePicker datePik){
        String pattern = "dd.MM.yyyy";
        datePik.setPromptText(pattern.toLowerCase());
        datePik.getEditor().focusedProperty().addListener(new ChangeListener<Boolean>()//focus on the TextField object of the DatePicker
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue){
                if (newPropertyValue == false){
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat(datePik.getEditor().getText());
                        sdf.setLenient(false);
                        //if not valid, it will throw ParseException
                        Date date = sdf.parse(datePik.getEditor().getText());
                        //System.out.println(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                        datePik.getEditor().setText("");
                    }
                }
            };
        });
    }

    public void setDataDatePicker(TextField textField) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd.MM.yyyy" );
//        try {
//            textField.setTextFormatter(new TextFormatter<>(new DateStringConverter(simpleDateFormat), simpleDateFormat.parse("07.01.1900")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        TextFormatter<String> formatter = new TextFormatter<String>( change -> {
//            change.setText(change.getText().replaceAll("[^a-zA-Z0-9]", ""));
//            return change;
//
//        });
//        textField.setTextFormatter(formatter);
//        UnaryOperator<TextFormatter.Change> numberValidationFormatter = change -> {
//            if(change.getText().matches("\\d{2}\\.\\d{2}\\.\\d{4}")){
//                return change; //if change is a number
//            } else {
//                change.setText(""); //else make no change
//                change.setRange(    //don't remove any selected text either.
//                        change.getRangeStart(),
//                        change.getRangeStart()
//                );
//                return change;
//            }
//        };

        textField.focusedProperty().addListener(new ChangeListener<Boolean>()//focus on the TextField object
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue){
                if (newPropertyValue == false){
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat(textField.getText());
                        sdf.setLenient(false);
                        //if not valid, it will throw ParseException
                        Date date = sdf.parse(textField.getText());
                        //System.out.println(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                        textField.setText("");
                    }
                }
            };
        });

    }



    public String getStringDate(String input) {
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        String formattedDate = null;
       try {
           Date date = parser.parse(input);
           SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH );
           formattedDate = formatter.format(date);
       } catch (ParseException e) {
           e.printStackTrace();
       }
        return formattedDate;
    }

    public void setClearTextField(TextField textField) {
       // if(textField.get)
    }
*/

}

