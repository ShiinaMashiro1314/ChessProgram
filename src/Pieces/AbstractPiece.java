package Pieces;

import Moves.TempMove;
import Moves.Position;
import Moves.Move;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractPiece implements Piece {
    boolean isWhite;
    boolean isBlockable;
    Position position;

    private Move convertToMove(TempMove tempMove) {
        return Move.builder()
                .piece(this)
                .startPosition(position)
                .endPosition(new Position(position.row + tempMove.rowIncrement,
                        position.col + tempMove.colIncrement))
                .build();
    }

    private boolean withinRange(Move move) {
        return 0 <= move.endPosition.row && 0 <= move.endPosition.col
                && move.endPosition.row <= 7 && move.endPosition.col <=7;
    }

    @Override
    public List<Move> getMoves() {
        return getTempMoves().stream()
                .map(this::convertToMove)
                .filter(this::withinRange)
                .collect(Collectors.toList());
    }

    protected abstract List<TempMove> getTempMoves();

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void updatePosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public boolean isBlockable() {
        return isBlockable;
    }
}


