package Pieces;

import Moves.Position;
import Moves.TempMove;

import java.util.ArrayList;
import java.util.List;

public class BlackPawn extends Pawn {
    public BlackPawn(int i, int j) {
        super(i, j);
        isWhite = false;
    }

    @Override
    public List<TempMove> getTempMoves() {
        List<TempMove> result = new ArrayList<>();
        result.add(new TempMove(-1, 0));
        if (position.row == 6) {
            result.add(new TempMove(-2, 0, new Position(-1, 0)));
        }
        result.add(new TempMove(-1, -1));
        result.add(new TempMove(-1, 1));
        return result;
    }
}
