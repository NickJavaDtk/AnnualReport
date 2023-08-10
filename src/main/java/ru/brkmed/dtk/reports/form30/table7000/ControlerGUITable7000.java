package ru.brkmed.dtk.reports.form30.table7000;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlerGUITable7000 {
    @FXML
    private Button btnExport;

    @FXML
    private Tab tab7000;

    @FXML
    private TabPane tabPane7000;

    @FXML
    private TableView<Table7000> table7000;

    @FXML
    private TableColumn<Table7000, Text> tb1;

    @FXML
    private TableColumn<Table7000, String> tb2;

    @FXML
    private TableColumn<Table7000, Long> tb3;

    @FXML
    private TableColumn<Table7000, Long> tb4;

    @FXML
    private TableColumn<Table7000, Long> tb5;

    @FXML
    private TableColumn<Table7000, Long> tb6;

    @FXML
    private TableColumn<Table7000, Long> tb7;

    @FXML
    private TableColumn<Table7000, Long> tb8;
    Parent parentReport;
    Stage stageReport;
    private static ControlerGUITable7000 guiTable7000;

    @FXML
    void exportXls(ActionEvent event) {

    }

    public ControlerGUITable7000() {
    }

    public ControlerGUITable7000(Parent parentReport, Stage stageReport) {
        this.parentReport = parentReport;
        this.stageReport = stageReport;
    }

    public void createWindowsReport() {
        FXMLLoader loader = new FXMLLoader(  );
        loader.setLocation(getClass().getResource("/table7000.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parentReport = loader.getRoot();
        stageReport = new Stage();
        stageReport.setTitle("Таблица 7000");
        stageReport.initModality(Modality.APPLICATION_MODAL);
        stageReport.setScene(new Scene(parentReport));
        guiTable7000 = new ControlerGUITable7000( parentReport, stageReport );
        setValueTableView();
        stageReport.showAndWait();

    }

    public void setValueTableView() {
        ArrayList<Node> listNodes = ListNodes.getAllNodes(parentReport);
        table7000 = (TableView<Table7000>) listNodes.get(21);
        tb1 = (TableColumn<Table7000, Text>) table7000.getColumns().get(0);
        //tb1.setCellValueFactory(new PropertyValueFactory<Table7000, String>("name"));
        tb1.setPrefWidth(461.0);
        tb1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Table7000, Text>, ObservableValue<Text>>( ) {
            @Override
            public ObservableValue<Text> call(TableColumn.CellDataFeatures<Table7000, Text> table7000String) {
                Text name = new Text(table7000String.getValue().getName());
                name.setWrappingWidth(tb1.getPrefWidth());
                return new ReadOnlyObjectWrapper<>( name );
            }
        });
        tb2 = (TableColumn<Table7000, String>) table7000.getColumns().get(1);
        tb2.setCellValueFactory(new PropertyValueFactory<Table7000, String>("numberString"));
        tb3 = (TableColumn<Table7000, Long>) table7000.getColumns().get(2);
        tb3.setCellValueFactory(new PropertyValueFactory<Table7000, Long>("total"));
        tb4 = (TableColumn<Table7000, Long>) table7000.getColumns().get(3);
        tb4.setCellValueFactory(new PropertyValueFactory<Table7000, Long>("taskPolic"));
        tb5 = (TableColumn<Table7000, Long>) table7000.getColumns().get(4);
        tb5.setCellValueFactory(new PropertyValueFactory<Table7000, Long>("taskHosp"));
        tb6 = (TableColumn<Table7000, Long>) table7000.getColumns().get(5);
        tb6.setCellValueFactory(new PropertyValueFactory<Table7000, Long>("helpPolic"));
        tb7 = (TableColumn<Table7000, Long>) table7000.getColumns().get(6);
        tb7.setCellValueFactory(new PropertyValueFactory<Table7000, Long>("helpHosp"));
        tb8 = (TableColumn<Table7000, Long>) table7000.getColumns().get(7);
        tb8.setCellValueFactory(new PropertyValueFactory<Table7000, Long>("other"));
        List<Table7000> list = new Table7000Dao(  ).getValue();
        ObservableList<Table7000> observableList = FXCollections.observableArrayList(list);
        table7000.getItems().addAll(observableList);

    }

    public Parent getParentReport() {
        return parentReport;
    }
}
