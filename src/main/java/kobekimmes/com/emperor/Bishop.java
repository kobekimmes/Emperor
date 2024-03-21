package kobekimmes.com.emperor;

class Bishop extends Piece {

    Bishop(Board _b, int _row, int _col, boolean _isBlack) {
        row = _row;
        col = _col;
        isBlack = _isBlack;
        chessBoard = _b;
    }


    public boolean isMoveLegal(int rowDest, int colDest) {
        return false;
    }





}