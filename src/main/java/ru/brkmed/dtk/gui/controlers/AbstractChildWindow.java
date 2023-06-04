package ru.brkmed.dtk.gui.controlers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.brkmed.dtk.gui.model.ListNodes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractChildWindow {

    private final boolean checkField = false;

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

        return getBoolValue(boolList);

    }

    public boolean checkTextListChoiceBoxField(List<ChoiceBox<String>> list) {
        boolList = new boolean[list.size( )];
        try {
            for (int i = 0; i < list.size( ); i++) {
                ChoiceBox<String>  choiceBox = list.get(i);

                if (choiceBox.getValue() == null) {
                    choiceBox.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                    boolList[i] = false;
                } else {
                    choiceBox.setStyle(null);
                    boolList[i] = true;
                }
            }
        } catch (NullPointerException exception) {
            for (int i = 0; i < list.size( ); i++) {
                ChoiceBox<String>  choiceBox = list.get(i);
                choiceBox.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                boolList[i] = false;
            }
        }

        return getBoolValue(boolList);

    }

    public boolean checkTextListDatePickerField(List<DatePicker> list) {
        boolList = new boolean[list.size( )];
        try {
            for (int i = 0; i < list.size( ); i++) {
                DatePicker  datePicker = list.get(i);

                if (datePicker.getValue() == null) {
                    datePicker.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                    boolList[i] = false;
                } else {
                    datePicker.setStyle(null);
                    boolList[i] = true;
                }
            }
        } catch (NullPointerException exception) {
            for (int i = 0; i < list.size( ); i++) {
                DatePicker  datePicker = list.get(i);
                datePicker.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                boolList[i] = false;
            }
        }

        return getBoolValue(boolList);

    }

    public void nullSetTxtField(List<TextField> list) {
        for (TextField textField : list) {
            textField.setText(null);
        }
    }

    public void nullSetChcBox(List<ChoiceBox<String>> list) {
        for (ChoiceBox chcBox : list) {
            chcBox.setValue(null);
        }
    }

    public void nullSetDtPicker(List<DatePicker> list) {
        for (DatePicker datePicker : list) {
            datePicker.getEditor().setText(null);
        }
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
