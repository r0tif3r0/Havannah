package Handling;

import Hexagon.Hexagon;
import Hexagon.HexagonMap;
import Logic.GameLogic;
import figures.Cells;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    @FXML
    private Group grid;
    @FXML
    private BorderPane border;
    @FXML
    private Button restartButton;
    @FXML
    private Button menuButton;
    @FXML
    private Label draw;
    @FXML
    private Label blueWon;
    @FXML
    private Label redWon;
    private boolean isFirstPlayerWalks = true;
    private final GameLogic gameLogic = new GameLogic();

    public void build8Grid() {
        menuButton.setVisible(true);
        restartButton.setVisible(true);
        grid = build8Map();
        border.setCenter(grid);
    }

    public Group build8Map() {
        Group grid = new Group();
        HexagonMap map = new HexagonMap(30);
        for (Cells cell : Cells.values()) {
            map.addHexagon(new Hexagon(cell.getX(), cell.getY()));
        }
        for (Hexagon h : map.getAllHexagons()) {
            h.setFill(Paint.valueOf("#ffffff"));
            h.setStrokeWidth(2.0);
            h.setStroke(Paint.valueOf("#777777"));
        }
        map.setOnHexagonClickedCallback(event -> {
            Hexagon hex = map.getHexagon(event.getQ(),event.getR());
            if (isFirstPlayerWalks) {
                fillHexRed(hex);
            } else fillHexBlue(hex);
            if(gameLogic.gameOver()){
                grid.setDisable(true);
                draw.setVisible(true);
            }
        });
        map.render(grid);
        return grid;
    }



    private void fillHexRed(Hexagon hexagon) {
        while (!gameLogic.hasRedHexagon(hexagon) && !gameLogic.hasBlueHexagon(hexagon)) {
            gameLogic.mkRedHexagon(hexagon);
            hexagon.setFill(Paint.valueOf("#7f0505"));
            isFirstPlayerWalks = false;
        }
        if (gameLogic.redWin()) {
            grid.setDisable(true);
            redWon.setVisible(true);
        }
    }

    private void fillHexBlue(Hexagon hexagon) {
        while (!gameLogic.hasBlueHexagon(hexagon) && !gameLogic.hasRedHexagon(hexagon)) {
            gameLogic.mkBlueHexagon(hexagon);
            hexagon.setFill(Paint.valueOf("#15057f"));
            isFirstPlayerWalks = true;
        }
        if (gameLogic.blueWin()) {
            grid.setDisable(true);
            blueWon.setVisible(true);
        }
    }

    public void menu() throws IOException {
        Stage stage = (Stage) menuButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startScene.fxml"));
        Parent root1 = loader.load();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void restart() throws IOException {
        Stage stage = (Stage) restartButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameScene.fxml"));
        Parent root1 = loader.load();
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
