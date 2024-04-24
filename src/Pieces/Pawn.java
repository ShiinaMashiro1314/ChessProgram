package Pieces;

import Moves.Position;

public abstract class Pawn extends AbstractPiece {
    public Pawn(int i, int j) {
        this.position = new Position(i, j);
        isBlockable = true;
    }

    @Override
    public Image getImage() {
        return null;
    }
}
