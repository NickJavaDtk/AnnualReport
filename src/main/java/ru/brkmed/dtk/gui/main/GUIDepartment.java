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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.apache.poi.ss.formula.functions.T;
import ru.brkmed.dtk.dao.mainClasses.entityes.*;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoBuilding;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDepartment;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoEmployee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoPositionFRMR;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIDepartment extends AbstractGUIControler{

    private  TableView<Department> tableDepartment;

    private ObservableList<Department> obsDepartment;

    private static Department departmentRecord;

    private static Map<Long, Building> mapChcBoxBuild;

    private static GUIDepartment GUIDepartment;

    private List<Button> buttons;

    private Stage stage;


    public GUIDepartment() {
    }

    public GUIDepartment(List<Button> buttons, TableView<Department> tableDepartment,  Stage stage) {
        this.tableDepartment = tableDepartment;
        this.buttons = buttons;
        this.stage = stage;
    }

    @Override
    public ObservableList<? extends AbstractEntity> getObservableList() {
        ObservableList<Department> observableList = FXCollections.observableArrayList();
        observableList.sorted();
        List<Department> departmentList =  new ControlerDaoDepartment().listDepartments();
        observableList.addAll(departmentList);
        return observableList;

    }

    @Override
    public void setValueTableView(TableView<? extends AbstractEntity> tableView) {
        tableDepartment = (TableView<Department>) super.getTableView();
        TableColumn<Department, Long> idDepartment = new TableColumn<>("ID Подразделения");
        idDepartment.setPrefWidth(275.0);
        idDepartment.setCellValueFactory(new PropertyValueFactory<Department, Long>("Id"));
        TableColumn<Department, String> nameDepartment = new TableColumn<>("Подразделение");
        nameDepartment.setPrefWidth(475.0);
        nameDepartment.setCellValueFactory(new PropertyValueFactory<Department, String>("nameDepartment"));
        TableColumn<Department, String> build = new TableColumn<>( "Здание" );
        build.setPrefWidth(475.0);
        build.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Department, String>, ObservableValue<String>>( ) {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Department, String> stringDepartment) {
                return new ReadOnlyObjectWrapper<>( stringDepartment.getValue().getBuilding().getNameBuilding() );
            }
        });
        TableColumn<Department, String> typeHelp = new TableColumn<>("Тип помощи");
        typeHelp.setPrefWidth(475.0);
        typeHelp.setCellValueFactory(new PropertyValueFactory<Department, String>("typeHelp"));
        TableColumn<Department, String> typeTask = new TableColumn<>("Тип задач");
        typeTask.setPrefWidth(475.0);
        typeTask.setCellValueFactory(new PropertyValueFactory<Department, String>("typeTask"));
        TableColumn<Department, Boolean> rural = new TableColumn<>( "Сельская местность");
        rural.setPrefWidth(275);
        rural.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Department, Boolean>, ObservableValue<Boolean>>( ) {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Department, Boolean> departmentBoolean) {
                Department department = departmentBoolean.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty( department.isRural() );
                return booleanProperty;
            }
        });
        rural.setCellFactory(new Callback<TableColumn<Department, Boolean>, TableCell<Department, Boolean>>( ) {
            @Override
            public TableCell<Department, Boolean> call(TableColumn<Department, Boolean> departmentTableColumn) {
                CheckBoxTableCell<Department, Boolean> cell = new CheckBoxTableCell<>(  );
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        TableColumn<Department, Boolean> fap = new TableColumn<>( "ФАП");
        fap.setPrefWidth(275);
        fap.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Department, Boolean>, ObservableValue<Boolean>>( ) {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Department, Boolean> departmentBoolean) {
                Department department = departmentBoolean.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty( department.isFap() );
                return booleanProperty;
            }
        });
        fap.setCellFactory(new Callback<TableColumn<Department, Boolean>, TableCell<Department, Boolean>>( ) {
            @Override
            public TableCell<Department, Boolean> call(TableColumn<Department, Boolean> departmentTableColumn) {
                CheckBoxTableCell<Department, Boolean> cell = new CheckBoxTableCell<>(  );
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        TableColumn<Department, Boolean> statist = new TableColumn<>( "Кабинет статистики");
        statist.setPrefWidth(275);
        statist.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Department, Boolean>, ObservableValue<Boolean>>( ) {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Department, Boolean> departmentBoolean) {
                Department department = departmentBoolean.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty( department.isStatist() );
                return booleanProperty;
            }
        });
        statist.setCellFactory(new Callback<TableColumn<Department, Boolean>, TableCell<Department, Boolean>>( ) {
            @Override
            public TableCell<Department, Boolean> call(TableColumn<Department, Boolean> departmentTableColumn) {
                CheckBoxTableCell<Department, Boolean> cell = new CheckBoxTableCell<>(  );
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        tableDepartment.getColumns().addAll(idDepartment, nameDepartment, build, typeHelp, typeTask, rural, fap, statist);
        obsDepartment = (ObservableList<Department>) getObservableList();
        TextField find = super.getTxtFieldFind();
        SortedList<Department> sortedList = (SortedList<Department>) findValueRecord(tableDepartment, obsDepartment, find);
        tableDepartment.setItems(sortedList);
        tableDepartment.getSelectionModel().select(0);
    }

    @Override
    public void fillValuesCreateWindow(Parent parent) {
       ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
       ChoiceBox<String> nameBuild = (ChoiceBox<String>) listNodes.get(3);
       nameBuild.getItems().addAll(getObsBuildDepartment());
       ChoiceBox<String> typeHelp = (ChoiceBox<String>) listNodes.get(14);
       typeHelp.getItems().addAll(getTypeHelp());
       ChoiceBox<String> typeTask = (ChoiceBox<String>) listNodes.get(5);
       typeTask.getItems().addAll(getTypeTask());

    }

    @Override
    public void fillValuesEditWindow(Parent parent) {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parent);
        TextField txtNameDepartment = (TextField) listNodes.get(1);
        ChoiceBox<String> nameBuild = (ChoiceBox<String>) listNodes.get(3);
        ChoiceBox<String> typeHelp = (ChoiceBox<String>) listNodes.get(14);
        ChoiceBox<String> typeTask = (ChoiceBox<String>) listNodes.get(5);
        CheckBox rural = (CheckBox) listNodes.get(7);
        CheckBox fap = (CheckBox) listNodes.get(8);
        CheckBox statist = (CheckBox) listNodes.get(9);

        departmentRecord = (Department) super.getRecordTableView();
        txtNameDepartment.setText(departmentRecord.getNameDepartment());
        nameBuild.setValue(departmentRecord.getBuilding().getNameBuilding());
        nameBuild.getItems().addAll(getObsBuildDepartment());
        typeHelp.setValue(departmentRecord.getTypeHelp());
        typeHelp.getItems().addAll(getTypeHelp());
        typeTask.setValue(departmentRecord.getTypeTask());
        typeTask.getItems().addAll(getTypeTask());
        rural.setSelected(departmentRecord.isRural( ));
        fap.setSelected(departmentRecord.isFap( ));
        statist.setSelected(departmentRecord.isStatist( ));


    }

    @Override
    public void addEventStage() {
        super.getStage( ).addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>( ) {
            @Override
            public void handle(WindowEvent windowEvent) {
                tableDepartment.setItems((ObservableList<Department>) getObservableList( ));
                getCountRecordLabel().setText(String.valueOf(tableDepartment.getItems().size()));
            }
        });

    }

    @Override
    public AbstractGUIControler addListButton(List<Button> buttonList, Button button, Stage stage) {
        buttonList.add(button);
        tableDepartment.setItems((ObservableList<Department>) getObservableList( ));
        GUIDepartment = new GUIDepartment( buttonList, tableDepartment, stage );
        return GUIDepartment;
    }

    @Override
    public SortedList<? extends AbstractEntity> findValueRecord(TableView<? extends AbstractEntity> tableView, ObservableList<? extends AbstractEntity> observableList, TextField txtFieldFind) {
        FilteredList<Department> filterData = new FilteredList<>((ObservableList<Department>) observableList, b -> true);
        txtFieldFind.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(department -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(department.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (department.getNameDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (department.getBuilding().getNameBuilding().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (department.getTypeHelp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (department.getTypeTask().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });


        });
        SortedList<Department> sortedData = new SortedList<>(filterData);
        TableView<Department> tableDepartament = (TableView<Department>) tableView;
        sortedData.comparatorProperty().bind(tableDepartament.comparatorProperty());
        return sortedData;
    }

    @Override
    public void resetFindTextField(ContextMenu contextMenu) {
        MenuItem menuItem = contextMenu.getItems( ).get(0);
        menuItem.setOnAction(actionEvent -> {
            ObservableList<Department> observableList = FXCollections.observableArrayList();
            List<Department> listDepartments = new ControlerDaoDepartment().listDepartments();
            observableList.addAll(listDepartments);
            SortedList<Department> sortedListField = (SortedList<Department>) findValueRecord(tableDepartment, observableList, getTxtFieldFind());
            tableDepartment.setItems(sortedListField);
        });

    }

    @Override
    public void deleteRecord() {
        Department department = (Department) super.getRecordTableView();
        ControlerDaoDepartment controlerDaoDepartment = new ControlerDaoDepartment();
        controlerDaoDepartment.deleteDepartments(department.getId());
        tableDepartment.setItems((ObservableList<Department>) getObservableList( ));
        getCountRecordLabel().setText(String.valueOf(tableDepartment.getItems().size()));

    }

    public void alternativeTab(TabPane tabPane) {
        super.basicWindowTab(tabPane, "Подразделения");
        Button create = super.getCreateButton();
        super.getNewDialogWindow(create,
                "/DepartmentCreateEditRecord.fxml", "Добавить подразделение");
        Button edit = super.getEditButton();
        super.getNewDialogWindow(edit,
                "/DepartmentCreateEditRecord.fxml", "Изменить подразделение");
        Button delete = super.getDeleteButton();
        getNewDialogWindowDelete(delete);


    }

    public ObservableList<String> getObsBuildDepartment() {
        ObservableList<String> obsString = FXCollections.observableArrayList();
        ControlerDaoBuilding daoBuilding = new ControlerDaoBuilding();
        List<Building> buildingList = daoBuilding.listBuilding();
        mapChcBoxBuild = new HashMap<>();
        for(Building build : buildingList) {
            mapChcBoxBuild.put(build.getId(), build);
            obsString.add(build.getNameBuilding());
        }
        return obsString;
    }

    public String[] getTypeTask() {
        String[] array = new String[] {"Административно-хозяйственная деятельность", "Автоматизация лечебного процесса"};
        return array;
    }


    public String[] getTypeHelp() {
        String[] array = new String[] {"Амбулаторно-поликлиническая", "Стационарная"};
        return array;
    }

    public TableView<Department> getTableDepartment() {
        return tableDepartment;
    }

    public static Department getDepartmentRecord() {
        return departmentRecord;
    }

    public static Map<Long, Building> getMapChcBoxBuild() {
        return mapChcBoxBuild;
    }

    public static GUIDepartment getGUIDepartment() {
        return GUIDepartment;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
