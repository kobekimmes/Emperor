package kobekimmes.com.emperor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Chess extends Application {
    int boardWidth = 800;
    int boardLength = 8;
    int squareSize = boardWidth / boardLength;
    @Override
    public void start(Stage stage) throws IOException {
        Board gameBoard = new Board();
        renderBoard(stage);
        renderPiecesOnBoard(stage, gameBoard);
    }

    public static void main(String[] args) {
        launch();
    }

    public void renderBoard(Stage stage) {
        Pane grid = new Pane();
        Scene scene = new Scene(grid, boardWidth, boardWidth);


        for (int i = 0; i < boardLength; i++) {

            Text fileNotation = new Text(String.valueOf((char) (i + 97)));
            Text rankNotation = new Text(String.valueOf(8 - i + 1));

            fileNotation.setFont(Font.font("Courier New", (double) squareSize / 5));
            rankNotation.setFont(Font.font("Courier New", (double) squareSize / 5));

            fileNotation.setLayoutX(squareSize * i + (squareSize - 20));
            fileNotation.setLayoutY(17);

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
        stage.setScene(scene);
        stage.setTitle("Chess Board");
        stage.show();
    }


    public void renderPiecesOnBoard(Stage stage, Board b) {
        Pane grid = (Pane) stage.getScene().getRoot();

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                Piece currPiece = b.getPiece(i, j);
                if (currPiece != null) {

                    Pane piecePane = new Pane();

                    ImageView chessPieceView = new ImageView();

                    InputStream imagePath = getClass().getResourceAsStream("/media/pieces/" +
                            (currPiece.isBlack() ? "black" : "white") + "-" +
                            currPiece.getClass().getSimpleName().toLowerCase() + ".png");

                    Image chessPiece = new Image(imagePath);
                    chessPieceView.setImage(chessPiece);

                    chessPieceView.setFitHeight(squareSize);
                    chessPieceView.setFitWidth(squareSize);

                    piecePane.getChildren().add(chessPieceView);

                    piecePane.setLayoutX(j * squareSize);
                    piecePane.setLayoutY(i * squareSize);

                    grid.getChildren().add(piecePane);

                }
            }
        }
    }
}