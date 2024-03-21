package kobekimmes.com.emperor;

class Rook extends Piece {

    Rook(Board _b, int _row, int _col, boolean _isBlack) {
        row = _row;
        col = _col;
        isBlack = _isBlack;
    }


    public boolean isMoveLegal(int rowDest, int colDest) {
        return false;
    }
}