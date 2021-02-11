package model;

import com.company.model.GameOfLifeBoard;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameOfLifeBoardTest {

    private final GameOfLifeBoard gameOfLifeBoard = new GameOfLifeBoard(3,3);

    @Test
    public void testToggleThrows() {

        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLifeBoard.toggleCell(30,2));
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLifeBoard.toggleCell(2,20));
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLifeBoard.toggleCell(-1,2));
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLifeBoard.toggleCell(0,-15));

    }

    @Test
    public void testToggleCell() throws IndexOutOfBoundsException {

        gameOfLifeBoard.toggleCell(0,0);
        assertTrue(gameOfLifeBoard.isCellAlive(0,0));

        gameOfLifeBoard.toggleCell(1,1);
        gameOfLifeBoard.toggleCell(1,1);
        assertFalse(gameOfLifeBoard.isCellAlive(1,1));

    }

    @Test
    public void testSetStartingPosition() throws IndexOutOfBoundsException {
        assertFalse(gameOfLifeBoard.isCellAlive(0,2));
        gameOfLifeBoard.setStartingPosition(0,2);
        assertTrue(gameOfLifeBoard.isCellAlive(0,2));

    }

    @Test
    public void testIsCellAlive() {

        gameOfLifeBoard.getBoard()[0][0] = true;
        assertTrue(gameOfLifeBoard.isCellAlive(0,0));
        assertFalse(gameOfLifeBoard.isCellAlive(0,1));
    }
}
