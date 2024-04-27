import Moves.Move;
import Moves.Position;
import Pieces.*;

import java.util.HashSet;
import java.util.Set;

public class Board {
    public Square[][] board = new Square[8][8];
    public Set<Piece> allPieces = new HashSet<>();
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

    public boolean checkInput(String[] input, boolean isWhite) {
        Character piece = input[0].charAt(0);
        Position startPosition = new Position(Integer.valueOf(input[1]) - 1,
                Integer.valueOf(input[2]) - 1);
        Position endPosition = new Position(Integer.valueOf(input[3]) - 1,
                Integer.valueOf(input[4]) - 1);
        Piece startPiece = getPiece(startPosition);

        if (startPiece != null && startPiece.getImage() == piece && startPiece.isWhite() == isWhite) {
            for (Move move : startPiece.getMoves()) {
                if (move.startPosition.equals(startPosition) && move.endPosition.equals(endPosition)) {
                    return takeMove(move);
                }
            }
        } else {
            if (startPiece == null) {
                System.out.print("No Piece at the starting position");
            } else if (startPiece.getImage() != piece) {
                System.out.printf("Wrong Piece selected. You selected %s, but was %s", piece, startPiece.getImage());
            } else {
                System.out.println("You selected your Opponent's Piece");
            }
        }
        return false;
    }

    private Piece getPiece(Position position) {
        return board[position.row][position.col].getPiece();
    }

    public boolean takeMove(Move move) {
        if (isValidMove(move)) {
            makeMove(move);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkForWin() {
        return false;
    }

    private boolean isValidMove(Move move) {
        Piece movingPiece =  move.getPiece();
        Position startPosition = move.getStartPosition();
        Position endPosition = move.getEndPosition();
        Piece pieceOnEndPosition = board[endPosition.row][endPosition.col].getPiece();

        // check if landing on piece with same color
        if (checkSameColor(pieceOnEndPosition, movingPiece.isWhite())) {
            return false;
        }

        // For Pawn, check for takes
        if (movingPiece.getImage() == 'P' && endPosition.col != startPosition.col) {
            return checkDifferentColor(pieceOnEndPosition, movingPiece.isWhite());
        }

        // TODO: Can we improve this?
        // check if move is blocked by other piece
        if (move.direction != null)  {
            for (int i = 1; i < 8; i++) {
                Position positionToCheck = startPosition.increament(move.direction.multiply(i));
                if (positionToCheck.equals(endPosition)) {
                    break;
                }

                if (board[positionToCheck.row][positionToCheck.col].hasPiece()) {
                    return false;
                }
            }
        }
        // TODO: How to check for checks?
        return true;
    }

    private boolean checkSameColor(Piece pieceOnEndPosition, boolean isWhite) {
        return pieceOnEndPosition != null && pieceOnEndPosition.isWhite() == isWhite;
    }

    private boolean checkDifferentColor(Piece pieceOnEndPosition, boolean isWhite) {
        return pieceOnEndPosition != null && pieceOnEndPosition.isWhite() != isWhite;
    }

    private void makeMove(Move move) {
        move.getPiece().updatePosition(move.endPosition);
        board[move.startPosition.row][move.startPosition.col].removePiece();
        board[move.endPosition.row][move.endPosition.col].addPiece(move.getPiece());
    }

    public void drawBoard() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i+1);
            System.out.print("   ");
            for (int j = 0; j <= 7; j++) {
                drawSquare(board[i][j]);
            }
            System.out.println();
        }
    }

    private void drawSquare(Square square) {
        if (square.hasPiece()) {
            System.out.print(" " + square.getPiece().getImage() + " ");
        } else {
            System.out.print("   ");
        }
    }
}
