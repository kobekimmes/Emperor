package kobekimmes.com.emperor;

class Position {

    int row, col;
    int boardRow, boardCol;

    public Position(int _row, int _col) {
        this.row = _row;
        this.col = _col;
        
        this.boardRow = 8 - this.row;
        this.boardCol = this.col + 1;
        
    }
    public String toString() {
        return "{ " + this.boardRow + "" + (char) (this.boardCol + 96) + " }";
    }

    public boolean equals(Position other) {
        return other.row == this.row && other.col == this.col;
    }
}