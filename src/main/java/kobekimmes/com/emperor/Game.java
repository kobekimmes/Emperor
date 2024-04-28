package kobekimmes.com.emperor;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Game {
    boolean isBlackTurn;
    boolean versus;
    int moveCount;
    Board chessBoard;

    Bot computer;

    //HashMap<Piece, List<Position>> legalMoves;

    Game(boolean _versus, Bot _computer) {
        isBlackTurn = false;
        chessBoard = new Board();
        moveCount = 0;
        versus = _versus;
        computer = _computer;
        setBookMovesForBot("");
    }

    void setBookMovesForBot(String moveString) {
        if (!versus && computer != null) {
            computer.setBookMoves(new Piece[]{chessBoard.pieceArray[1][0]}, new Position[]{new Position(3, 0)});
        }
    }

    void loadNewGame(String pieceString) {
        Fen.load(chessBoard, pieceString);
    }

    void changeTurn() {
        isBlackTurn = !isBlackTurn;
    }

    boolean kingInCheck() {
        for (Piece p : (isBlackTurn ? chessBoard.blackPieces : chessBoard.whitePieces)) {
            if (p instanceof King) {
                return ((King) p).isInCheck();
            }
        }
        return false;
    }

    boolean makeMove(Piece p, Position newPos) {
        if (p.movePiece(newPos, isBlackTurn) && !kingInCheck()) {
            //System.out.println("Piece; " + p + " moved to" + newPos);
            if (isBlackTurn) {
                moveCount++;
            }
            //updateLegalMovesForGameState();
            return true;
        }
        else {
            //System.out.println("Piece failed to move");
            return false;
        }
    }

    boolean botMove() {
        if (!versus && computer != null) {
            computer.move(this);
            moveCount++;
            return true;
        }
        return false;
    }

    void unmakeMove(Piece p, Position oldPos) {
        p.setPos(oldPos);
    }

    boolean isGameOver() {
        return false;
    }


    void produceLegalMovesForGameState(boolean blacksMove) {
        //legalMoves = new HashMap<>();
        for (Piece p : (blacksMove ? chessBoard.blackPieces : chessBoard.whitePieces)) {
            p.generateLegalMoves();
            //legalMoves.put(p, p.legalMoves);
        }
    }
}