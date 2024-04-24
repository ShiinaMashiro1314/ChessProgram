package Pieces;

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
        if (row != 7) {
            result.add(new TempMove(1, 0));
        }
        if (row == 1) {
            result.add(new TempMove(2, 0));
        }
        return result;
    }
}
