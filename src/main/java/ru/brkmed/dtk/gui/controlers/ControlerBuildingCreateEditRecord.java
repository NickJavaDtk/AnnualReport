package ru.brkmed.dtk.gui.controlers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Building;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoBuilding;

import java.util.ArrayList;
import java.util.List;

public class ControlerBuildingCreateEditRecord extends AbstractChildWindow {

    ControlerDaoBuilding daoBuilding = new ControlerDaoBuilding();

  //  ControlerGUIBuilding stguBuld = ControlerGUIBuilding.controlerGUIBuilding;
    Button crt = ControlerGUIBuilding.create;
    Button edt = ControlerGUIBuilding.edit;




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

        List<Button> buttonList = ControlerGUIBuilding.controlerGUIBuilding.getButtons();

        if (buttonList.get(0).getText().equals("Создать")) {
            if( super.checkTextListTxtField(addListTxtField())) {
                    daoBuilding.addBuild(txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
                    nullSetTxtField(addListTxtField( ));
            }
        } else {
            if( super.checkTextListTxtField(addListTxtField())) {
                Long id = ControlerGUIBuilding.buildRecord.getId();
                daoBuilding.updateBuild(id, txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
                TableView<Building> tableView = ControlerGUIBuilding.controlerGUIBuilding.getTableBuilding();
                AbstractGUIControler absGUI = new ControlerGUIBuilding(  );
                ObservableList<Building> observableList = (ObservableList<Building>) absGUI.getObservableList();
                tableView.setItems(observableList);
                ControlerGUIBuilding.controlerGUIBuilding.getStage( ).close( );
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
}
