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
            System.err.println("Board.setPiece(): Invalid bounds entered or attempting to add null object");
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

    void removePiece(int row, int col) {
        if ((-1 < row && row < pieceArray.length) && (-1 < col && col < pieceArray.length)) {
            pieceArray[row][col] = null;
        }
    }

    boolean checkBounds(int sr, int sc, int er, int ec) {
        return ((sr > -1 && sr < 8)  && (sc > -1 && sc < 8) && (er > -1 && er < 8) && (ec > -1 && ec < 8));
    }


    void renderBoard() {

    }

    boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (!checkBounds(startRow, startCol, endRow, endCol)) {
            return false;
        }

        Piece startPiece = this.getPiece(startRow, startCol);
        if (startPiece != null) {
            Piece endPiece = this.getPiece(endRow, endCol);
            return (startPiece.isBlack == isBlack && (endPiece == null || endPiece.isBlack != isBlack));
        }
        System.err.println("Board.verifySourceAndDestination(): Invalid bounds entered or piece does not exist");
        return false;
    }

    boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (!checkBounds(startRow, startCol, endRow, endCol)) {
            return false;
        }

        int rise = Math.abs(endRow - startRow);
        int run = Math.abs(endCol - startCol);

        if (rise != run) {
            return false;
        }

        int xDir = startRow > endRow ? -1 : 1;
        int yDir = startCol > endCol ? -1 : 1;

        for (int i = 1; i < rise; i++) {
            if (pieceArray[(i*xDir)+startRow][(i*yDir)+startCol] != null) {
                return false;
            }
        }
        return true;

    }

    boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (!checkBounds(startRow, startCol, endRow, endCol)) {
            return false;
        }

        if (startRow != endRow) {
            return false;
        }

        int start = Math.min(startCol, endCol)+1;
        int stop = Math.max(startCol, endCol)-1;

        for (int i = start; i < stop; i++) {
            if (pieceArray[startRow][i + start] != null) {
                return false;
            }
        }

        return true;
    }

    boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (!checkBounds(startRow, startCol, endRow, endCol)) {
            return false;
        }

        if (startCol != endCol) {
            return false;
        }

        int start = Math.min(startRow, endRow)+1;
        int stop = Math.max(startRow, endRow)-1;

        for (int i = start; i < stop; i++) {
            if (pieceArray[i + start][startCol] != null) {
                return false;
            }
        }

        return true;
    }


    boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (!checkBounds(startRow, startCol, endRow, endCol)) {
            return false;
        }

        return Math.abs(endCol - startCol) <= 1 && Math.abs(endRow - startRow) <= 1;

    }






}