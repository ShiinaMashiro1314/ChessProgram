package Pieces;

import Moves.Position;
import Moves.TempMove;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {

    public Queen(boolean isWhite, int i, int j) {
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
            tempMoves.add(new TempMove(i, 0, new Position(1, 0)));
            tempMoves.add(new TempMove(-i, 0, new Position(-1, 0)));
            tempMoves.add(new TempMove(0, i, new Position(0, 1)));
            tempMoves.add(new TempMove(0, -i, new Position(0, -1)));
        }
        return tempMoves;
    }

    @Override
    public Character getImage() {
        return 'Q';
    }
}
