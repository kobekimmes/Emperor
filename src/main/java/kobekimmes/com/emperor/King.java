package kobekimmes.com.emperor;

class King extends Piece {

    King(Board _b, int _row, int _col, boolean _isBlack) {
        row = _row;
        col = _col;
        isBlack = _isBlack;
        chessBoard = _b;

    }


    public boolean isMoveLegal(int rowDest, int colDest) {
        if (chessBoard.verifySourceAndDestination(row, col, rowDest, colDest, isBlack)) {
            return chessBoard.verifyAdjacent(row, col, rowDest, colDest);
        }
        return false;
    }
}