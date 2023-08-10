package ru.brkmed.dtk.gui.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.brkmed.dtk.dao.mainClasses.entityes.AbstractEntity;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoBuilding;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GUIBuilding extends AbstractGUIControler implements Initializable {
//    ObservableList<Building> observableList = null;
//
//
//    List<Building> listIluminatedBuild = null;
//
//    static TableView<Building> getTableBuild;
//
//    static List<Button> buttonList;
//
//    static Long idBuild;
//
//    static Stage getStage;

    //----------------------------------

    private  TableView<Building> tableBuilding;

    public static Button create;

    public static Button edit;

    public Button delete;

    private Button button;

    private List<Button> buttons;

    public static GUIBuilding GUIBuilding;

    public static Building buildRecord;

    private ObservableList<Building> obsBuild;

    private Stage stage;

    private static TextField find;

    private  SortedList<Building> sortedList;

/*


    public void createAddTab(TabPane tabPane) {
        Tab building = new Tab( "Здания" );
        building.setClosable(true);
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

        Button btnCreateBuilding = new Button( "Создать" );
        btnCreateBuilding.setLayoutY(2.0);
        btnCreateBuilding.setPadding(new Insets(10));
        innerAnchorPane.setLeftAnchor(btnCreateBuilding, 0.0);
        innerAnchorPane.setTopAnchor(btnCreateBuilding, 2.0);


        Button btnEditBuilding = new Button( "Изменить" );
        btnEditBuilding.setLayoutY(2.0);
        btnEditBuilding.setLayoutX(71.0);
        btnEditBuilding.setPadding(new Insets(10));
        innerAnchorPane.setLeftAnchor(btnEditBuilding, 0.0);
        innerAnchorPane.setTopAnchor(btnEditBuilding, 2.0);

        Group groupInnnerPane = new Group( btnCreateBuilding, btnEditBuilding);
        innerAnchorPane.getChildren().add(groupInnnerPane);

        TableView<Building> listBuilding = getCurrentTableView(externAnchorPane);
        getTableBuild = listBuilding;

        btnCreateBuilding.setOnAction(event -> {
            buttonList = new ArrayList<>();
            buttonList.add(btnCreateBuilding);
            setButtonList(buttonList);
            FXMLLoader loader = new FXMLLoader(  );
            loader.setLocation(getClass().getResource("/BuildingCreateEditRecord.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage(  );
            stage.setTitle("Добавить здания");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
                @Override
                public void handle(WindowEvent windowEvent) {

                       observableList = getObsBuilding();
                       listBuilding.setItems(observableList);

                }
            });
            stage.showAndWait();


        });

        btnEditBuilding.setOnAction(event -> {
            buttonList = new ArrayList<>();
            buttonList.add(btnEditBuilding);
            setButtonList(buttonList);
            FXMLLoader loader = new FXMLLoader(  );
            loader.setLocation(getClass().getResource("/BuildingCreateEditRecord.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            getStage = new Stage(  );
            getStage.setTitle("Редактировать здание");
            getStage.initModality(Modality.APPLICATION_MODAL);
            getStage.setScene(new Scene(root));
            ArrayList<Node> listNodes = ListNodes.getAllNodes(root );
            TextField nameBuild = (TextField) listNodes.get(3);
            TextField adressBuild = (TextField) listNodes.get(4);
            Button saveOrEdit = (Button) listNodes.get(6);
            Building getBuild = getRecordBuilding(listBuilding);
            nameBuild.setText(getBuild.getNameBuilding());
            adressBuild.setText(getBuild.getAdressBuilding());

            getStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
                @Override
                public void handle(WindowEvent windowEvent) {

                    observableList = getObsBuilding();
                    listBuilding.setItems(observableList);

                }
            });
        getStage.showAndWait();

        });



        Label countRecordBuilding = new Label( "Количество записей" );
        countRecordBuilding.setLayoutX(14.0);
        countRecordBuilding.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(countRecordBuilding, 5.0);
        externAnchorPane.setLeftAnchor(countRecordBuilding, 14.0);
        externAnchorPane.getChildren().add(countRecordBuilding);
        String countRecord = String.valueOf(listBuilding.getItems().size());
        Label resultBuilding = new Label(countRecord);
        resultBuilding.setLayoutX(138.0);
        resultBuilding.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(resultBuilding, 5.0);
        externAnchorPane.getChildren().add(resultBuilding);




        externAnchorPane.getChildren().add(listBuilding);
        externAnchorPane.getChildren().add(innerAnchorPane);


        tabPane.getTabs().add(building);
        building.setContent(externAnchorPane);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }



    public TableView<Building> getCurrentTableView(AnchorPane externAnchorPane) {
        TableView<Building> listBuilding = new TableView<>( );
        listBuilding.setLayoutY(46.0);
        listBuilding.prefHeight(443.0);
        listBuilding.prefWidth(721.0);
        listBuilding.setMaxHeight(Region.USE_COMPUTED_SIZE);
        listBuilding.setMaxWidth(Region.USE_COMPUTED_SIZE);
        listBuilding.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        externAnchorPane.setLeftAnchor(listBuilding, 0.0);
        externAnchorPane.setRightAnchor(listBuilding, 0.0);
        externAnchorPane.setTopAnchor(listBuilding, 46.0);
        externAnchorPane.setBottomAnchor(listBuilding, 18.0);


        TableColumn<Building, Long> idBuilding = new TableColumn<>("ID здания");
        idBuilding.setPrefWidth(45.0);
        idBuilding.setCellValueFactory(new PropertyValueFactory<Building, Long>("Id"));
        TableColumn<Building, String> nameBuilding = new TableColumn<>("Название здания");
        nameBuilding.setPrefWidth(275.0);
        nameBuilding.setCellValueFactory(new PropertyValueFactory<Building, String>("nameBuilding"));
        TableColumn<Building, String> adressBuilding = new TableColumn<>( "Адрес здания" );
        adressBuilding.setPrefWidth(275.0);
        adressBuilding.setCellValueFactory(new PropertyValueFactory<Building, String>("adressBuilding"));

        listBuilding.getColumns().add(idBuilding);
        listBuilding.getColumns().add(nameBuilding);
        listBuilding.getColumns().add(adressBuilding);

        observableList = getObsBuilding();
        listBuilding.setItems(observableList);
        listBuilding.getSelectionModel().select(0);
        return listBuilding;
    }



    public ObservableList<Building> getObsBuilding() {
        ObservableList<Building> observableList = FXCollections.observableArrayList();
        observableList.sorted();
        List<Building> buildingList = new ControlerDaoBuilding().listBuilding();
        for(Building build : buildingList) {
            observableList.add(build);
        }
        return observableList;
    }

    public  Building getRecordBuilding(TableView tableView) {
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
        TablePosition<Building, Long> tbBuildingID = (TablePosition<Building, Long>) tableView.getSelectionModel().getSelectedCells( ).get(0 );
        int rowID = tbBuildingID.getRow( );
        Building buildingID = (Building) tableView.getItems().get(rowID);
        idBuild = buildingID.getId();
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


        return buildingID;
    }

    public List<Building> getListIluminatedBuild() {
        return listIluminatedBuild;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }

*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//
    }

    public void alternativeTab(TabPane tabPane) {
        super.basicWindowTab(tabPane, "Новый путь зданий");
        create = super.getCreateButton();
        super.getNewDialogWindow(create,
                "/BuildingCreateEditRecord.fxml", "Добавить здание");
        edit = super.getEditButton();
        super.getNewDialogWindow(edit,
                "/BuildingCreateEditRecord.fxml", "Изменить здание");
       // setButton(super.getPresButton());
        delete = super.getDeleteButton();
        getNewDialogWindowDelete(delete);


    }


    public GUIBuilding() {
    }

    public GUIBuilding(List<Button> buttons, TableView<Building> tableBuilding, Stage stage) {
        this.buttons = buttons;
        this.tableBuilding = tableBuilding;
        this.stage = stage;
    }



    @Override
    public ObservableList<? extends AbstractEntity> getObservableList() {
        ObservableList<Building> observableList = FXCollections.observableArrayList();
        observableList.sorted();
        List<Building> buildingList = new ControlerDaoBuilding().listBuilding();
        observableList.addAll(buildingList);
        return observableList;
    }

    @Override
    public void setValueTableView(TableView<? extends AbstractEntity> tableView) {
        tableBuilding = (TableView<Building>) super.getTableView();
        TableColumn<Building, Long> idBuilding = new TableColumn<>("ID здания");
        idBuilding.setPrefWidth(45.0);
        idBuilding.setCellValueFactory(new PropertyValueFactory<Building, Long>("Id"));
        TableColumn<Building, String> nameBuilding = new TableColumn<>("Название здания");
        nameBuilding.setPrefWidth(275.0);
        nameBuilding.setCellValueFactory(new PropertyValueFactory<Building, String>("nameBuilding"));
        TableColumn<Building, String> adressBuilding = new TableColumn<>( "Адрес здания" );
        adressBuilding.setPrefWidth(675.0);
        adressBuilding.setCellValueFactory(new PropertyValueFactory<Building, String>("adressBuilding"));
        tableBuilding.getColumns( ).add(idBuilding);
        tableBuilding.getColumns( ).add(nameBuilding);
        tableBuilding.getColumns( ).add(adressBuilding);
        obsBuild = (ObservableList<Building>) getObservableList( );
        find = super.getTxtFieldFind();
        sortedList = (SortedList<Building>) findValueRecord(tableBuilding, obsBuild, find);
        tableBuilding.setItems(sortedList);
       // tableBuilding.getItems().addAll(obsBuild);
        tableBuilding.getSelectionModel( ).select(0);

    }

    @Override
    public void fillValuesCreateWindow(Parent parent) {

    }

    @Override
    public void fillValuesEditWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent );
        TextField nameBuild = (TextField) listNodes.get(3);
        TextField adressBuild = (TextField) listNodes.get(4);
        Button saveOrEdit = (Button) listNodes.get(6);
        buildRecord = (Building) super.getRecordTableView();
        nameBuild.setText(buildRecord.getNameBuilding());
        adressBuild.setText(buildRecord.getAdressBuilding());
    }

    @Override
    public void addEventStage() {

        super.getStage( ).addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
               tableBuilding.setItems((ObservableList<Building>) getObservableList( ));
               getCountRecordLabel().setText(String.valueOf(tableBuilding.getItems().size()));
            }
        });
    }

    @Override
    public AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage) {
        buttonList.add(button);
        tableBuilding.setItems((ObservableList<Building>) getObservableList( ));
        GUIBuilding = new GUIBuilding( buttonList, tableBuilding, stage );
        return GUIBuilding;
    }

    @Override
    public SortedList<? extends AbstractEntity> findValueRecord(TableView<? extends AbstractEntity> tableView, ObservableList<? extends AbstractEntity> observableList, TextField txtFieldFind) {
        FilteredList<Building> filterData = new FilteredList<>((ObservableList<Building>) observableList, b -> true);
        txtFieldFind.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(building -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(building.getId()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (building.getNameBuilding().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (building.getAdressBuilding().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });


        });
        SortedList<Building> sortedData = new SortedList<>(filterData);
        TableView<Building> tableBuild = (TableView<Building>) tableView;
        sortedData.comparatorProperty().bind(tableBuild.comparatorProperty());
        return sortedData;
    }

    @Override
    public void resetFindTextField(ContextMenu contextMenu) {
        MenuItem menuItem = contextMenu.getItems( ).get(0);
        menuItem.setOnAction(actionEvent -> {
            ObservableList<Building> observableList = FXCollections.observableArrayList();
            List<Building> listBuilding = new ControlerDaoBuilding().listBuilding();
            observableList.addAll(listBuilding);
            SortedList<Building> sortedListField = (SortedList<Building>) findValueRecord(tableBuilding, observableList, getFind());
            tableBuilding.setItems(sortedListField);
        });
    }


    @Override
    public void deleteRecord() {
        Building building = (Building) super.getRecordTableView();
        ControlerDaoBuilding daoBuilding = new ControlerDaoBuilding();
        daoBuilding.deleteBuild(building.getId( ));
        tableBuilding.setItems((ObservableList<Building>) getObservableList( ));
        getCountRecordLabel().setText(String.valueOf(tableBuilding.getItems().size()));

    }




    public void setButton(Button button) {
        this.button = button;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public TableView<Building> getTableBuilding() {
        return tableBuilding;
    }


    @Override
    public Stage getStage() {
        return stage;
    }

    public  SortedList<Building> getSortedList() {
        return sortedList;
    }

    public static TextField getFind() {
        return find;
    }
}
