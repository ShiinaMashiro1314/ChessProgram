import Moves.Move;
import Pieces.*;

public class Board {
    public Square[][] board = new Square[8][8];
    public Board() {
        // Create all squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
            }
        }

        // Setup pieces on initial board
        for (int i = 0; i < 8; i++) {
            board[1][i].addPiece(new WhitePawn(1, i));
            board[6][i].addPiece(new BlackPawn(6, i));
        }

        for (int i = 0; i < 2; i++) {
            boolean isWhite = i == 0;
            int row = isWhite ? 0 : 7;
            board[row][0].addPiece(new Rook(isWhite, row, 0));
            board[row][7].addPiece(new Rook(isWhite, row, 7));
            board[row][1].addPiece(new Knight(isWhite, row, 1));
            board[row][6].addPiece(new Knight(isWhite, row, 6));
            board[row][2].addPiece(new Bishop(isWhite, row, 2));
            board[row][5].addPiece(new Bishop(isWhite, row, 5));
            board[row][3].addPiece(new Queen(isWhite, row, 3));
            board[row][4].addPiece(new King(isWhite, row, 4));
        }
    }

    public boolean takeMove(Move move) {
        if (isValidMove(move)) {
            makeMove(move);
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidMove(Move move) {
        return true;
    }

    private void makeMove(Move move) {
        move.getPiece().updatePosition(move.endPosition);
        board[move.startPosition.row][move.endPosition.col].removePiece();
        board[move.endPosition.row][move.endPosition.col].addPiece(move.getPiece());
    }

    public void drawBoard() {

    }
}
