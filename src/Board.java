import Moves.Move;
import Moves.Position;
import Pieces.*;

import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Board extends Application {
    private final Square[][] board = new Square[8][8];
    private final Set<Piece> allWhitePieces = new HashSet<>();
    private final Set<Piece> allBlackPieces = new HashSet<>();
    private Integer enPassantCol = null;
    private boolean madeEnPassant = false;

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

        for (int i = 0; i < 8; i++) {
            allWhitePieces.add(board[0][i].getPiece());
            allWhitePieces.add(board[1][i].getPiece());
            allBlackPieces.add(board[6][i].getPiece());
            allBlackPieces.add(board[7][i].getPiece());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    // Converts input string position to actual position. i.e C1 to 0, 2
    private Position stringToPosition(String str) {
        return new Position(str.charAt(1) - '1', str.charAt(0) - 'A');
    }

    // TODO: Implement castle
    public boolean checkInput(String[] input, boolean isWhite) {
        Character piece = input[0].charAt(0);
        String start = input[1];
        String end = input[2];
        System.out.println(start);
        Position startPosition = stringToPosition(start);
        System.out.println(startPosition.row);
        System.out.println(startPosition.col);
        Position endPosition = stringToPosition(end);
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
            // en Passant check
            if (enPassantCol != null && endPosition.col == enPassantCol) {
                if (movingPiece.isWhite() && endPosition.row == 5) {
                    madeEnPassant = true;
                    return true;
                }
                if (!movingPiece.isWhite() && endPosition.row == 3) {
                    madeEnPassant = true;
                    return true;
                }
            }
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
        return true;
    }

    private boolean underCheckAfterMove(Move move) {
        return false;
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
        Piece endPiece = board[move.endPosition.row][move.endPosition.col].getPiece();
        if (endPiece != null) {
            if (endPiece.isWhite()) {
                allWhitePieces.remove(endPiece);
            } else {
                allBlackPieces.remove(endPiece);
            }
        }
        board[move.endPosition.row][move.endPosition.col].addPiece(move.getPiece());
        // update for en passant
        // TODO: add verification
        if (madeEnPassant) {
            if (move.piece.isWhite()) {
                allBlackPieces.remove(board[5][enPassantCol].getPiece());
                board[5][enPassantCol].removePiece();
            } else {
                allWhitePieces.remove(board[2][enPassantCol].getPiece());
                board[2][enPassantCol].removePiece();
            }
            madeEnPassant = false;
        }
        enPassantViableCheck(move);
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
        System.out.print("    ");
        for (char i = 'A'; i <= 'H'; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }

    private void enPassantViableCheck(Move move) {
        if (move.getPiece() instanceof Pawn) {
            if (Math.abs(move.endPosition.row - move.startPosition.row) == 2) {
                enPassantCol = move.startPosition.col;
            }
        } else {
            enPassantCol = null;
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
