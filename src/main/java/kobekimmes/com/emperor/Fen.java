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
                    b.setPiece(row, col, new Pawn(b, row, col++, currPiece != 'p'));
                    break;
                case 'k':
                case 'K':
                    b.setPiece(row, col, new King(b, row, col++, currPiece != 'k'));
                    break;
                case 'q':
                case 'Q':
                    b.setPiece(row, col, new Queen(b, row, col++, currPiece != 'q'));
                    break;
                case 'r':
                case 'R':
                    b.setPiece(row, col, new Rook(b, row, col++, currPiece != 'r'));
                    break;
                case 'b':
                case 'B':
                    b.setPiece(row, col, new Bishop(b, row, col++, currPiece != 'b'));
                    break;
                case 'n':
                case 'N':
                    b.setPiece(row, col, new Knight(b, row, col++, currPiece != 'n'));
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