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
    private MenuItem menuItemConnections;

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
        ControlerGUIBuilding guiBuilding = new ControlerGUIBuilding();
        //guiBuilding.createAddTab(mainTabPane);
       // ControlerGUIBuilding gdd = new AbstractGUIControler(  )
        guiBuilding.alternativeTab(mainTabPane);


    }

    public void createConnections(ActionEvent actionEvent) {
        ControlerGUIConnections guiConnections = new ControlerGUIConnections();
        guiConnections.createAddTab(mainTabPane);
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

    public TabPane getMainTabPane() {
        return mainTabPane;
    }


}
