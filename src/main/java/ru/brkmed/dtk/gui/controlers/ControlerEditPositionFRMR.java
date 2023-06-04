package ru.brkmed.dtk.gui.controlers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoPositionFRMR;
import ru.brkmed.dtk.gui.main.AbstractGUIControler;
import ru.brkmed.dtk.gui.main.GUIBuilding;
import ru.brkmed.dtk.gui.main.GUICurrentPositionFRMR;

public class ControlerEditPositionFRMR {

    ControlerDaoPositionFRMR positionFRMR = new ControlerDaoPositionFRMR();
    @FXML
    private Button btnSave;

    @FXML
    private CheckBox chbVisibiliti;

    @FXML
    private Label lblIdFRMR;

    @FXML
    private Label lblIdNsi;

    @FXML
    private Label lblIdParent;

    @FXML
    private Label lblWorkPos;

    @FXML
    void editSaveCurrentPosition(ActionEvent event) {
        Long id = GUICurrentPositionFRMR.getRecordCurrentPositionFRMR( ).getId( );
        Boolean visibility = chbVisibiliti.isSelected();
        positionFRMR.updateCurrentPosition(id, visibility);
        TableView<CurrentPositionFRMR> tableView = GUICurrentPositionFRMR.getGUICurrentPositionFRMR().getTablePositionFRMR();
        SortedList<CurrentPositionFRMR> sortedList = GUICurrentPositionFRMR.getGUICurrentPositionFRMR( ).getSortedList( );

       // ObservableList<CurrentPositionFRMR> observableList = (ObservableList<CurrentPositionFRMR>) absGUI.getObservableList();
        tableView.setItems(sortedList);
        GUICurrentPositionFRMR.getGUICurrentPositionFRMR().getStage( ).close( );

    }
}
