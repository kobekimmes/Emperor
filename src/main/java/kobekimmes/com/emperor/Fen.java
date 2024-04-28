package kobekimmes.com.emperor;

class Fen {

    static void load(Board b, String pieces) {

        int row = 0, col = 0;
        char currPiece;
        boolean isBlack;
        Piece newPiece;

        for (int i = 0; i < pieces.length(); i++) {
            currPiece = pieces.charAt(i);

            switch (currPiece) {
                case '/':
                    row++;
                    col = 0;
                    break;
                case 'p':
                case 'P':
                    isBlack = currPiece != 'P';
                    newPiece = new Pawn(b, row, col, isBlack);
                    b.setPiece(new Position(row, col++), newPiece);
//                    if (isBlack) {
//                        b.blackPieces.add(newPiece);
//                    } else {
//                        b.whitePieces.add(newPiece);
//                    }
                    break;
                case 'k':
                case 'K':
                    isBlack = currPiece != 'K';
                    newPiece = new King(b, row, col, isBlack);
                    b.setPiece(new Position(row, col++), newPiece);
//                    if (isBlack) {
//                        b.blackPieces.add(newPiece);
//                    } else {
//                        b.whitePieces.add(newPiece);
//                    }
                    break;
                case 'q':
                case 'Q':
                    isBlack = currPiece != 'Q';
                    newPiece = new Queen(b, row, col, isBlack);
                    b.setPiece(new Position(row, col++), newPiece);
//                    if (isBlack) {
//                        b.blackPieces.add(newPiece);
//                    } else {
//                        b.whitePieces.add(newPiece);
//                    }
                    break;
                case 'r':
                case 'R':
                    isBlack = currPiece != 'R';
                    newPiece = new Rook(b, row, col, isBlack);
                    b.setPiece(new Position(row, col++), newPiece);
//                    if (isBlack) {
//                        b.blackPieces.add(newPiece);
//                    } else {
//                        b.whitePieces.add(newPiece);
//                    }
                    break;
                case 'b':
                case 'B':
                    isBlack = currPiece != 'B';
                    newPiece = new Bishop(b, row, col, isBlack);
                    b.setPiece(new Position(row, col++), newPiece);
//                    if (isBlack) {
//                        b.blackPieces.add(newPiece);
//                    } else {
//                        b.whitePieces.add(newPiece);
//                    }
                    break;
                case 'n':
                case 'N':
                    isBlack = currPiece != 'N';
                    newPiece = new Knight(b, row, col, isBlack);
                    b.setPiece(new Position(row, col++), newPiece);
//                    if (isBlack) {
//                        b.blackPieces.add(newPiece);
//                    } else {
//                        b.whitePieces.add(newPiece);
//                    }
                    break;
                default:
                    if (Character.isDigit(currPiece)) {
                        col+= Integer.parseInt(String.valueOf(currPiece));
                    }
                    else {
                        System.err.println("Fen.load(): Invalid character read when populating the board");
                    }
                    break;
            }
        }
    }
}