import Pieces.Piece;

public class Square {
    public Piece piece;
    public Integer row;
    public Integer col;
    public boolean isBlack() {
        return (row + col) % 2 == 0;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Square(int i, int j) {
        row = i;
        col = j;
        piece = null;
    }

    public void addPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }
}
