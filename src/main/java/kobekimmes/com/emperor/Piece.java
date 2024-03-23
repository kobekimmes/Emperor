package kobekimmes.com.emperor;

abstract class Piece {

    protected int row, col;
    protected boolean isBlack;
    protected Board chessBoard;
    abstract boolean isMoveLegal(int endRow, int endCol);

    public void setPos(int _row, int _col) {
        this.row = _row;
        this.col = _col;
    }

    public boolean isBlack() {
        return this.isBlack;
    }

    boolean movePiece(int endRow, int endCol) {
        if (this.isMoveLegal(endRow, endCol)) {
            this.chessBoard.removePiece(this.row, this.col);
            this.setPos(endRow, endCol);
            this.chessBoard.setPiece(endRow, endCol, this);
            return true;
        }
        return false;
    }



}