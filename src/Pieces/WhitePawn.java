package Pieces;

import Moves.Position;
import Moves.TempMove;

import java.util.ArrayList;
import java.util.List;

public class WhitePawn extends Pawn {
    public WhitePawn(int i, int j) {
        super(i, j);
        isWhite = true;
    }

    @Override
    public List<TempMove> getTempMoves() {
        List<TempMove> result = new ArrayList<>();
        if (position.row != 7) {
            result.add(new TempMove(1, 0));
        }
        if (position.row == 1) {
            result.add(new TempMove(2, 0, new Position(1, 0)));
        }
        result.add(new TempMove(1, -1));
        result.add(new TempMove(1, 1));
        return result;
    }
}
