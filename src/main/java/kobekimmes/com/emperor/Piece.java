package kobekimmes.com.emperor;

import java.util.ArrayList;


abstract class Piece {

    protected ArrayList<Position> legalMoves;
    protected Position pos;
    protected boolean isBlack;
    protected Board chessBoard;
    abstract boolean isMoveLegal(Position newPos);

    public void setPos(Position newPos) {
        chessBoard.removePiece(this.pos);
        this.pos = newPos;
        chessBoard.setPiece(newPos, this);

    }

    public boolean isBlack() {
        return this.isBlack;
    }

    boolean movePiece(Position newPos, boolean isBlackTurn) {
        if (this.isMoveLegal(newPos) && isBlack == isBlackTurn) {
            this.setPos(newPos);
            return true;
        }
        return false;
    }

    void generateLegalMoves() {
        legalMoves = new ArrayList<>();
        for (int i = 0; i < chessBoard.pieceArray.length; i++) {
            for (int j = 0; j < chessBoard.pieceArray.length; j++) {
                if (this.isMoveLegal(new Position(i, j))) {
                    legalMoves.add(new Position(i, j));
                }
            }
        }
    }

    public String toString() {
        return (this.isBlack() ? "Black " : "White ") + this.getClass().getSimpleName() + " " + this.pos.toString();
    }


}