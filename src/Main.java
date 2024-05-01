import Moves.Move;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Board board = new Board();
        board.drawBoard();
        boolean isWhiteMove = true;

        Scanner s = new Scanner(System.in);
        String pattern = "[PRBKNQ] [A-H][1-8] [A-H][1-8]";

        System.out.println('C' - 'A');
        while (true) {
            if (isWhiteMove) {
                System.out.println("White's turn to move!");
            } else {
                System.out.println("Black's turn to move!");
            }
            System.out.println("Enter your move in the format: Piece startPosition endPosition: " + pattern);
            String line = s.nextLine();
            if (line.matches(pattern)) {
                String[] input = line.split(" ");
                if (!board.checkInput(input, isWhiteMove)) {
                    System.out.println("Illegal Move, try again");
                } else {
                    isWhiteMove = !isWhiteMove;
                }
                board.drawBoard();
                if (board.checkForWin()) {
                    System.out.println((isWhiteMove ? "White" : "Black") + " won! Congratulations!!");
                    break;
                }
            } else {
                System.out.println("Wrong input format, try again");
            }

        }
    }
}