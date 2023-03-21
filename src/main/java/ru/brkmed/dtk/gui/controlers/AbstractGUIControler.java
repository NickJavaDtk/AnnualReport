package ru.brkmed.dtk.gui.controlers;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.brkmed.dtk.dao.mainClasses.entityes.AbstractEntity;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGUIControler {
    private Stage stage;
    private Parent parent;

    private Button createButton;

    private Button editButton;

    private TableView<? extends AbstractEntity> tableView;

    private Long id;

    private Button presButton;


    public AbstractGUIControler(){

    }

    private AbstractGUIControler getChildListButton;

    public void basicWindowTab(TabPane tabPane, String nameTab) {
        Tab newTab = new Tab( nameTab );
        newTab.setClosable(true);
        AnchorPane externAnchorPane = getExternAnchorPane();
        AnchorPane innerAnchorPane = getInnerAnchorPane();
        createButton = addAnchorPane("Создать", innerAnchorPane);
        editButton = addAnchorPane("Изменить", innerAnchorPane);
        editButton.setLayoutX(71.0);
        Group groupInnerPane = new Group( createButton, editButton);
        innerAnchorPane.getChildren().add(groupInnerPane);
        tableView = getCurrentTableViewAbs(externAnchorPane);
        setValueTableView(tableView);

        Label countRecordBuilding = new Label( "Количество записей" );
        countRecordBuilding.setLayoutX(14.0);
        countRecordBuilding.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(countRecordBuilding, 5.0);
        externAnchorPane.setLeftAnchor(countRecordBuilding, 14.0);
        externAnchorPane.getChildren().add(countRecordBuilding);
        String countRecord = String.valueOf(tableView.getItems().size());
        Label resultBuilding = new Label(countRecord);
        resultBuilding.setLayoutX(138.0);
        resultBuilding.setLayoutY(485.0);
        externAnchorPane.setBottomAnchor(resultBuilding, 5.0);
        externAnchorPane.getChildren().add(resultBuilding);




        externAnchorPane.getChildren().add(tableView);
        externAnchorPane.getChildren().add(innerAnchorPane);


        tabPane.getTabs().add(newTab);
        newTab.setContent(externAnchorPane);


    }

    public AnchorPane getExternAnchorPane() {
        AnchorPane externAnchorPane = new AnchorPane();
        externAnchorPane.setPrefWidth(747.0);
        externAnchorPane.setPrefHeight(507.0);
        externAnchorPane.setMaxHeight(Region.USE_COMPUTED_SIZE);
        externAnchorPane.setMinHeight(Region.USE_COMPUTED_SIZE);
        externAnchorPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
        externAnchorPane.setMinWidth(Region.USE_COMPUTED_SIZE);
        externAnchorPane.minHeight(0.0);
        externAnchorPane.minWidth(0.0);
        return externAnchorPane;
    }

    public AnchorPane getInnerAnchorPane() {
        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setLayoutX(6.0);
        innerAnchorPane.prefHeight(44.0);
        innerAnchorPane.prefWidth(264.0);
        return innerAnchorPane;
    }

    public Button addAnchorPane(String nameButton, AnchorPane innerAnchorPane) {
        Button button = new Button( nameButton );
        button.setLayoutY(2.0);
        button.setPadding(new Insets(10));
        innerAnchorPane.setLeftAnchor(button, 0.0);
        innerAnchorPane.setTopAnchor(button, 2.0);
        return button;
    }

    public abstract ObservableList<? extends AbstractEntity> getObservableList();

    public  TableView<? extends AbstractEntity> getCurrentTableViewAbs(AnchorPane externAnchorPane) {
        TableView<? extends  AbstractEntity> tableView = new TableView<>( );
        tableView.setLayoutY(46.0);
        tableView.prefHeight(443.0);
        tableView.prefWidth(721.0);
        tableView.setMaxHeight(Region.USE_COMPUTED_SIZE);
        tableView.setMaxWidth(Region.USE_COMPUTED_SIZE);
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        externAnchorPane.setLeftAnchor(tableView, 0.0);
        externAnchorPane.setRightAnchor(tableView, 0.0);
        externAnchorPane.setTopAnchor(tableView, 46.0);
        externAnchorPane.setBottomAnchor(tableView, 18.0);
        return tableView;
    }

    public void getNewDialogWindow(Button button, String resourceXml, String nameWindowCreate) {
        setPresButton(button);
        button.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(  );
            loader.setLocation(getClass().getResource(resourceXml));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            parent = loader.getRoot();
            stage = new Stage(  );
            stage.setTitle(nameWindowCreate);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(parent));
            if(button.getText().equals("Изменить")) {
                fillValuesEditWindow(parent);
            }
            List<Button> buttonList = new ArrayList<>();
            setGetChildListButton(addListButton(buttonList, button, stage));
            addEventStage();
            ArrayList<Node> list = ListNodes.getAllNodes(parent);
            stage.showAndWait();
        });


    }


    public abstract void setValueTableView(TableView<? extends AbstractEntity> tableView);

    public abstract void fillValuesEditWindow(Parent parent);

    public abstract void addEventStage();

    public abstract AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage);

    public Button getPresButton() {
        return presButton;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public AbstractGUIControler getGetChildListButton() {
        return getChildListButton;
    }

    public void setGetChildListButton(AbstractGUIControler getChildListButton) {
        this.getChildListButton = getChildListButton;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setPresButton(Button presButton) {
        this.presButton = presButton;
    }

    public TableView<? extends AbstractEntity> getTableView() {
        return tableView;
    }

    public  AbstractEntity getRecordTableView() {
        TableView<? extends AbstractEntity> tableAbs = getTableView();
        TablePosition<? extends AbstractEntity, Long> tpAbs = tableAbs.getSelectionModel().getSelectedCells( ).get(0);
        int rowID = tpAbs.getRow();
        AbstractEntity abstractEntity = tableAbs.getItems().get(rowID);
        id = abstractEntity.getId( );
        return abstractEntity;
    }

    public Long getId() {
        return id;
    }
}
