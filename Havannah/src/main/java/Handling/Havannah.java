package Handling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Havannah extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/fxml/startScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root));

        InputStream iconStream = getClass().getResourceAsStream("/images/icon.png");
        assert iconStream != null;
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setTitle("Havannah");


        stage.show();
    }
}
