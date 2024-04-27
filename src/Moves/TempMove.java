package Moves;

public class TempMove {
    public int rowIncrement;
    public int colIncrement;
    public Position direction;

    public TempMove(int i, int j) {
        rowIncrement = i;
        colIncrement = j;
        direction = null;
    }

    public TempMove(int i, int j, Position direction) {
        rowIncrement = i;
        colIncrement = j;
        this.direction = direction;
    }
}
