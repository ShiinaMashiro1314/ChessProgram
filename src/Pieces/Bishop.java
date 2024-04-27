package Pieces;

import Moves.Position;
import Moves.TempMove;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(boolean isWhite, int i, int j) {
        this.isWhite = isWhite;
        this.isBlockable = true;
        this.position = new Position(i, j);
    }

    @Override
    public List<TempMove> getTempMoves() {
        List<TempMove> tempMoves = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            tempMoves.add(new TempMove(i, i, new Position(1, 1)));
            tempMoves.add(new TempMove(-i, -i, new Position(-1, -1)));
            tempMoves.add(new TempMove(-i, i, new Position(-1, 1)));
            tempMoves.add(new TempMove(i, -i, new Position(1, -1)));
        }
        return tempMoves;
    }

    @Override
    public Character getImage() {
        return 'B';
    }
}
