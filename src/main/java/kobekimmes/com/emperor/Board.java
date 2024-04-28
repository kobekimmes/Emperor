package kobekimmes.com.emperor;
import java.util.ArrayList;

import javafx.geometry.Pos;

class Board {

    Piece[][] pieceArray;

    ArrayList<Piece> whitePieces;
    ArrayList<Piece> blackPieces;

    Board() {
        pieceArray = new Piece[8][8];
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        Fen.load(this, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    void setPiece(Position pos, Piece p) {
        if ((-1 < pos.row && pos.row < pieceArray.length) && (-1 < pos.col && pos.col < pieceArray.length) && (p != null)) {
            pieceArray[pos.row][pos.col] = p;
            if (p.isBlack) {
                blackPieces.add(p);
            }
            else {
                whitePieces.add(p);
            }
        }
        else {
            System.out.println("Board.setPiece(): Invalid bounds entered or attempting to add null object");
        }
    }

    Piece getPiece(Position pos) {
        if ((-1 < pos.row && pos.row < pieceArray.length) && (-1 < pos.col && pos.col < pieceArray.length)) {
            return pieceArray[pos.row][pos.col];
        }
        else {
            System.out.println("Board.getPiece(): Invalid bounds entered");
            return null;
        }
    }

    void removePiece(Position pos) {
        if ((-1 < pos.row && pos.row < pieceArray.length) && (-1 < pos.col && pos.col < pieceArray.length)) {
            Piece pieceToRemove = pieceArray[pos.row][pos.col];
            if (pieceToRemove != null) {
                if (pieceToRemove.isBlack) {
                    blackPieces.remove(pieceToRemove);
                }
                else{
                    whitePieces.remove(pieceToRemove);
                }
            }
            pieceArray[pos.row][pos.col] = null;
        }
    }

    boolean checkBounds(Position from, Position to) {
        return ((from.row > -1 && from.row < 8)  && (from.col > -1 && from.col < 8) &&
                (to.row > -1 && to.row < 8) && (to.col > -1 && to.col < 8));
    }

    boolean verifySourceAndDestination(Position from, Position to, boolean isBlack) {
        if (!checkBounds(from, to)) {
            return false;
        }

        Piece startPiece = this.getPiece(from);
        if (startPiece != null) {
            Piece endPiece = this.getPiece(to);
            return (startPiece.isBlack == isBlack && (endPiece == null || endPiece.isBlack != isBlack));
        }
        return false;
    }

    boolean verifyDiagonal(Position from, Position to) {
        if (!checkBounds(from, to)) {
            return false;
        }

        int rise = Math.abs(to.row - from.row);
        int run = Math.abs(to.col - from.col);

        if (rise != run) {
            return false;
        }

        int xDir = from.row > to.row ? -1 : 1;
        int yDir = from.col > to.col ? -1 : 1;

        for (int i = 1; i < rise; i++) {
            if (pieceArray[(i*xDir)+from.row][(i*yDir)+from.col] != null) {
                return false;
            }
        }
        return true;

    }

    boolean verifyHorizontal(Position from, Position to) {
        if (!checkBounds(from, to)) {
            return false;
        }

        if (from.row != to.row) {
            return false;
        }

        int start = Math.min(from.col, to.col)+1;
        int stop = Math.max(from.col, to.col);

        for (int i = 1; i < stop - start; i++) {
            if (pieceArray[from.row][i + start] != null) {
                return false;
            }
        }

        return true;
    }

    boolean verifyVertical(Position from, Position to) {
        if (!checkBounds(from, to)) {
            return false;
        }

        if (from.col != to.col) {
            return false;
        }

        int start = Math.min(from.row, to.row)+1;
        int stop = Math.max(from.row, to.row);

        for (int i = 1; i < stop-start; i++) {
            if (pieceArray[start+i][from.col] != null) {
                return false;
            }
        }

        return true;
    }


    boolean verifyAdjacent(Position from, Position to) {
        if (!checkBounds(from, to)) {
            return false;
        }

        return Math.abs(to.col - from.col) <= 1 && Math.abs(to.row - from.row) <= 1;

    }






}