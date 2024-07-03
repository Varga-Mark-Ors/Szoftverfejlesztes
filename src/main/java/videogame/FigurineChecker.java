package videogame;

import puzzle.State;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A FigurineChecker osztály a ToyFigurine mozgását és állapotát ellenőrzi a játéktáblán.
 * Implementálja a State interfészt, amely a játék állapotának kezelését segíti.
 */
public class FigurineChecker implements State<String> {

    private ToyFigurine figurine = new ToyFigurine();
    private final Map board = new Map();
    /**
     * Lehetséges mozgások
     */
    String possibleMoves = "UP, RIGHT, DOWN, LEFT";

    /**
     * Visszaadja a figura jelenlegi állapotát.
     *
     * @return A figura oszlop, sor és szín információi.
     */
    public String getData() {
        return "Col:" +
                figurine.getCol() +
                " Row:" +
                figurine.getRow() +
                " Color:" +
                board.getMap()[figurine.getRow()][figurine.getCol()];
    }

    /**
     * Ellenőrzi, hogy a figura beragadt-e, azaz nincs-e legális lépése.
     *
     * @return Igaz, ha a figura beragadt, különben hamis.
     */
    public boolean isStuck() {
        Set<String> legalMoves = getLegalMoves();
        return legalMoves.isEmpty() || "[]".equals(legalMoves.toString());
    }

    /**
     * Ellenőrzi, hogy a játék meg van-e oldva, azaz a figura elérte-e a célpozíciót.
     *
     * @return Igaz, ha a figura elérte a célpozíciót, különben hamis.
     */
    @Override
    public boolean isSolved() {
        return figurine.getRow() == 0 && figurine.getCol() == 6;
    }

    /**
     * Ellenőrzi, hogy egy adott mozgás legális-e.
     *
     * @param s A mozgás iránya.
     * @return Igaz, ha a mozgás legális, különben hamis.
     */
    @Override
    public boolean isLegalMove(String s) {
        String legalMoves = getLegalMoves().toString();
        return legalMoves.contains(s);
    }

    /**
     * Végrehajt egy adott mozgást a figurával.
     *
     * @param s A mozgás iránya.
     */
    @Override
    public void makeMove(String s) {
        switch (s){
            case "UP":
                figurine.setRow(figurine.getRow() - 1);
                figurine.setDirection(Direction.UP);
                break;
            case "RIGHT":
                figurine.setCol(figurine.getCol() + 1);
                figurine.setDirection(Direction.RIGHT);
                break;
            case "DOWN":
                figurine.setRow(figurine.getRow() + 1);
                figurine.setDirection(Direction.DOWN);
                break;
            case "LEFT":
                figurine.setCol(figurine.getCol() - 1);
                figurine.setDirection(Direction.LEFT);
                break;
        }
    }

    /**
     * Ellenőrzi, hogy egy adott irányban történő mozgás a táblán belül van-e.
     *
     * @param direction Az irány.
     * @return Igaz, ha a mozgás a táblán belül van, különben hamis.
     */
    public boolean inBoundaries(Direction direction){
        return switch (direction) {
            case UP -> figurine.getRow() - 1 >= 0;
            case LEFT -> figurine.getCol() - 1 >= 0;
            case RIGHT -> figurine.getCol() + 1 <= 6;
            case DOWN -> figurine.getRow() + 1 <= 6;
        };
    }

    /**
     * Visszaadja a figura aktuális pozíciójából legális mozgásokat.
     *
     * @return A legális mozgások halmaza.
     */
    @Override
    public Set<String> getLegalMoves() {
        char color = board.getMap()[figurine.getRow()][figurine.getCol()];
        Direction direction = figurine.getDirection();
        Set<String> legalMoves = new HashSet<>();

        switch (color){
            case ('r'):
                if (inBoundaries(direction)) {
                    legalMoves.add(direction.toString());
                }
                if (direction == Direction.LEFT) {
                    if (inBoundaries(Direction.UP)){
                        legalMoves.add(Direction.UP.toString());
                    }
                } else {
                    if (inBoundaries(Direction.values()[(direction.getValue() + 1)])){
                        legalMoves.add(Direction.values()[(direction.getValue() + 1)].toString());
                    }
                }
                break;
            case ('b'):
                if (inBoundaries(direction)) {
                    legalMoves.add(direction.toString());
                }
                if (direction == Direction.UP) {
                    if (inBoundaries(Direction.LEFT)){
                        legalMoves.add(Direction.LEFT.toString());
                    }
                } else {
                    if (inBoundaries(Direction.values()[(direction.getValue() - 1)])){
                        legalMoves.add(Direction.values()[(direction.getValue() - 1)].toString());
                    }
                }
                break;
            case ('w'):
                if (inBoundaries(direction)) {
                    legalMoves.add(direction.toString());
                }
                break;
        }
        return legalMoves;
    }

    /**
     * Klónozza az aktuális állapotot.
     *
     * @return A klónozott állapot.
     */
    @Override
    public State<String> clone() {
        FigurineChecker copy = new FigurineChecker();
        copy.figurine = new ToyFigurine(figurine.getRow(), figurine.getCol(), figurine.getDirection());
        return copy;
    }

    /**
     * Egyenlőségvizsgálat két FigurineChecker objektum között.
     *
     * @param o Az objektum, amivel összehasonlítjuk.
     * @return Igaz, ha az objektumok egyenlők, különben hamis.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FigurineChecker that = (FigurineChecker) o;
        return figurine.equals(that.figurine) && board.equals(that.board) && possibleMoves.equals(that.possibleMoves);
    }

    /**
     * Visszaadja az objektum hash kódját.
     *
     * @return Az objektum hash kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(figurine, board, possibleMoves);
    }
}

