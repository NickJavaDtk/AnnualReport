package ru.brkmed.dtk.gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoBuilding;
import ru.brkmed.dtk.gui.main.AbstractGUIControler;
import ru.brkmed.dtk.gui.main.GUIBuilding;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class ControlerBuildingCreateEditRecord extends AbstractChildWindow implements Initializable {

    ControlerDaoBuilding daoBuilding = new ControlerDaoBuilding();

  //  ControlerGUIBuilding stguBuld = ControlerGUIBuilding.controlerGUIBuilding;
    Button crt = GUIBuilding.create;
    Button edt = GUIBuilding.edit;




    public static boolean saveBtn;
    @FXML
    private Button btnBuldCrEdit;

    @FXML
    private TextField txtFileldAdressBuild;

    @FXML
    private TextField txtFileldNameBuild;

    @FXML
    void btnSave(ActionEvent event) {
//        List<Button> guiButton = ControlerGUIBuilding.buttonList;
//
//        if(guiButton.get(0).getText().equals("Создать")) {
//           checkTextInTextField(txtFileldNameBuild, txtFileldAdressBuild);
//            if (txtFileldNameBuild.getText( ).length( ) != 0 && txtFileldAdressBuild.getText( ).length( ) != 0) {
//                daoBuilding.addBuild(txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
//                txtFileldNameBuild.setText(null);
//                txtFileldAdressBuild.setText(null);
//            }
//        } else if(guiButton.get(0).getText().equals("Изменить")) {
//            checkTextInTextField(txtFileldNameBuild, txtFileldAdressBuild);
//            if (txtFileldNameBuild.getText( ).length( ) != 0 && txtFileldAdressBuild.getText( ).length( ) != 0) {
//                Long id = ControlerGUIBuilding.idBuild;
//                daoBuilding.updateBuild(id, txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
//                saveBtn = true;
//                if(saveBtn) {
//                    ObservableList<Building> obsBuild = new ControlerGUIBuilding().getObsBuilding();
//                    TableView<Building> tblView = ControlerGUIBuilding.getTableBuild;
//                    tblView.setItems(obsBuild);
//                    ControlerGUIBuilding.getStage.close();
//                }
//            }
//        }

        List<Button> buttonList = GUIBuilding.GUIBuilding.getButtons();

        if (buttonList.get(0).getText().equals("Создать")) {
            if( super.checkTextListTxtField(addListTxtField())) {
                    daoBuilding.addBuild(txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
                    nullSetTxtField(addListTxtField( ));
            }
        } else {
            if( super.checkTextListTxtField(addListTxtField())) {
                Long id = GUIBuilding.buildRecord.getId();
                daoBuilding.updateBuild(id, txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
                TableView<Building> tableView = GUIBuilding.GUIBuilding.getTableBuilding();
                ObservableList<Building> observableList = FXCollections.observableArrayList();
                List<Building> buildingList = new ControlerDaoBuilding().listBuilding();
                observableList.addAll(buildingList);
                tableView.setItems(observableList);
                GUIBuilding.GUIBuilding.getStage( ).close( );
            }
        }



    }

    public  boolean getSaveBtn() {
        return saveBtn;
    }

    public  void setSaveBtn(boolean saveBtn) {
       this.saveBtn = saveBtn;
    }

    public void checkTextInTextField(TextField first, TextField second) {
        if (first.getText( ).length( ) == 0) {
            first.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            first.setStyle(null);
        }
        if (second.getText( ).length( ) == 0) {
            second.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            second.setStyle(null);
        }
    }

    @Override
    public List<TextField> addListTxtField() {
        List<TextField> list = new ArrayList<>(  );
        list.add(txtFileldNameBuild);
        list.add(txtFileldAdressBuild);
        return list;
    }

    @Override
    public List<ChoiceBox<String>> addListChcBox() {
        return null;
    }

    @Override
    public List<DatePicker> addListDtpick() {
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
