package ru.brkmed.dtk.gui.controlers;

import javafx.event.Event;
import javafx.scene.control.*;
import ru.brkmed.dtk.dao.mainClasses.references.controler.TestControler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import ru.brkmed.dtk.reports.form30.table7000.Table7000Dao;

public class ControlerFX extends Application implements Initializable {
    @FXML private TextField nameText;
    @FXML private TextField surnameText;
    @FXML private TextField patronymicText;
    @FXML private TextField cPositionText;
    @FXML private TextField startCertText;
    @FXML private TextField endCertText;
    @FXML private TextArea name;
    @FXML private TextArea surname;
    @FXML private TextArea patronymic;
    @FXML private TextArea cPosition;
    @FXML private DatePicker startCert;
    @FXML private DatePicker endCert;
    @FXML private Button createRow;
    @FXML private CheckBox sert;
    private String nameS;
    private String surnameS;
    private String patronymicS;
    private String cPositionS;
    private Date startCertS;
    private Date endSertS;
    private boolean sertB;
    TestControler testControler;





    public static void main(String[] args) {
        Application.launch(args);
       // TestControler testC = new TestControler();
       // testC.addEmployee(s, s1, i);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//     createRow.setOnAction(event -> {
//        // Alert alert = new Alert(Alert.AlertType.INFORMATION, "Принято" + name.getText() + " " + surname.getText() + " " +  count.getText());
//       //  alert.showAndWait();
//         if ((nameText.getText() == "") && (surnameText.getText() == "") && (countText.getText() == "")){
//             System.out.println("Ничего не введено");
//         } else {
//            s = nameText.getText();
//            s1 = surnameText.getText();
//            s2 = countText.getText();
//         }
//
//     });
//        System.out.println(s + s1 + s2);


    }

    public ControlerFX() {
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/testfx.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Тест сохранения");
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void create(Event event) {
 //       createRow.setOnAction(new EventHandler<ActionEvent>() {
 //           @Override
 //           public void handle(ActionEvent event) {
//                if ((nameText.getText() == "") && (surnameText.getText() == "") && (countText.getText() == "")){
//             System.out.println("Ничего не введено");
//         } else {
//            s = nameText.getText();
//            s1 = surnameText.getText();
//            s2 = countText.getText();
//         }
//            inputText();
//
//            }
//        });
//      //  System.out.println(s + s1 + i);
      inputText();
    if (testControler == null) {
        testControler = new TestControler();
 //       testControler.addEmployee(nameS, surnameS, patronymicS, cPositionS, sertB, startCertS, endSertS);
    } else {
 //       testControler.addEmployee(nameS, surnameS, patronymicS, cPositionS, sertB, startCertS, endSertS);
    }

    }
    public void inputText() {
        if (!(name.getText() == null) && !(surname.getText() == null) && !(patronymic.getText() == null)
        && !(cPosition.getText() == null)) {
            //         s = name.getText();
            //         s1 = surname.getText();
            //         i = Integer.parseInt(count.getText());
            //     } else {
            //         System.out.println("Ничего не введено");
            //     }
            //    System.out.println(s + s1 + i);
            nameS = name.getText();
            surnameS = surname.getText();
            patronymicS = patronymic.getText();
            cPositionS = patronymic.getText();


        }
        else {

            System.out.println("Что не ввели" );
        }
    }
    public void isSert (Event event) {
        sertB = true;
    }

    public void getDateStartSert (Event event) {
        if (startCert.getValue() != null) {
            startCertS = Date.from(startCert.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
    public void getDateEndSert (Event event) {
        if (endCert.getValue() != null) {
            endSertS = Date.from(endCert.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
}
