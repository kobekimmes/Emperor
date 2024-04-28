package kobekimmes.com.emperor;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

public class Chess extends Application {
    int boardWidth = 800;
    int boardLength = 8;
    int squareSize = boardWidth / boardLength;
    double mouseOffsetX, mouseOffsetY;

    Game game;
    @Override
    public void start(Stage stage) throws IOException {
        game = new Game(false, new MinMax());
        //game = new Game(true, null);
        Board gameBoard = game.chessBoard;
        renderBoard(stage);
        renderPiecesOnBoard(stage, gameBoard);
        stage.setResizable(false);
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
        stage.setTitle("Emperor");
        stage.show();
    }


    public void renderPiecesOnBoard(Stage stage, Board b) {
        Pane grid = (Pane) stage.getScene().getRoot();

//        for (int i = 0; i < boardLength; i++) {
//            for (int j = 0; j < boardLength; j++) {
//                Piece currPiece = b.getPiece(new Position(i, j));
//                if (currPiece != null) {
        for (Piece[] pieces : b.pieceArray) {
            for (Piece p : pieces) {
                if (p != null) {
                    Pane piecePane = new Pane();

                    ImageView chessPieceView = produceImageView(p);

                    piecePane.getChildren().add(chessPieceView);

                    handleMovementLogic(stage, b, piecePane, p);

                    piecePane.setLayoutX(p.pos.col * squareSize);
                    piecePane.setLayoutY(p.pos.row * squareSize);

                    grid.getChildren().add(piecePane);
                }
            }
        }
    }

    ImageView produceImageView(Piece p) {
        ImageView chessPieceView = new ImageView();

        InputStream imagePath = getClass().getResourceAsStream("/media/pieces/" +
                (p.isBlack() ? "black" : "white") + "-" +
                p.getClass().getSimpleName().toLowerCase() + ".png");

        Image chessPiece = new Image(imagePath);
        chessPieceView.setImage(chessPiece);

        chessPieceView.setFitHeight(squareSize);
        chessPieceView.setFitWidth(squareSize);

        return chessPieceView;
    }

    void handleMovementLogic(Stage stage, Board b, Node node, Piece p) {

        node.setOnMousePressed((MouseEvent e) -> {
           mouseOffsetX = e.getSceneX() - node.getLayoutX();
           mouseOffsetY = e.getSceneY() - node.getLayoutY();
           System.out.println(mouseOffsetX + ", " + mouseOffsetY);
        });

        node.setOnMouseDragged((MouseEvent e) -> {
            node.setLayoutX(e.getSceneX() - mouseOffsetX);
            node.setLayoutY(e.getSceneY() - mouseOffsetY);
        });


        node.setOnMouseReleased((MouseEvent e) -> {
            int newYPos = (int) (boardLength * e.getSceneX() / boardWidth);
            int newXPos = (int) (boardLength * e.getSceneY() / boardWidth);
            //System.out.println(newXPos + ", " + newYPos);
            //System.out.println(game.chessBoard.blackPieces);
            //System.out.println(game.chessBoard.whitePieces);


            if (game.makeMove(p, new Position(newXPos, newYPos))) {
                node.setLayoutX(newYPos * squareSize);
                node.setLayoutY(newXPos * squareSize);

                game.changeTurn();
                renderBoard(stage);
                renderPiecesOnBoard(stage, b);

                if (game.botMove()) {
                    renderBoard(stage);
                    renderPiecesOnBoard(stage, b);
                    game.changeTurn();
                }
            }
            else {
                node.setLayoutX(p.pos.col * squareSize);
                node.setLayoutY(p.pos.row * squareSize);
            }

        });

        mouseOffsetX = 0;
        mouseOffsetY = 0;

    }
}