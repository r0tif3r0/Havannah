package Handling;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelpController {


    public Button backButton;

    public void back(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startScene.fxml"));
        Parent root1 = loader.load();
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
