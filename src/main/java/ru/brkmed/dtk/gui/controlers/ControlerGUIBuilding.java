package ru.brkmed.dtk.gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import ru.brkmed.dtk.dao.mainСlasses.entities.references.Building;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlerGUIBuilding implements Initializable {


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

        Group groupInnnerPane = new Group( btnCreateBuilding, btnEditBuilding );
        innerAnchorPane.getChildren().add(groupInnnerPane);

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
        //idBuilding.setCellFactory(new PropertyValueFactory<Building, Long>("Id"));
        TableColumn<Building, String> nameBuilding = new TableColumn<>("Название здания");
        nameBuilding.setPrefWidth(275.0);
        nameBuilding.setCellFactory(new PropertyValueFactory<>("nameBuilding"));
        TableColumn<Building, String> adressBuilding = new TableColumn<>( "Адрес здания" );
        adressBuilding.setPrefWidth(275.0);

        Label countRecordBuilding = new Label( "Количество записей" );
        countRecordBuilding.setLayoutX(14.0);
        countRecordBuilding.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(countRecordBuilding, 5.0);
        externAnchorPane.setLeftAnchor(countRecordBuilding, 14.0);
        externAnchorPane.getChildren().add(countRecordBuilding);

        Label resultBuilding = new Label( "00" );
        resultBuilding.setLayoutX(138.0);
        resultBuilding.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(resultBuilding, 5.0);
        externAnchorPane.getChildren().add(resultBuilding);

        listBuilding.getColumns().add(idBuilding);
        listBuilding.getColumns().add(nameBuilding);
        listBuilding.getColumns().add(adressBuilding);


        externAnchorPane.getChildren().add(listBuilding);
        externAnchorPane.getChildren().add(innerAnchorPane);


        tabPane.getTabs().add(building);
        building.setContent(externAnchorPane);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public ObservableList<Building> getObsBuilding() {
        ObservableList<Building> observableList = FXCollections.observableArrayList();
        observableList.add()
    }
}
