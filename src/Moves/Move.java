package Moves;

import Pieces.Piece;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Move {
    public Piece piece;
    public Position startPosition;
    public Position endPosition;
    public Position direction;
}
