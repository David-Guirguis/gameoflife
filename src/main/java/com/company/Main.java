package com.company;
import com.company.controller.GameOfLifeController;
import com.company.model.Pair;
import com.company.view.GameOfLifeView;

import java.util.ArrayList;

public class Main   {

    public static void main(String[] args) {

        GameOfLifeController gameOfLifeController = new GameOfLifeController(10,20);

        ArrayList<Pair> startingPositions = new ArrayList<>();
        startingPositions.add(new Pair(2,1));
        startingPositions.add(new Pair(2,2));
        startingPositions.add(new Pair(2,3));
        startingPositions.add(new Pair(1,3));
        startingPositions.add(new Pair(0,2));
        gameOfLifeController.setStartingPositions(startingPositions);

        GameOfLifeView gameOfLifeView = new GameOfLifeView(gameOfLifeController);
        gameOfLifeView.run();

    }


}
