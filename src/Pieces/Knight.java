package Pieces;

import Moves.Position;
import Moves.TempMove;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {

    public Knight(boolean isWhite, int i, int j) {
        this.isWhite = isWhite;
        this.isBlockable = false;
        this.position = new Position(i, j);
    }

    @Override
    public List<TempMove> getTempMoves() {
        List<TempMove> tempMoves = new ArrayList<>();
        tempMoves.add(new TempMove(2, 1));
        tempMoves.add(new TempMove(2, -1));
        tempMoves.add(new TempMove(-2, 1));
        tempMoves.add(new TempMove(-2, -1));
        tempMoves.add(new TempMove(1, 2));
        tempMoves.add(new TempMove(1, -2));
        tempMoves.add(new TempMove(-1, 2));
        tempMoves.add(new TempMove(-1, -2));
        return tempMoves;
    }

    @Override
    public Image getImage() {
        return null;
    }
}
