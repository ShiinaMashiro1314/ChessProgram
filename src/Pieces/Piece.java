package Pieces;

import Moves.Position;
import Moves.Move;

import java.util.List;

public interface Piece {
    public List<Move> getMoves();

    public boolean isWhite();

    public boolean isBlockable();

    public Character getImage();

    public Position getPosition();

    public void updatePosition(Position position);
}
