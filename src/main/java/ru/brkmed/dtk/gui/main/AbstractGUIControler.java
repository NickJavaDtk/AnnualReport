package ru.brkmed.dtk.gui.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import ru.brkmed.dtk.dao.mainClasses.entityes.AbstractEntity;
import ru.brkmed.dtk.gui.model.ListNodes;
import ru.brkmed.dtk.gui.model.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public abstract class AbstractGUIControler {
    private Stage stage;
    private Parent parent;

    private Button createButton;

    private Button editButton;

    private Button deleteButton;

    private Button loadButton;

    private Button updateTableView;

    private TableView<? extends AbstractEntity> tableView;

    private Long id;

    private Button presButton;

    private Label findRecord;

    private TextField txtFieldFind;

    private ContextMenu menuUpdateFilter;

    private final String textCreateButton = "Создать";

    private final String textEditButton = "Изменить";

    private final String textDeleteButton = "Удалить";

    private final String textLoadButton = "Загрузить";

    private final String textLabel = "Поиск";

    Double screenSizeWidth = Toolkit.getDefaultToolkit( ).getScreenSize( ).getWidth();




    public AbstractGUIControler(){

    }

    private AbstractGUIControler getChildListButton;


    public void basicWindowTab(TabPane tabPane, String nameTab) {
        Tab newTab = new Tab( nameTab );
        newTab.setClosable(true);
        AnchorPane externAnchorPane = getExternAnchorPane();
        AnchorPane innerAnchorPane = getInnerAnchorPane();
        externAnchorPane.setTopAnchor(innerAnchorPane, 2.0);
        externAnchorPane.setLeftAnchor(innerAnchorPane, 6.0);
        externAnchorPane.setRightAnchor(innerAnchorPane, 25.0);
        createButton = addAnchorPane(textCreateButton, innerAnchorPane);
        createButton.setMinWidth(80.0);
        editButton = addAnchorPane(textEditButton, innerAnchorPane);
        editButton.setLayoutX(86.0);
        deleteButton = addAnchorPane(textDeleteButton, innerAnchorPane);
        deleteButton.setLayoutX(166.0);
        Label findRecord = getLabelFindValue(MainWindow.getMainStage(), textLabel);

        //findRecord.setLayoutY(20.0);
       // findRecord.setMaxWidth(564.0);
        //innerAnchorPane.setRightAnchor(findRecord, 152.0);
        //innerAnchorPane.setTopAnchor(findRecord, 19.0);
       // AnchorPane.setRightAnchor(findRecord, 152.0);
        txtFieldFind = getTextFieldFindValue(MainWindow.getMainStage());
        //txtFieldFind.setLayoutX(580);
        //txtFieldFind.setLayoutY(15.0);
        //innerAnchorPane.setTopAnchor(txtFieldFind, 12.0);
        //innerAnchorPane.setRightAnchor(txtFieldFind, 0.0);
        //updateTableView = getUpdateDataTableView(MainWindow.getMainStage());
        Group groupInnerPane = new Group( createButton, editButton, deleteButton, updateTableView, findRecord, txtFieldFind);
        if (this.getClass().getCanonicalName( ).equals("ru.brkmed.dtk.gui.main.GUIEmployee")) {
            loadButton = addAnchorPane(textLoadButton, innerAnchorPane);
            loadButton.setLayoutX(236.0);
            groupInnerPane.getChildren().add(loadButton);
        }


        innerAnchorPane.getChildren().add(groupInnerPane);
        tableView = getCurrentTableViewAbs(externAnchorPane);
        menuUpdateFilter = getUpdateFilter();
        tableView.setContextMenu(menuUpdateFilter);
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
        innerAnchorPane.prefWidth(716.0);
        innerAnchorPane.setMaxHeight(Region.USE_COMPUTED_SIZE);
        innerAnchorPane.setMinHeight(Region.USE_COMPUTED_SIZE);
        innerAnchorPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
        innerAnchorPane.setMinWidth(Region.USE_COMPUTED_SIZE);
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

    public Label getLabelFindValue(Stage stageMain, String name) {
        Label label = new Label(name);
        label.setLayoutY(20.0);
        Double size = 540.0;
        if (!stageMain.isMaximized()) {
            label.setLayoutX(size);
        } else {
            label.setLayoutX(screenSizeWidth - 202.0);
        }
        stageMain.widthProperty().addListener(new ChangeListener( ) {
            @Override
            public void changed(ObservableValue observableValue, Object oldNumber, Object newNumber) {
//
                if (label.getLayoutX() == 540.0) {
                    label.setLayoutX(screenSizeWidth - 202.0);
                } else {
                    label.setLayoutX(size);
                }
            }
        });


        return label;
    }

    public TextField getTextFieldFindValue(Stage stageMain) {
        TextField textField = new TextField();
        textField.setLayoutY(15.0);
        Double size = 580.0;
        if (!stageMain.isMaximized()) {
            textField.setLayoutX(size);
        } else {
            textField.setLayoutX(screenSizeWidth - 162.0);
        }

        stageMain.widthProperty().addListener(new ChangeListener( ) {
            @Override
            public void changed(ObservableValue observableValue, Object oldNumber, Object newNumber) {
//
                if (textField.getLayoutX() == 580.0 ) {
                    textField.setLayoutX(screenSizeWidth - 162.0);
                } else {
                    textField.setLayoutX(size);
                }
            }
        });
        return textField;

    }

//    public Button getUpdateDataTableView(Stage stageMain) {
//        Button button = new Button();
//        ImageView image = new ImageView(new Image("tst3.png"));
//        button.graphicProperty().setValue(image);
//        button.setTooltip(new Tooltip( "Обновить фильтр" ));
//        button.setLayoutY(0.0);
//        Double size = 490.0;
//        if (!stageMain.isMaximized()) {
//            button.setLayoutX(size);
//        } else {
//            button.setLayoutX(screenSizeWidth - 249.0);
//        }
//
//        stageMain.widthProperty().addListener(new ChangeListener( ) {
//            @Override
//            public void changed(ObservableValue observableValue, Object oldNumber, Object newNumber) {
////
//                if (button.getLayoutX() == 490.0 ) {
//                    button.setLayoutX(screenSizeWidth - 249.0);
//                } else {
//                    button.setLayoutX(size);
//                }
//            }
//        });
//      return button;
//    }

    public ContextMenu getUpdateFilter() {
        MenuItem updateFilter = new MenuItem( "Обновить поиск" );
        ContextMenu contextMenu = new ContextMenu( updateFilter );
        return contextMenu;
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
       // setPresButton(button);
        button.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader( );
            loader.setLocation(getClass( ).getResource(resourceXml));
            try {
                loader.load( );
            } catch (IOException e) {
                e.printStackTrace( );
            }
            parent = loader.getRoot( );
            stage = new Stage( );
            stage.setTitle(nameWindowCreate);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            if (button.getText().equals("Создать")) {
                fillValuesCreateWindow(parent);
                resetFindTextField(menuUpdateFilter);
            }
            if (button.getText( ).equals("Изменить")) {
                fillValuesEditWindow(parent);
                resetFindTextField(menuUpdateFilter);

//                ObservableList<? extends AbstractEntity> observableList = getObservableList();
//                SortedList<? extends AbstractEntity> sortedList = findValueRecord(tableView, observableList, txtFieldFind);
//                tableView.setItems(observableList);


            }
            List<Button> buttonList = new ArrayList<>( );
            setGetChildListButton(addListButton(buttonList, button, stage));
            addEventStage( );
            ArrayList<Node> list = ListNodes.getAllNodes(parent);
            stage.showAndWait( );
        });


    }

    public void getNewDialogWindowDelete(Button button) {
        button.setOnAction(actionEvent -> {
            int n =   JOptionPane.showConfirmDialog(null,
                    "Вы действительно хотите удалить текущую запись", "Подтвердите удаление",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.OK_OPTION) {
                deleteRecord();
                resetFindTextField(getMenuUpdateFilter());
            }
        });


    }


    public abstract void setValueTableView(TableView<? extends AbstractEntity> tableView);

    public abstract void fillValuesCreateWindow(Parent parent);

    public abstract void fillValuesEditWindow(Parent parent);

    public abstract void addEventStage();

    public abstract AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage);

    public abstract SortedList<? extends AbstractEntity> findValueRecord(TableView<? extends AbstractEntity> tableView,
                                                               ObservableList<? extends AbstractEntity> observableList, TextField txtFieldFind);

    public abstract void resetFindTextField(ContextMenu contextMenu);


    public abstract void deleteRecord();

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

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public void setPresButton(Button presButton) {
        this.presButton = presButton;
    }

    public TableView<? extends AbstractEntity> getTableView() {
        return tableView;
    }

    public TextField getTxtFieldFind() {
        return txtFieldFind;
    }

    public ContextMenu getMenuUpdateFilter() {
        return menuUpdateFilter;
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

    public void getDoubleValue(TextField textField) {

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

    public void getIntegerValue11Char(TextField textField) {

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
                if (Pattern.matches("\\d{12}?", ob)) {
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
                if (Pattern.matches("\\d{13}?", ob)) {
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

    public void getDataDatePicker(DatePicker datePik){
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

    public void getIntegerValue(TextField textField) {
        UnaryOperator<TextFormatter.Change> integer = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                if (textField.getText().length() < 12 ) {
                    return change;
                }
            }
            return null;

        };
        textField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integer));
        textField.setText(String.valueOf(integer));

    }




}
