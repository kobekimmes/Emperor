package kobekimmes.com.emperor;


import java.util.ArrayList;

class MinMax extends Bot {

    double alpha;
    double beta;

    MinMax() {
        alpha = Double.NEGATIVE_INFINITY;
        beta = Double.POSITIVE_INFINITY;
    }
    double performSearch(Game game, boolean isBlackTurn, int depth) {
        if (game.isGameOver() || depth == 2) {
            //System.out.println("best move: " + nextPiece + ", " + nextPosition);
            System.out.println(evaluateBoard(game.chessBoard));
            return evaluateBoard(game.chessBoard);
        }
        else {
            bestVal = isBlackTurn ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            ArrayList<Piece> pieces = isBlackTurn ? game.chessBoard.blackPieces : game.chessBoard.whitePieces;
            //System.out.println(pieces);
            game.produceLegalMovesForGameState(isBlackTurn);
                    //System.out.println(p + ": " + p.legalMoves);
            for (Piece p : pieces) {
                for (Position pos : p.legalMoves) {
                    Position oldPos = p.pos;
                    p.movePiece(pos, isBlackTurn);
                    double score = performSearch(game, !isBlackTurn, depth + 1);
                    p.setPos(oldPos);

                    nextPiece = isBlackTurn ? (score < bestVal ? p : nextPiece) : (score > bestVal ? p : nextPiece);
                    nextPosition = isBlackTurn ? (score < bestVal ? pos : nextPosition) : (score > bestVal ? pos : nextPosition);
                    bestVal = isBlackTurn ? Math.min(bestVal, score) : Math.max(bestVal, score);

                    //System.out.println(nextPiece + ", " + nextPosition + " Depth = " + depth);

                    if (isBlackTurn) {
                        beta = Math.min(beta, bestVal);
                    } else {
                        alpha = Math.max(alpha, bestVal);

                    }
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
                return  bestVal;
        }
    }

    @Override
    void produceMove(Game game) {
        performSearch(game, true, 0);
    }
}
