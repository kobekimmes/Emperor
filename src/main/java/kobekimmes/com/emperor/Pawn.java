package kobekimmes.com.emperor;

class Pawn extends Piece {

    Pawn(Board _b, int _row, int _col, boolean _isBlack) {
        pos = new Position(_row, _col);

        isBlack = _isBlack;
        chessBoard = _b;
    }


    public boolean isMoveLegal(Position newPos) {
        if (!chessBoard.checkBounds(pos, newPos)) {
            return false;
        }
        int moveDir = isBlack ? 1 : -1;

        if ((newPos.row - this.pos.row) == moveDir) { //Diagonal capture
            if ((Math.abs(newPos.col - this.pos.col) == 1)) {
                    if (chessBoard.getPiece(newPos) != null && chessBoard.getPiece(newPos).isBlack != this.isBlack) {
                        return true;
                    }
                    else {
                        return false;
                    }
            } else { //Moving forward
                return chessBoard.getPiece(newPos) == null;
            }
        }
        else if ((newPos.row - this.pos.row) == moveDir * 2 && (pos.row == 1 || pos.row == 6)) {
            return (newPos.col == pos.col) && chessBoard.getPiece(newPos) == null && chessBoard.getPiece(new Position(newPos.row - moveDir, newPos.col)) == null;
        }

        return false;
    }
}