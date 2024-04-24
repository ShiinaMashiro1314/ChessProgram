package Pieces;

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
        if (row == 6) {
            result.add(new TempMove(-2, 0));
        }
        return result;
    }
}
