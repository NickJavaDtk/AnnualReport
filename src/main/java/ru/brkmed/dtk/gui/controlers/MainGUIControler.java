package ru.brkmed.dtk.gui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class MainGUIControler {
    @FXML
    private Button createCabinet;

    @FXML
    private Button editCabinet;

    @FXML
    private Button loadCabinet;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private MenuItem menuItemBuilding;

    @FXML
    private MenuItem menuItemEmploee;

    @FXML
    private MenuItem menuItemPM;

    @FXML
    private MenuItem menuItemPrinter;

    @FXML
    private MenuItem menuItemUnit;

    @FXML
    private Tab tabCabinet;

    @FXML
    void createBuilding(ActionEvent event) {
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

        TableView listBuilding = new TableView<>();
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

        TableColumn idBuilding = new TableColumn<>( "ID здания" );
        idBuilding.setPrefWidth(45.0);
        TableColumn nameBuilding = new TableColumn<>( "Название здания" );
        nameBuilding.setPrefWidth(275.0);
        TableColumn adressBuilding = new TableColumn<>( "Адрес здания" );
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







       // Group groupPane = new Group(innerAnchorPane, externAnchorPane );

        mainTabPane.getTabs().add(building);
        building.setContent(externAnchorPane);




    }

    @FXML
    void createEmploee(ActionEvent event) {

    }

    @FXML
    void createPM(ActionEvent event) {

    }

    @FXML
    void createPrinter(ActionEvent event) {

    }

    @FXML
    void createUnit(ActionEvent event) {

    }



}
