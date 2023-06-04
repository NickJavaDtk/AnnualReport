package ru.brkmed.dtk.gui.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import ru.brkmed.dtk.dao.mainClasses.entityes.*;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoConnection;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoPositionFRMR;
import ru.brkmed.dtk.gui.controlers.ControlerLoadPositionFRMR;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.util.ArrayList;
import java.util.List;

public class GUICurrentPositionFRMR extends AbstractGUIControler {


    private TableView<CurrentPositionFRMR> tablePositionFRMR;

    private ObservableList<CurrentPositionFRMR> obsPositionFRMR;

    private static CurrentPositionFRMR recordCurrentPositionFRMR;


    private static GUICurrentPositionFRMR GUICurrentPositionFRMR;

    private List<Button> buttons;

    private static SortedList<CurrentPositionFRMR> sortedList;

    private Stage stage;


    public GUICurrentPositionFRMR() {
    }

    public GUICurrentPositionFRMR(List<Button> buttons, TableView<CurrentPositionFRMR> tablePositionFRMR, Stage stage) {
        this.buttons = buttons;
        this.tablePositionFRMR = tablePositionFRMR;
        this.stage = stage;
    }

    @Override
    public ObservableList<? extends AbstractEntity> getObservableList() {
        ObservableList<CurrentPositionFRMR> observableList = FXCollections.observableArrayList( );
        observableList.sorted( );
        List<CurrentPositionFRMR> currentList = new ControlerDaoPositionFRMR( ).listCurrentPositionFRMR( );
        observableList.addAll(currentList);
        return observableList;
    }

    @Override
    public void setValueTableView(TableView<? extends AbstractEntity> tableView) {
        tablePositionFRMR = (TableView<CurrentPositionFRMR>) super.getTableView( );
        TableColumn<CurrentPositionFRMR, Long> id = new TableColumn<>("ID записи");
        id.setPrefWidth(85.0);
        id.setCellValueFactory(new PropertyValueFactory<CurrentPositionFRMR, Long>("Id"));
        TableColumn<CurrentPositionFRMR, Integer> idNsi = new TableColumn<>("ID НСИ");
        idNsi.setPrefWidth(85.0);
        idNsi.setCellValueFactory(new PropertyValueFactory<CurrentPositionFRMR, Integer>("idNsi"));
        TableColumn<CurrentPositionFRMR, Integer> parentId = new TableColumn<>("ID Должности");
        parentId.setPrefWidth(85.0);
        parentId.setCellValueFactory(new PropertyValueFactory<CurrentPositionFRMR, Integer>("parentId"));
        TableColumn<CurrentPositionFRMR, String> name = new TableColumn<>("Должность");
        name.setPrefWidth(755.0);
        name.setCellValueFactory(new PropertyValueFactory<CurrentPositionFRMR, String>("workPosition"));
        TableColumn<CurrentPositionFRMR, Boolean> visibility = new TableColumn<>("Видимость");
        visibility.setPrefWidth(85.0);
 //       visibility.setCellValueFactory(new PropertyValueFactory<CurrentPositionFRMR, Boolean>("visibility"));
//        visibility.setCellValueFactory(param -> param.getValue().isVisibility());
//        CheckBoxTableCell.forTableColumn();
//        //visibility.setCellFactory(CheckBoxTableCell.forTableColumn(visibility));
//        visibility.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrentPositionFRMR, Boolean>, ObservableValue<Boolean>>( ) {
//            @Override
//            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<CurrentPositionFRMR, Boolean> current) {
//                Boolean bool = current.getValue().isVisibility();
//                return new ReadOnlyObjectWrapper<>( bool );
//            }
//        });
//       // CheckBoxTableCell.forTableColumn(visibility);
//        //visibility.setCellFactory(CheckBoxTableCell.forTableColumn(visibility));

       visibility.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrentPositionFRMR, Boolean>, ObservableValue<Boolean>>( ) {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<CurrentPositionFRMR, Boolean> employeeCurrent) {
                CurrentPositionFRMR currentPositionFRMR = employeeCurrent.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty( currentPositionFRMR.isVisibility() );
                return booleanProperty;
            }
        });
        visibility.setCellFactory(new Callback<TableColumn<CurrentPositionFRMR, Boolean>, TableCell<CurrentPositionFRMR, Boolean>>( ) {
            @Override
            public TableCell<CurrentPositionFRMR, Boolean> call(TableColumn<CurrentPositionFRMR, Boolean> employeeTableColumn) {
                CheckBoxTableCell<CurrentPositionFRMR, Boolean> cell = new CheckBoxTableCell<>(  );
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });


        TableColumn<CurrentPositionFRMR, Integer> idFRMR = new TableColumn<>("ID ФРМР");
        idFRMR.setPrefWidth(85.0);
        idFRMR.setCellValueFactory(new PropertyValueFactory<CurrentPositionFRMR, Integer>("med"));
        tablePositionFRMR.getColumns( ).addAll(id, idNsi, parentId, name, visibility, idFRMR);
        obsPositionFRMR = (ObservableList<CurrentPositionFRMR>) getObservableList( );
        TextField find = super.getTxtFieldFind( );
        sortedList = (SortedList<CurrentPositionFRMR>) findValueRecord(tablePositionFRMR, obsPositionFRMR, find);
        tablePositionFRMR.setItems(sortedList);
        tablePositionFRMR.getSelectionModel( ).select(0);
        //  tablePositionFRMR.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    }

    @Override
    public void fillValuesCreateWindow(Parent parent) {


    }

    @Override
    public void fillValuesEditWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        Label idNSI = (Label) listNodes.get(2);
        Label idCurrentPos = (Label) listNodes.get(5);
        CheckBox chcBox = (CheckBox) listNodes.get(7);
        Label idFRMR = (Label) listNodes.get(10);
        Label currentPosition = (Label) listNodes.get(13);
        recordCurrentPositionFRMR = (CurrentPositionFRMR) super.getRecordTableView( );
        idNSI.setText(String.valueOf(recordCurrentPositionFRMR.getIdNsi( )));
        idCurrentPos.setText(String.valueOf(recordCurrentPositionFRMR.getParentId( )));
        chcBox.setSelected(recordCurrentPositionFRMR.isVisibility( ));
        idFRMR.setText(String.valueOf(recordCurrentPositionFRMR.getMed( )));
        currentPosition.setText(recordCurrentPositionFRMR.getWorkPosition( ));


    }

    @Override
    public void addEventStage() {
        super.getStage( ).addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
                tablePositionFRMR.setItems((ObservableList<CurrentPositionFRMR>) getObservableList( ));
            }
        });

    }

    @Override
    public AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage) {
        buttonList.add(button);
        tablePositionFRMR.setItems((ObservableList<CurrentPositionFRMR>) getObservableList( ));
        GUICurrentPositionFRMR = new GUICurrentPositionFRMR(buttonList, tablePositionFRMR, stage);
        return GUICurrentPositionFRMR;
    }

    @Override
    public SortedList<? extends AbstractEntity> findValueRecord(TableView<? extends AbstractEntity> tableView, ObservableList<? extends AbstractEntity> observableList, TextField txtFieldFind) {
        FilteredList<CurrentPositionFRMR> filterData = new FilteredList<>((ObservableList<CurrentPositionFRMR>) observableList, b -> true);
        txtFieldFind.textProperty( ).addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(current -> {
                if (newValue == null || newValue.isEmpty( )) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase( );
                if (String.valueOf(current.getId( )).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(current.getIdNsi( )).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(current.getParentId( )).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (current.getWorkPosition( ).toLowerCase( ).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(current.isVisibility( )).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(current.getMed( )).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });


        });
        SortedList<CurrentPositionFRMR> sortedData = new SortedList<>(filterData);
        TableView<CurrentPositionFRMR> tableBuild = (TableView<CurrentPositionFRMR>) tableView;
        sortedData.comparatorProperty( ).bind(tableBuild.comparatorProperty( ));
        return sortedData;
    }

    @Override
    public void resetFindTextField(ContextMenu contextMenu) {
        MenuItem menuItem = contextMenu.getItems( ).get(0);
        menuItem.setOnAction(actionEvent -> {
            ObservableList<CurrentPositionFRMR> observableList = FXCollections.observableArrayList();
            List<CurrentPositionFRMR> listCurrentPositionFRMR = new ControlerDaoPositionFRMR().listCurrentPositionFRMR();
            observableList.addAll(listCurrentPositionFRMR);
            SortedList<CurrentPositionFRMR> sortedListField = (SortedList<CurrentPositionFRMR>) findValueRecord(tablePositionFRMR, observableList, getTxtFieldFind());
            tablePositionFRMR.setItems(sortedListField);
        });
    }

    @Override
    public void deleteRecord() {

    }




    public void alternativeTab(TabPane tabPane) {
        super.basicWindowTab(tabPane, "Справочник должностей");
        Button load = super.getCreateButton( );
        load.setText("Загрузить");
        super.getNewDialogWindow(load,
                "/LoadNSI.fxml", "Загрузка НСИ");
        Button edit = super.getEditButton( );
        super.getNewDialogWindow(edit,
                "/CurrentPositionEditRecord.fxml", "Редактировать должность");
        // setButton(super.getPresButton());
        Button delete = super.getDeleteButton( );
        getNewDialogWindowDelete(delete);

    }

    public static ru.brkmed.dtk.gui.main.GUICurrentPositionFRMR getGUICurrentPositionFRMR() {
        return GUICurrentPositionFRMR;
    }

    public static CurrentPositionFRMR getRecordCurrentPositionFRMR() {
        return recordCurrentPositionFRMR;
    }

    public TableView<CurrentPositionFRMR> getTablePositionFRMR() {
        return tablePositionFRMR;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public SortedList<CurrentPositionFRMR> getSortedList() {
        return sortedList;
    }
}
