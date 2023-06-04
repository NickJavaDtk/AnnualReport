package ru.brkmed.dtk.gui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.brkmed.dtk.gui.main.GUIBuilding;
import ru.brkmed.dtk.gui.main.GUIConnections;
import ru.brkmed.dtk.gui.main.GUICurrentPositionFRMR;
import ru.brkmed.dtk.gui.main.GUIEmployee;

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
    void createBuilding(ActionEvent event) {
        GUIBuilding guiBuilding = new GUIBuilding();
        //guiBuilding.createAddTab(mainTabPane);
       // ControlerGUIBuilding gdd = new AbstractGUIControler(  )
        guiBuilding.alternativeTab(mainTabPane);


    }

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
