package controller;

import com.company.controller.GameOfLifeController;
import com.company.model.GameState;
import com.company.model.Pair;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;

public class GameOfLifeControllerTest {

    private final GameOfLifeController gameOfLifeController = new GameOfLifeController(3,3);


    @Test
    public void testSetStartingPos() {
        ArrayList<Pair> startingPositions = new ArrayList<>();
        startingPositions.add(new Pair(1,0));
        startingPositions.add(new Pair(1,1));
        startingPositions.add(new Pair(1,2));
        gameOfLifeController.setStartingPositions(startingPositions);

        assertTrue(gameOfLifeController.getBoard()[1][0]);
        assertTrue(gameOfLifeController.getBoard()[1][0]);
        assertTrue(gameOfLifeController.getBoard()[1][0]);

    }

    @Test
    public void testToggleGameState() {

        gameOfLifeController.toggleGameState();
        assertTrue(gameOfLifeController.getGameState() == GameState.RUNNING);
    }

    @Test
    public void testOneTickUpdate() {

        ArrayList<Pair> startingPositions = new ArrayList<>();
        startingPositions.add(new Pair(1,0));
        startingPositions.add(new Pair(1,1));
        startingPositions.add(new Pair(1,2));
        gameOfLifeController.setStartingPositions(startingPositions);

        gameOfLifeController.update();

        boolean[][] board = gameOfLifeController.getBoard();
        boolean aliveColumnOfCells = board[0][1] && board[1][1] && board[2][1];
        assertTrue(aliveColumnOfCells);

        // 0    1    2
        //(0)  (1)  (2)
        // 0    1    2

        // --->

        // 0  (1)   2
        // 0  (1)   2
        // 0  (1)   2
    }

}
