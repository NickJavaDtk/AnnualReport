package ru.brkmed.dtk.gui.controlers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ru.brkmed.dtk.dao.mainСlasses.entities.Building;
import ru.brkmed.dtk.dao.mainСlasses.references.controler.ControlerDaoBuilding;

import java.util.List;

public class ControlerBuildingCreateEditRecord {

    ControlerDaoBuilding daoBuilding = new ControlerDaoBuilding();
   // ControlerGUIBuilding guiBuilding = new ControlerGUIBuilding();


    public static boolean saveBtn;
    @FXML
    private Button btnBuldCrEdit;

    @FXML
    private TextField txtFileldAdressBuild;

    @FXML
    private TextField txtFileldNameBuild;

    @FXML
    void btnSave(ActionEvent event) {
        List<Button> guiButton = ControlerGUIBuilding.buttonList;

        if(guiButton.get(0).getText().equals("Создать")) {
           checkTextInTextField(txtFileldNameBuild, txtFileldAdressBuild);
            if (txtFileldNameBuild.getText( ).length( ) != 0 && txtFileldAdressBuild.getText( ).length( ) != 0) {
                daoBuilding.addBuild(txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
                txtFileldNameBuild.setText(null);
                txtFileldAdressBuild.setText(null);
            }
        } else if(guiButton.get(0).getText().equals("Изменить")) {
            checkTextInTextField(txtFileldNameBuild, txtFileldAdressBuild);
            if (txtFileldNameBuild.getText( ).length( ) != 0 && txtFileldAdressBuild.getText( ).length( ) != 0) {
                Long id = ControlerGUIBuilding.idBuild;
                daoBuilding.updateBuild(id, txtFileldNameBuild.getText( ), txtFileldAdressBuild.getText( ));
                saveBtn = true;
                if(saveBtn) {
                    ObservableList<Building> obsBuild = new ControlerGUIBuilding().getObsBuilding();
                    TableView<Building> tblView = ControlerGUIBuilding.getTableBuild;
                    tblView.setItems(obsBuild);
                    ControlerGUIBuilding.getStage.close();
                }
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

}
