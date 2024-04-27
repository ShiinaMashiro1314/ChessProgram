package Moves;

public class Position {
    public int row;
    public int col;

    public Position(int i, int j) {
        row = i;
        col = j;
    }

    public Position increament(Position position) {
        return new Position(this.row + position.row, this.col + position.col);
    }

    public Position multiply(int i) {
        return new Position(this.row * i, this.col * i);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }
}
