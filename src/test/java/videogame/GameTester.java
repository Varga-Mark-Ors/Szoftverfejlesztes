package videogame;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GameTester {

    @Test
    public void testDirectionValues() {
        assertEquals(0, Direction.UP.getValue());
        assertEquals(1, Direction.RIGHT.getValue());
        assertEquals(2, Direction.DOWN.getValue());
        assertEquals(3, Direction.LEFT.getValue());
    }

    @Test
    public void testFromValue() {
        assertEquals(Direction.UP, Direction.fromValue(0));
        assertEquals(Direction.RIGHT, Direction.fromValue(1));
        assertEquals(Direction.DOWN, Direction.fromValue(2));
        assertEquals(Direction.LEFT, Direction.fromValue(3));
    }

    @Test
    public void testInvalidDirectionValue() {
        assertThrows(IllegalArgumentException.class, () -> Direction.fromValue(4));
        assertThrows(IllegalArgumentException.class, () -> Direction.fromValue(-1));
    }

    @Test
    public void testMapInitialization() {
        Map map = new Map();
        char[][] expectedMap = {
                {'b','w','w','w','r','w','c'},
                {'b','w','b','w','w','b','w'},
                {'w','r','b','r','w','w','w'},
                {'b','b','b','w','b','w','w'},
                {'r','r','w','r','r','w','w'},
                {'w','w','b','r','w','w','r'},
                {'w','w','r','r','w','w','w'},
        };

        assertArrayEquals(expectedMap, map.getMap());
    }

    @Test
    public void testGetData() {
        FigurineChecker checker = new FigurineChecker();
        String expectedData = "Col:0 Row:6 Color:w";
        assertEquals(expectedData, checker.getData());
    }

    @Test
    public void testIsStuck() {
        FigurineChecker checker = new FigurineChecker();
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        assertTrue(checker.isStuck());
        FigurineChecker checker2 = new FigurineChecker();
        checker2.makeMove("UP");
        checker2.makeMove("UP");
        assertFalse(checker2.isStuck());
    }

    @Test
    public void testIsLegalMove() {
        FigurineChecker checker = new FigurineChecker();
        assertTrue(checker.isLegalMove("UP"));
        assertFalse(checker.isLegalMove("LEFT"));
    }

    @Test
    public void testInBoundaries() {
        FigurineChecker checker = new FigurineChecker();
        assertTrue(checker.inBoundaries(Direction.UP));
        assertFalse(checker.inBoundaries(Direction.LEFT));
    }

    @Test
    public void testGetLegalMoves() {
        FigurineChecker checker = new FigurineChecker();
        Set<String> legalMoves = checker.getLegalMoves();
        assertTrue(legalMoves.contains("UP"));
        assertFalse(legalMoves.contains("LEFT"));
    }

    @Test
    public void testClone() {
        FigurineChecker checker = new FigurineChecker();
        FigurineChecker clone = (FigurineChecker) checker.clone();
        assertEquals(checker.getData(), clone.getData());
        clone.makeMove("UP");
        assertNotEquals(checker.getData(), clone.getData());
    }

    @Test
    public void testMain() {
        String input = "0\n0\n0\n0\n0\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("You got to a point from where you cannot go anywhere else."));
    }

    @Test
    public void testIsSolved() {
        FigurineChecker checker = new FigurineChecker();
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("RIGHT");
        checker.makeMove("RIGHT");
        checker.makeMove("RIGHT");
        checker.makeMove("DOWN");
        checker.makeMove("DOWN");
        checker.makeMove("LEFT");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("LEFT");
        checker.makeMove("LEFT");
        checker.makeMove("DOWN");
        checker.makeMove("DOWN");
        checker.makeMove("RIGHT");
        checker.makeMove("RIGHT");
        checker.makeMove("RIGHT");
        checker.makeMove("RIGHT");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("UP");
        checker.makeMove("RIGHT");
        checker.makeMove("RIGHT");

        assertTrue(checker.isSolved());
    }
}
