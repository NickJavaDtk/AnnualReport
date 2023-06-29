package ru.brkmed.dtk.gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.entityes.Department;
import ru.brkmed.dtk.dao.mainClasses.entityes.Device;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoDevice;
import ru.brkmed.dtk.gui.main.GUIDepartment;
import ru.brkmed.dtk.gui.main.GUIDevice;

import java.util.ArrayList;
import java.util.List;

public class ControlerDeviceCreateEditRecord extends AbstractChildWindow {
    public CheckBox chbConnect;
    @FXML
    private Button btnSave;

    @FXML
    private ChoiceBox<String> chbOC;

    @FXML
    private ChoiceBox<String> chbTypeDevice;

    @FXML
    private TextField txtFieldBrand;

    @FXML
    private TextField txtFieldNumber;

    @FXML
    private TextField txtFieldTerm;

    ControlerDaoDevice daoDevice = new ControlerDaoDevice();
    @FXML
    void btnSaveDevice(ActionEvent event) {
        List<Button> guiButton = GUIDevice.getGUIDevice( ).getButtons( );
        boolean[] array = new boolean[] {super.checkTextListTxtField(addListTxtField()), checkTextListChoiceBoxField(addListChcBox())} ;
        if (guiButton.get(0).getText().equals("Создать")) {
            if (super.getBoolValue(array)) {
                daoDevice.addDevice(getOverFieldDevice());
                chbTypeDevice.setStyle(null);
                chbOC.setStyle(null);
                chbConnect.setSelected(false);
                super.nullSetTxtField(addListTxtField());
                super.nullSetTxtField(addListTxtFieldForNull());
                super.nullSetChcBox(addListChcBox());

            }

        }

        else {
            if (super.getBoolValue(array)) {
                Long id = GUIDevice.getDeviceRecord( ).getId( );
                daoDevice.updateDevices(id, getOverFieldDevice());
                TableView<Device> tblView = GUIDevice.getGUIDevice( ).getTableDevice( );
                ObservableList<Device> observableList = FXCollections.observableArrayList();
                List<Device> list = daoDevice.listDevices();
                observableList.addAll(list);
                tblView.setItems(observableList);
                GUIDevice.getGUIDevice().getStage().close();
            }
        }

    }

    @Override
    public List<TextField> addListTxtField() {
        List<TextField> list = new ArrayList<>();
        list.add(txtFieldTerm);
        return list;
    }

    @Override
    public List<ChoiceBox<String>> addListChcBox() {
        List<ChoiceBox<String>> list = new ArrayList<>();
        list.add(chbTypeDevice);
        list.add(chbOC);
        return list;

    }

    @Override
    public List<DatePicker> addListDtpick() {
        return null;
    }

    @Override
    public boolean checkTextListChoiceBoxField(List<ChoiceBox<String>> list) {
        boolean[] boolList = new boolean[list.size( )];
        try {
//
            ChoiceBox<String> typeDevice = list.get(0);
            ChoiceBox<String> typeOC = list.get(1);
            if (typeDevice.getValue() == null && typeOC.getValue() == null) {
                typeDevice.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                typeOC.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                boolList[0] = false;
                boolList[1] = false;
            } else if ((typeDevice.getValue().equals("Персональные компьютеры") && typeOC.getValue() == null) ||
                    (typeDevice.getValue().equals("Персональные компьютеры") && typeOC.getValue() == "")) {
                typeDevice.setStyle(null);
                typeOC.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                boolList[0] = true;
                boolList[1] = false;
                if (!chbConnect.isSelected()) {
                    chbConnect.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else {
                    chbConnect.setStyle(null);
                }
            } else if (typeDevice.getValue() != null) {
                typeOC.setStyle(null);
                boolList[0] = true;
                boolList[1] = true;
            }

        } catch (NullPointerException exception) {
//
            exception.getMessage( );
        }

        return getBoolValue(boolList);
    }


    public List<TextField> addListTxtFieldForNull() {
        List<TextField> list = new ArrayList<>(  );
        list.add(txtFieldNumber);
        list.add(txtFieldBrand);
        return list;

    }

    public Device getOverFieldDevice() {
        Device device = new Device();
        device.setTypeDevice(chbTypeDevice.getValue());
        device.setBrand(txtFieldBrand.getText(  ));
        device.setNumber(txtFieldNumber.getText());
        if (chbOC.getValue() == null) {
            device.setTypeOC(new GUIDevice(  ).getObsTypeOC()[0]);
        } else {
            device.setTypeOC(chbOC.getValue());
        }
        device.setTerm(Integer.parseInt(txtFieldTerm.getText()));
        device.setConnect(chbConnect.isSelected());
        return device;
    }
}
