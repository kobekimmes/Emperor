package kobekimmes.com.emperor;

class Knight extends Piece {

    Knight(Board _b, int _row, int _col, boolean _isBlack) {
        pos = new Position(_row, _col);
        isBlack = _isBlack;
        chessBoard = _b;
    }


    public boolean isMoveLegal(Position newPos) {
        return false;
    }
}