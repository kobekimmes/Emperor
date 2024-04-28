package kobekimmes.com.emperor;


import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

abstract class Bot {
    protected Piece nextPiece;
    protected Position nextPosition;
    protected Queue<Piece> bookPiece;
    protected Queue<Position> bookPosition;
    protected double eval;

    protected double bestVal;

    protected Bot() {

    }

    protected double pieceValue(Piece p) {

        double value = 0;

        if (p != null) {

            if (p instanceof Pawn) {
                value = 1.00;
            } else if (p instanceof Knight) {
                value = 3.00;
            } else if (p instanceof Bishop) {
                value = 3.20;
            } else if (p instanceof Rook) {
                value = 5.00;
            } else if (p instanceof Queen) {
                value = 9.00;
            } else if (p instanceof King) {
                value = 0;
            }
            return value * (p.isBlack ? -1 : 1);
        }

        return 0.0;
    }
     double evaluateBoard(Board b) {
         double evaluation = 0;
         for (Piece[] pieces : b.pieceArray) {
             for (Piece piece : pieces) {
                 evaluation += pieceValue(piece);
             }
         }
         eval = evaluation;
         return eval;


     }
    abstract void produceMove(Game game);

    void setBookMoves(Piece[] p, Position[] pos) {
        bookPosition = new LinkedList<>();
        bookPiece = new LinkedList<>();

        bookPiece.addAll(Arrays.asList(p));
        bookPosition.addAll(Arrays.asList(pos));
    }

    boolean getBookMoves() {
        if (!bookPiece.isEmpty() && !bookPosition.isEmpty()) {
            nextPiece = bookPiece.remove();
            nextPosition = bookPosition.remove();
            return true;
        }
        return false;
    }

    protected void move(Game game) {
        if (!getBookMoves()) {
            produceMove(game);
            nextPiece.movePiece(nextPosition, true);
        }
        else {
            nextPiece.movePiece(nextPosition, true);
        }
    }

}