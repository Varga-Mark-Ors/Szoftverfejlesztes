package solver;

import puzzle.solver.BreadthFirstSearch;
import videogame.FigurineChecker;

public class MainSolver {
    public static void main(String[] args){
        new BreadthFirstSearch<String>().solveAndPrintSolution(new FigurineChecker());
    }
}
