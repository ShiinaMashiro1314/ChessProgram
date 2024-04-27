package Pieces;

import Moves.Position;
import Moves.TempMove;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {

    public King(boolean isWhite, int i, int j) {
        this.isWhite = isWhite;
        this.isBlockable = true;
        this.position = new Position(i, j);
    }
    @Override
    public List<TempMove> getTempMoves() {
        List<TempMove> tempMoves = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            tempMoves.add(new TempMove(i, 0));
            tempMoves.add(new TempMove(-i, 0));
            tempMoves.add(new TempMove(0, i));
            tempMoves.add(new TempMove(0, -i));
            tempMoves.add(new TempMove(i, i));
            tempMoves.add(new TempMove(-i, -i));
            tempMoves.add(new TempMove(-i, i));
            tempMoves.add(new TempMove(i, -i));
        }
        return tempMoves;
    }

    @Override
    public Character getImage() {
        return 'K';
    }
}
