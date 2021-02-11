package com.company.controller;
import com.company.model.GameOfLifeBoard;
import com.company.model.GameState;
import com.company.model.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class GameOfLifeController {

    private final GameOfLifeBoard gameOfLifeBoard;
    private GameState gameState;
    private static final Logger log = LoggerFactory.getLogger(GameOfLifeController.class);

    public GameOfLifeController(int rows, int columns) {
        gameOfLifeBoard = new GameOfLifeBoard(rows,columns);
        gameState = GameState.PAUSED;
    }

    public void setStartingPositions(ArrayList<Pair> startingAlivePositions){
        startingAlivePositions.forEach(pair -> {
            try {
                gameOfLifeBoard.setStartingPosition(pair.getX(), pair.getY());
            } catch (IndexOutOfBoundsException e) {
                log.error("Index out of bounds");
                log.error(String.valueOf(e));
            }
        });
    }

    public void update() {

        //Middle hand board - all cells need to be updated at once
        boolean[][] newBoard = new boolean[gameOfLifeBoard.getBoardRows()][gameOfLifeBoard.getBoardColumns()];

        for(int i=0;i<gameOfLifeBoard.getBoardRows();i++) {
            for(int j=0;j<gameOfLifeBoard.getBoardColumns();j++) {

                //get nr of alive neighbours for every cell
                int nrAliveNeighbours = nrOfAliveNeighbours(i,j);

                //if live cell
                if(gameOfLifeBoard.isCellAlive(i,j))
                {   //die by underpopulation (<2) or overpopulation (>3)
                    if(nrAliveNeighbours == 2 || nrAliveNeighbours == 3) {
                        newBoard[i][j] = true;
                    } else {
                        newBoard[i][j] = false;
                    }
                }
                else {
                    //if dead cell
                    if(nrAliveNeighbours == 3) {
                        newBoard[i][j] = true;
                    } else {
                        newBoard[i][j] = false;
                    }
                }
            }
        }
        gameOfLifeBoard.setBoard(newBoard);
    }

    private int nrOfAliveNeighbours(int x, int y) {
        int sum = 0;

        boolean upInbounds = y-1>=0;
        boolean downInbounds = y+1<gameOfLifeBoard.getBoardColumns();
        boolean leftInbounds = x-1>=0;
        boolean rightInbounds = x+1<gameOfLifeBoard.getBoardRows();

        if(upInbounds) {
            if(gameOfLifeBoard.isCellAlive(x, y-1)) {
                sum++;
            }
        }

        if(downInbounds) {
            if(gameOfLifeBoard.isCellAlive(x,y+1)) {
                sum++;
            }
        }

        if(leftInbounds) {
            if(gameOfLifeBoard.isCellAlive(x-1, y)) {
                sum++;
            }
        }

        if(rightInbounds) {
            if(gameOfLifeBoard.isCellAlive(x+1, y)) {
                sum++;
            }
        }

        if(downInbounds && rightInbounds) {
            if(gameOfLifeBoard.isCellAlive(x+1,y+1)) {
                sum++;
            }
        }

        if(downInbounds && leftInbounds) {
            if(gameOfLifeBoard.isCellAlive(x-1,y+1)) {
                sum++;
            }
        }

        if(upInbounds && rightInbounds) {
            if(gameOfLifeBoard.isCellAlive(x+1,y-1)) {
                sum++;
            }
        }

        if(upInbounds && leftInbounds) {
            if(gameOfLifeBoard.isCellAlive(x-1,y-1)) {
                sum++;
            }
        }
        return sum;
    }

    public boolean[][] getBoard() {
        return gameOfLifeBoard.getBoard();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    //for an interactive view where game can be paused and re-started
    public void toggleGameState() {
        switch (this.gameState) {
            case RUNNING:
                this.gameState = GameState.PAUSED;
                break;
            case PAUSED:
                this.gameState = GameState.RUNNING;
                break;
        }
    }
}
