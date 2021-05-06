import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Havannah extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Line line = new Line (0,0,100,100);
        line.setStroke(Color.GRAY);

        Group group = new Group();
        Scene scene = new Scene(group,400,300);
        group.getChildren().add(line);

        primaryStage.setTitle("HAVANNAH");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
