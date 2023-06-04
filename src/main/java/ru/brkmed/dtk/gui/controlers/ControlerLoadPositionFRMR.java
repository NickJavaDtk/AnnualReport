package ru.brkmed.dtk.gui.controlers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.references.nsi.ParserCurrentPosition;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerLoadPositionFRMR implements Initializable {
    private Desktop desktop = Desktop.getDesktop();

    //private ParserCurrentPosition parser;
    private static File file;


    final FileChooser fileChooser = new FileChooser();

    Stage primaryStage = null;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnView;

    @FXML
    private TextField txtFieldNameFile;

    @FXML
    private Label lblStatus;

    @FXML
    void openFindView(ActionEvent event) {
        txtFieldNameFile.clear();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Файлы НСИ 1.2.643.5.1.13.13.99.2.709_?.?", "1.2.643.5.1.13.13.99.2.709_3.4.xml"));
        file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            txtFieldNameFile.setText(file.getAbsolutePath());
            btnLoad.setDisable(false);
        }


    }

    @FXML
    void startLoad(ActionEvent event) {
//        if (parser != null && parser.isRunning()) {
//            parser.cancel();
//        }
//        parser = new ParserCurrentPosition();
//        Thread thread = new Thread( parser );
//        thread.setDaemon(true);
//        thread.start();
//        pbLoad.progressProperty().bind(parser.progressProperty());
        ParserCurrentPosition parser = new ParserCurrentPosition();
        ControlerDaoPositionFRMR posFRMR = new ControlerDaoPositionFRMR();
        Task pbTask = createWorkerLoad(parser,posFRMR);
     //   List<CurrentPositionFRMR> cur = parser.parseXML(getFile());
     //   posFRMR.loadNsi(cur);
        pbTask.messageProperty().addListener(new ChangeListener<String>( ) {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                lblStatus.setText(newValue);
            }

        });
        new Thread( pbTask ).start();



    }

    public void closeWindow(ActionEvent actionEvent) {


    }



//    public void startLoadWindow() {
//        FXMLLoader loader = new FXMLLoader( );
//        loader.setLocation(getClass( ).getResource("/LoadNSI.fxml"));
//        try {
//            loader.load( );
//        } catch (IOException e) {
//            e.printStackTrace( );
//        }
//        Parent parent = loader.getRoot( );
//        primaryStage = new Stage( );
//        primaryStage.setTitle("Загрузить НСИ");
//        primaryStage.initModality(Modality.APPLICATION_MODAL);
//        primaryStage.setScene(new Scene(parent));
//        primaryStage.showAndWait();
//    }

    public Task createWorkerLoad(ParserCurrentPosition parser, ControlerDaoPositionFRMR posFRMR) {
        return  new Task( ) {
            @Override
            protected Object call() {
                btnLoad.setDisable(true);
                updateMessage("Идет чтение файла");
                List<CurrentPositionFRMR> currentPos = parser.parseXML(getFile( ));
                updateMessage("Файл прочитан");
                updateMessage("Идет загрузка файла");
                posFRMR.loadNsi(currentPos);
                updateMessage("Файл загружен");


                return true;
            }

        };
    }


    private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLoad.setDisable(true);

    }

    public static File getFile() {
        return file;
    }


}
