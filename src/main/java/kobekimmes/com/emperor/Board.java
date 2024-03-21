package kobekimmes.com.emperor;

class Board {

    Piece[][] pieceArray;

    Board() {
        Fen.load(this, "");
    }

    void setPiece(int row, int col, Piece p) {
        if ((-1 < row && row < pieceArray.length) && (-1 < col && col < pieceArray.length) && (p != null)) {
            pieceArray[row][col] = p;
        }
        else {
            System.err.println("Board.setPiece(): Invalid bounds entered or null object");
        }
    }

    Piece getPiece(int row, int col) {
        if ((-1 < row && row < pieceArray.length) && (-1 < col && col < pieceArray.length)) {
            return pieceArray[row][col];
        }
        else {
            System.err.println("Board.getPiece(): Invalid bounds entered");
            return null;
        }
    }


    void renderBoard() {

    }

    boolean verifySourceAndDestination() {
        return false;
    }

    boolean verifyDiagonal() {
        return false;
    }

    boolean verifyRow() {
        return false;
    }

    boolean verifyColumn() {
        return false;
    }






}