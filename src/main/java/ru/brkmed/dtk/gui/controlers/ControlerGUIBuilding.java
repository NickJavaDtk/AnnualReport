package ru.brkmed.dtk.gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.brkmed.dtk.dao.mainСlasses.entities.Building;
import ru.brkmed.dtk.dao.mainСlasses.references.controler.ControlerDaoBuilding;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerGUIBuilding implements Initializable {
    ObservableList<Building> observableList = null;


    List<Building> listIluminatedBuild = null;

    static TableView<Building> getTableBuild;

    static List<Button> buttonList;

    static Long idBuild;

    static Stage getStage;



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

}
