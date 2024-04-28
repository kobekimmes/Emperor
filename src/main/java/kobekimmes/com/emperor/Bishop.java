package kobekimmes.com.emperor;

class Bishop extends Piece {

    Bishop(Board _b, int _row, int _col, boolean _isBlack) {
        pos = new Position(_row, _col);
        isBlack = _isBlack;
        chessBoard = _b;
    }


    public boolean isMoveLegal(Position newPos) {
        if (chessBoard.verifySourceAndDestination(pos, newPos, isBlack)) {
            return chessBoard.verifyDiagonal(pos, newPos);
        }
        return false;
    }





}