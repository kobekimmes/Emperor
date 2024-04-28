package kobekimmes.com.emperor;

class Rook extends Piece {

    Rook(Board _b, int _row, int _col, boolean _isBlack) {
        pos = new Position(_row, _col);
        isBlack = _isBlack;
        chessBoard = _b;
    }


    public boolean isMoveLegal(Position newPos) {
        if (chessBoard.verifySourceAndDestination(pos, newPos, isBlack)) {
            return chessBoard.verifyHorizontal(pos, newPos) || chessBoard.verifyVertical(pos, newPos);
        }
        return false;
    }
}