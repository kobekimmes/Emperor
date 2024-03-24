package kobekimmes.com.emperor;

class Fen {

    static void load(Board b, String pieces) {

        int row = 0, col = 0;
        char currPiece;

        for (int i = 0; i < pieces.length(); i++) {
            currPiece = pieces.charAt(i);

            switch (currPiece) {
                case '/':
                    row++;
                    col = 0;
                    break;
                case 'p':
                case 'P':
                    b.setPiece(row, col, new Pawn(b, row, col++, currPiece != 'P'));
                    break;
                case 'k':
                case 'K':
                    b.setPiece(row, col, new King(b, row, col++, currPiece != 'K'));
                    break;
                case 'q':
                case 'Q':
                    b.setPiece(row, col, new Queen(b, row, col++, currPiece != 'Q'));
                    break;
                case 'r':
                case 'R':
                    b.setPiece(row, col, new Rook(b, row, col++, currPiece != 'R'));
                    break;
                case 'b':
                case 'B':
                    b.setPiece(row, col, new Bishop(b, row, col++, currPiece != 'B'));
                    break;
                case 'n':
                case 'N':
                    b.setPiece(row, col, new Knight(b, row, col++, currPiece != 'N'));
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