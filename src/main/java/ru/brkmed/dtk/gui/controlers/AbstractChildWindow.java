package ru.brkmed.dtk.gui.controlers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractChildWindow {

    private boolean checkField = false;

    private boolean[] boolList;

    public abstract List<TextField> addListTxtField();

    public abstract List<ChoiceBox<String>> addListChcBox();

    public abstract List<DatePicker> addListDtpick();

    public boolean checkTextListTxtField(List<TextField> list) {
        boolList = new boolean[list.size( )];
        try {
            for (int i = 0; i < list.size( ); i++) {
                TextField txtField = list.get(i);

                if (txtField.getText( ).length( ) == 0 || txtField.getText( ) == null) {
                    txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                    boolList[i] = false;
                } else {
                    txtField.setStyle(null);
                    boolList[i] = true;
                }
            }
        } catch (NullPointerException exception) {
            for (int i = 0; i < list.size( ); i++) {
                TextField txtField = list.get(i);
                txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                boolList[i] = false;
            }
        }


       // boolean c = getBoolValue(boolList);
        return getBoolValue(boolList);

    }

    public void nullSetTxtField(List<TextField> list) {
        for (TextField txtField : list) {
            txtField.setText(null);
        }
    }

    public boolean isCheckField() {
        return checkField;
    }

    public boolean getBoolValue(boolean[] bool) {
        boolean check = bool[0];
        for (int i = 0; i < bool.length; i++) {
            check = check && bool[i];
        }
        return check;
    }

    public void validationFiledField(Parent parent) {
        ArrayList<Node> nodeList = ListNodes.getAllNodes(parent);
        // nodeList.
    }

}
