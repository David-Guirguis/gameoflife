package com.company.view;
import com.company.controller.GameOfLifeController;
import com.company.model.GameState;

public class GameOfLifeView {

    private final GameOfLifeController gameOfLifeController;
    private final String BLACKSQUARE = "\u25A0"+" ";
    private final String WHITESQUARE = "\u25A1"+" ";

    public GameOfLifeView(GameOfLifeController gameOfLifeController) {
        this.gameOfLifeController = gameOfLifeController;
    }

    public void run() {

        gameOfLifeController.setGameState(GameState.RUNNING);

        while (gameOfLifeController.getGameState() == GameState.RUNNING) {
            boolean[][] gameBoard = gameOfLifeController.getBoard();

            clearScreen();
            printBoard(gameBoard);
            gameOfLifeController.update();
            sleep(800);
        }

    }

    private void printBoard(boolean[][] gameBoard) {

        for(int i=0;i<gameBoard.length;i++) {
            for(int j=0;j<gameBoard[0].length;j++) {
                if(gameBoard[i][j]) {
                    System.out.print(WHITESQUARE);
                } else {
                    System.out.print(BLACKSQUARE);
                }
            }
            System.out.println();
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //toggle PAUSE/RUNNING if using JavaFX or something else interactive
    private void toggleGameState() {
        gameOfLifeController.toggleGameState();
    }

    private void clearScreen() {
        for (int i=0;i<15;i++) {
            System.out.println();
        }
    }
}
