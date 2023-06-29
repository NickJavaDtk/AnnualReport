package ru.brkmed.dtk.gui.controlers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Connection;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoBuilding;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoConnection;
import ru.brkmed.dtk.gui.main.AbstractGUIControler;
import ru.brkmed.dtk.gui.main.GUIConnections;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

public class ControlerConnectionsCreateEditRecord extends AbstractChildWindow implements Initializable {
    @FXML
    private Button btnConnectCrEdit;

    @FXML
    private   ChoiceBox<String> chcBoxSetBuild;


    @FXML
    private ChoiceBox<String> chcBoxTypeConnect;

    @FXML
    private ChoiceBox<String> chcBoxTypeTax;

    @FXML
    private DatePicker dpDateConnect;

    @FXML
    private TextField txtFieldConnect;

    @FXML
    private TextField txtFieldConnectionTax;

    @FXML
    private TextField txtFieldSuplier;

    @FXML
    private TextField txtFieldTax;

    @FXML
    private TextField txtFieldNameConnect;

    @FXML
    private Label lblBuild;

    @FXML
    private Label lblTypeConnect;

    @FXML
    private Label lblTypeTax;

    ControlerDaoConnection contDao = new ControlerDaoConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    public static List<String> getList() {
        ControlerDaoBuilding controlerDaoBuilding = new ControlerDaoBuilding( );
        List<String> listNameBuild = new ArrayList<>( );
        for (Building build : controlerDaoBuilding.listBuilding( )) {
            listNameBuild.add(build.getNameBuilding( ));
        }
        return listNameBuild;
    }
//        List<Building> buildingList = controlerDaoBuilding.listBuilding();
//        ChoiceBox<String> choiceBox = new ChoiceBox<>();
//        for (Building build : buildingList) {
//            choiceBox.getItems().add(build.getNameBuilding());
//        }
//
//
//
//        //choiceBox.show();
////        choiceBox.setOnAction((event) -> {
////                    int selec = choiceBox.getSelectionModel().getSelectedIndex();
////                    Object select = choiceBox.getSelectionModel().getSelectedItem();
////                    System.out.println(select );
////        }
////                 );
////        return choiceBox;
//    }


    @FXML
    void btnSaveConnect(ActionEvent event) {
        List<Button> guiButton = GUIConnections.getControlerGUIConnections( ).getButtonList( );
        boolean[] array = new boolean[] {super.checkTextListTxtField(addListTxtField()), super.checkTextListChoiceBoxField(addListChcBox()),
                super.checkTextListDatePickerField(addListDtpick())} ;
        if (guiButton.get(0).getText().equals("Создать")) {
            if (super.getBoolValue(array)) {
                contDao.addConnection(getOverFieldConnection());
                super.nullSetTxtField(addListTxtField());
                super.nullSetChcBox(addListChcBox());
                super.nullSetDtPicker(addListDtpick());

            }

        }

        else {
            if (super.getBoolValue(array)) {
                Long id = GUIConnections.getConnectRecord().getId();
                contDao.updateConnections(id, getOverFieldConnection());
                TableView<Connection> tblView = GUIConnections.getControlerGUIConnections().getTableConnection();
                AbstractGUIControler absGUI = new GUIConnections(  );
                ObservableList<Connection> observableList = (ObservableList<Connection>) absGUI.getObservableList();
                tblView.setItems(observableList);
                GUIConnections.getControlerGUIConnections().getStage().close();
            }
        }

 /*       if(guiButton.get(0).getText().equals("Создать")) {
            checkFieldConnect(txtFieldNameConnect, chcBoxSetBuild, dpDateConnect, txtFieldSuplier,
                    chcBoxTypeConnect, txtFieldConnect, txtFieldTax, txtFieldConnectionTax, chcBoxTypeTax);
            boolean checkLuck = choiceBoolConnect(txtFieldNameConnect, chcBoxSetBuild, dpDateConnect, txtFieldSuplier,
                    chcBoxTypeConnect, txtFieldConnect, txtFieldTax, txtFieldConnectionTax, chcBoxTypeTax);
            if (checkLuck) {
//                Connection connect = new Connection();
//                connect.setNameConnection(txtFieldNameConnect.getText());
//                Map<Long, Building> choice = ControlerGUIConnections.mapChcBoxBuild;
//                Building build = null;
//                String nameCon = chcBoxSetBuild.getValue();
//                for(Map.Entry<Long, Building> map : choice.entrySet()) {
//                    Building tmpBuild = map.getValue();
//                    if (nameCon.equals(tmpBuild.getNameBuilding())) {
//                        build = tmpBuild;
//                    }
//                }
//                connect.setBuild(build);
//                Date date = Date.from(dpDateConnect.getValue().atStartOfDay(ZoneId.systemDefault( )).toInstant());
//                connect.setDateConnection(date);
//                connect.setSuplier(txtFieldSuplier.getText());
//                connect.setTypeConnection(chcBoxTypeConnect.getValue());
//                String sConn = replaceDouble(txtFieldConnect.getText());
//                Double dConn = Double.parseDouble(sConn);
//                connect.setSpeedConnection(dConn);
//                sConn = replaceDouble(txtFieldTax.getText());
//                Double dTax = Double.parseDouble(sConn);
//                connect.setTax(dTax);
//                sConn = replaceDouble(txtFieldConnectionTax.getText());
//                Double dStTax = Double.parseDouble(sConn);
//                connect.setStartTax(dStTax);
//                connect.setTypeTax(chcBoxTypeTax.getValue());
                contDao.addConnection(getOverFieldConnection());
                for (Parent parent : getMinSceneList()) {
                    String input = String.valueOf(parent);
                    String result = input.substring(0, input.indexOf("["));
                    switch (result) {
                        case ("TextField"):
                            txtFieldConnect.setText(null);
                            txtFieldNameConnect.setText(null);
                            txtFieldConnectionTax.setText(null);
                            txtFieldSuplier.setText(null);
                            txtFieldTax.setText(null);
                            break;
                        case ("ChoiceBox"):
                            chcBoxSetBuild.setValue(null);
                            chcBoxTypeConnect.setValue(null);
                            chcBoxTypeTax.setValue(null);
                            break;
                        case ("DatePicker"):
                            dpDateConnect.setValue(null);
                            break;
                    }
                }


            }

        }

        else if(guiButton.get(0).getText().equals("Изменить")) {
            checkFieldConnect(txtFieldNameConnect, chcBoxSetBuild, dpDateConnect, txtFieldSuplier,
                    chcBoxTypeConnect, txtFieldConnect, txtFieldTax, txtFieldConnectionTax, chcBoxTypeTax);
            boolean checkLuck = choiceBoolConnect(txtFieldNameConnect, chcBoxSetBuild, dpDateConnect, txtFieldSuplier,
                    chcBoxTypeConnect, txtFieldConnect, txtFieldTax, txtFieldConnectionTax, chcBoxTypeTax);
            if (checkLuck) {
                Long id = ControlerGUIConnections.idConnect;
                contDao.updateConnections(id, getOverFieldConnection());
               // saveBtn = true;
               // if(saveBtn) {
                    ObservableList<Connection> obsBuild = (ObservableList<Connection>) new ControlerGUIConnections().getObservableList();
                    TableView<Connection> tblView = ControlerGUIConnections.getTableConnections;
                    tblView.setItems(obsBuild);
                    ControlerGUIConnections.getStage().close();
               // }
            }
        }
*/
    }

    public void setChcBoxSetBuild(ChoiceBox<String> chcBoxSetBuild) {
        this.chcBoxSetBuild = chcBoxSetBuild;

    }

    public ControlerConnectionsCreateEditRecord() {
      //  chcBoxSetBuild = new ChoiceBox<>(FXCollections.observableList(getList()));
    }

    public void checkFieldConnect(TextField txtFieldNameConnect, ChoiceBox<String> chcBoxSetBuild,
                                  DatePicker dpDateConnect, TextField txtFieldSuplier, ChoiceBox<String> chcBoxTypeConnect,
                                  TextField txtFieldConnect, TextField txtFieldTax, TextField txtFieldConnectionTax,
                                  ChoiceBox<String> chcBoxTypeTax) {
        if (txtFieldNameConnect.getText().length() == 0) {
            txtFieldNameConnect.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            txtFieldNameConnect.setStyle(null);
        }
        if (chcBoxSetBuild.getValue() == null) {
            chcBoxSetBuild.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            chcBoxSetBuild.setStyle(null);
        }
        if (dpDateConnect.getEditor().getText().length() == 0) {
            dpDateConnect.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            dpDateConnect.setStyle(null);
        }
        if (txtFieldSuplier.getText().length() == 0) {
            txtFieldSuplier.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            txtFieldSuplier.setStyle(null);
        }
        if (chcBoxTypeConnect.getValue() == null) {
            chcBoxTypeConnect.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            chcBoxTypeConnect.setStyle(null);
        }
        if (txtFieldConnect.getText().length() == 0) {
            txtFieldConnect.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            txtFieldConnect.setStyle(null);
        }
        if (txtFieldTax.getText().length() == 0) {
            txtFieldTax.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            txtFieldTax.setStyle(null);
        }
        if (txtFieldConnectionTax.getText().length() == 0) {
            txtFieldConnectionTax.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            txtFieldConnectionTax.setStyle(null);
        }
        if (chcBoxTypeTax.getValue() == null) {
            chcBoxTypeTax.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            chcBoxTypeTax.setStyle(null);
        }

    }

    public boolean choiceBoolConnect(TextField txtFieldNameConnect, ChoiceBox<String> chcBoxSetBuild,
                                     DatePicker dpDateConnect, TextField txtFieldSuplier, ChoiceBox<String> chcBoxTypeConnect,
                                     TextField txtFieldConnect, TextField txtFieldTax, TextField txtFieldConnectionTax,
                                     ChoiceBox<String> chcBoxTypeTax) {
        if (txtFieldNameConnect.getText().length() != 0 && chcBoxSetBuild.getValue().length() != 0) {
            if (dpDateConnect.getEditor().getText().length() != 0 && txtFieldSuplier.getText().length() != 0) {
                if (chcBoxTypeConnect.getValue().length() != 0 && txtFieldConnect.getText().length() != 0) {
                    if (txtFieldTax.getText().length() != 0 && txtFieldConnectionTax.getText().length() != 0 && chcBoxTypeTax.getValue().length() != 0) {
                        return true;
                    }
                }
            }
        }
      return false;
    }

    public Connection getOverFieldConnection() {
        Connection connect = new Connection();
        connect.setNameConnection(txtFieldNameConnect.getText());
        Map<Long, Building> choice = GUIConnections.getMapChcBoxBuild();
        Building build = null;
        String nameCon = chcBoxSetBuild.getValue();
        for(Map.Entry<Long, Building> map : choice.entrySet()) {
            Building tmpBuild = map.getValue();
            if (nameCon.equals(tmpBuild.getNameBuilding())) {
                build = tmpBuild;
            }
        }
        connect.setBuild(build);
        Date date = Date.from(dpDateConnect.getValue().atStartOfDay(ZoneId.systemDefault( )).toInstant());
        connect.setDateConnection(date);
        connect.setSuplier(txtFieldSuplier.getText());
        connect.setTypeConnection(chcBoxTypeConnect.getValue());
        String sConn = super.replaceDouble(txtFieldConnect.getText());
        Double dConn = Double.parseDouble(sConn);
        connect.setSpeedConnection(dConn);
        sConn = super.replaceDouble(txtFieldTax.getText());
        Double dTax = Double.parseDouble(sConn);
        connect.setTax(dTax);
        sConn = super.replaceDouble(txtFieldConnectionTax.getText());
        Double dStTax = Double.parseDouble(sConn);
        connect.setStartTax(dStTax);
        connect.setTypeTax(chcBoxTypeTax.getValue());
        return connect;
    }


    public List<Parent> getMinSceneList() {
        List<Parent> list = new ArrayList<>();
        list.add(txtFieldConnect);
        list.add(chcBoxSetBuild);
        list.add(dpDateConnect);
        return list;

    }


    @Override
    public List<TextField> addListTxtField() {
     //   txtFieldNameConnect, chcBoxSetBuild, dpDateConnect, txtFieldSuplier,
       //         chcBoxTypeConnect, txtFieldConnect, txtFieldTax, txtFieldConnectionTax, chcBoxTypeTax
        List<TextField> txtList = new ArrayList<>(  );
        txtList.add(txtFieldNameConnect);
        txtList.add(txtFieldSuplier);
        txtList.add(txtFieldConnect);
        txtList.add(txtFieldTax);
        txtList.add(txtFieldConnectionTax);
        return txtList;
    }

    @Override
    public List<ChoiceBox<String>> addListChcBox() {
        List<ChoiceBox<String>> chcList = new ArrayList<>(  );
        chcList.add(chcBoxSetBuild);
        chcList.add(chcBoxTypeConnect);
        chcList.add(chcBoxTypeTax);
        return chcList;
    }

    @Override
    public List<DatePicker> addListDtpick() {
        List<DatePicker> dpConnect = new ArrayList<>();
        dpConnect.add(dpDateConnect);
        return dpConnect;
    }
}
