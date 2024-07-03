package videogame;

import java.util.Scanner;

/**
 * A fő metódus, amely elindítja a játékot.
 */
public class Main {
    public static void main(String[] args) {
        FigurineChecker figurineChecker = new FigurineChecker();
        Scanner scanner = new Scanner(System.in);
        String possibleMoves = "0,1,2,3";

        while(!figurineChecker.isSolved() && !figurineChecker.isStuck()) {
            System.out.println("Jelenlegi poziciója! " + figurineChecker.getData());
            System.out.println("Hova mozoghat: " + figurineChecker.getLegalMoves());
            System.out.println("Kérem adjon meg egy számot. (0 - Up, 1 - Right, 2 - Down, 3 - Left)");
            String move = scanner.next();

            if (possibleMoves.contains(move) && !move.contains(",")){
                int iMove = Integer.parseInt(move);
                move = String.valueOf(Direction.fromValue(iMove));

                if (figurineChecker.isLegalMove(move)){
                    figurineChecker.makeMove(move);
                    System.out.println("Az ön megadott értéke megfelelő.");
                    System.out.println();
                }
                else
                {
                    System.out.println("Az ön megadott értéke nem volt megfelelő.");
                    System.out.println();
                }
            }
            else {
                System.out.println("Az ön megadott értéke nem volt megfelelő.");
                System.out.println();
            }
        }

        if (figurineChecker.isSolved()){
            System.out.println("You win!");
        }
        else
        {
            System.out.println("You got to a point from where you cannot go anywhere else.");
            System.out.println("You lost!");
        }
    }
}
