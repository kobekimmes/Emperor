package kobekimmes.com.emperor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Chess extends Application {
    int boardWidth = 1200;
    int boardLength = 8;
    int squareSize = boardWidth / boardLength;
    @Override
    public void start(Stage stage) throws IOException {
        renderBoard(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public void renderBoard(Stage stage) {
        Pane grid = new Pane();
        Scene scene = new Scene(grid, boardWidth, boardWidth);


        for (int i = 0; i < boardLength; i++) {

            Text fileNotation = new Text(String.valueOf((char) (i + 97)));
            Text rankNotation = new Text(String.valueOf(8 - i+1));

            fileNotation.setFont(Font.font("Times New Roman", (double) squareSize / 5));
            rankNotation.setFont(Font.font("Times New Roman", (double) squareSize / 5));

            fileNotation.setLayoutX(squareSize * i + (squareSize - 20));
            fileNotation.setLayoutY(22);

            rankNotation.setLayoutX(3);
            rankNotation.setLayoutY(squareSize * i - 5);

            for (int j = 0; j < boardLength; j++) {
                Rectangle square = new Rectangle(i * squareSize, j * squareSize, squareSize, squareSize);
                square.setFill((i + j) % 2 != 0 ? Color.GRAY : Color.WHITE);
                grid.getChildren().add(square);
            }

            grid.getChildren().add(rankNotation);
            grid.getChildren().add(fileNotation);

        }
        //grid.setGridLinesVisible(true);
        stage.setScene(scene);
        stage.setTitle("Chess Board");
        stage.show();
    }
}