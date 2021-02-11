package com.company.model;

public class GameOfLifeBoard {
    private boolean[][] board;
    private final int  rows, columns;
    //not used, but has everything needed to be used in an interactive view
    private GameState gameState;

    public GameOfLifeBoard(int rows, int columns) {
        if(rows>0 && rows<Integer.MAX_VALUE && columns>0 && columns<Integer.MAX_VALUE)
        {
            board = new boolean[rows][columns];
            this.rows = rows;
            this.columns = columns;
            this.gameState = GameState.PAUSED;
        } else {
            throw new IndexOutOfBoundsException(String.format("index out of bounds.Allowed values are rows and columns between 1...%s",Integer.MAX_VALUE));
        }
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }

    public void setStartingPosition(int x, int y) throws IndexOutOfBoundsException {
        if(isCellInBounds(x,y)) {
            board[x][y] = true;
        } else {
            throw new IndexOutOfBoundsException(String.format("index out of bounds, cell has to be inside board range. Allowed values are rows 0...%s and columns between 0...%s", rows-1, columns-1));

        }
    }

    public void toggleCell(int x, int y) throws IndexOutOfBoundsException {
        if(isCellInBounds(x,y)) {
            board[x][y] = !board[x][y];
        } else {
            throw new IndexOutOfBoundsException(String.format("index out of bounds, cell does not exist. Allowed values are rows 0...%s and columns between 0...%s", rows-1, columns-1));
        }
    }

    public int getBoardRows() {
        return board.length;
    }

    public int getBoardColumns() {
        return board[0].length;
    }

    public boolean isCellAlive(int x, int y) {
        return board[x][y];
    }

    private boolean isCellInBounds(int x, int y) {
        if(x>rows-1 || x<0 || y>columns-1 || y<0) {
            return false;
        } else {
            return true;
        }
    }
}
