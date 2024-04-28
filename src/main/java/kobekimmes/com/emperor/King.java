package kobekimmes.com.emperor;

import javafx.geometry.Pos;

class King extends Piece {

    boolean inCheck;

    King(Board _b, int _row, int _col, boolean _isBlack) {
        pos = new Position(_row, _col);
        isBlack = _isBlack;
        chessBoard = _b;
        inCheck = false;

    }


    public boolean isMoveLegal(Position newPos) {
        if (chessBoard.verifySourceAndDestination(pos, newPos, isBlack)) {
            return chessBoard.verifyAdjacent(pos, newPos) && !this.isNewMoveInCheck(newPos);
        }
        return false;
    }


    public boolean isNewMoveInCheck(Position newPos) {
        for (Piece piece : (isBlack ? chessBoard.whitePieces : chessBoard.blackPieces)) {
            if (piece != null) {
                if (piece instanceof Pawn) {
                    if (piece.isMoveLegal(newPos) && (newPos.col != piece.pos.col)) {
                        return true;
                    }
                } else if (piece.isMoveLegal(newPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInCheck() {
        for (Piece piece : (isBlack ? chessBoard.whitePieces : chessBoard.blackPieces)) {
            if (piece != null) {
                if (piece instanceof Pawn) {
                    if (piece.isMoveLegal(pos) && (pos.col != piece.pos.col)) {
                        inCheck = true;
                        return true;
                    }
                } else if (piece.isMoveLegal(pos)) {
                    inCheck = true;
                    return true;
                }
            }
        }
        return false;
    }
}