package ru.brkmed.dtk.gui.controlers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.entityes.Connection;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoBuilding;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoConnection;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ControlerConnectionsCreateEditRecord {
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
        List<Button> guiButton = ControlerGUIConnections.buttonList;

        if(guiButton.get(0).getText().equals("Создать")) {
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
                    ObservableList<Connection> obsBuild = new ControlerGUIConnections().getObsConnection();
                    TableView<Connection> tblView = ControlerGUIConnections.getTableConnections;
                    tblView.setItems(obsBuild);
                    ControlerGUIConnections.getStage.close();
               // }
            }
        }

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
        Map<Long, Building> choice = ControlerGUIConnections.mapChcBoxBuild;
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
        String sConn = replaceDouble(txtFieldConnect.getText());
        Double dConn = Double.parseDouble(sConn);
        connect.setSpeedConnection(dConn);
        sConn = replaceDouble(txtFieldTax.getText());
        Double dTax = Double.parseDouble(sConn);
        connect.setTax(dTax);
        sConn = replaceDouble(txtFieldConnectionTax.getText());
        Double dStTax = Double.parseDouble(sConn);
        connect.setStartTax(dStTax);
        connect.setTypeTax(chcBoxTypeTax.getValue());
        return connect;
    }

    public  String replaceDouble(String s) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String exit = getString(s.toCharArray());
        return getString(decimalFormat.format(Double.parseDouble(exit)).toCharArray());
    }

    public  String getString (char[] chars) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ',') {
                chars[i] = '.';
            }
        }

        sb.append(chars);
        return sb.toString( );
    }

    public List<Parent> getMinSceneList() {
        List<Parent> list = new ArrayList<>();
        list.add(txtFieldConnect);
        list.add(chcBoxSetBuild);
        list.add(dpDateConnect);
        return list;

    }



}
