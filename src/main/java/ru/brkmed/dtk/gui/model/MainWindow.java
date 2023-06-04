package ru.brkmed.dtk.gui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends Application implements Initializable {

    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
            mainStage = stage;
            mainStage.setScene(new Scene(root));
            mainStage.setTitle("Учет");
            mainStage.show();
           // mainStage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        MainWindow.mainStage = mainStage;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
