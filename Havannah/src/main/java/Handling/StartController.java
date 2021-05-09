package Handling;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class StartController {


    @FXML
    private Button helpButton;
    @FXML
    private Button startButton;


    @FXML
    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void help(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)helpButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/helpScene.fxml"));
        Parent root1 = loader.load();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void start(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)startButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameScene.fxml"));
        Parent root1 = loader.load();
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
