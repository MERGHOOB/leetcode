package com.margub.driver;

import com.margub.game.SnakeAndLadderGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> snakes = new HashMap<>();
//        SnakeAndLadderGame.getInstance().init();
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.next());
        for(int i= 0; i<count; i++) {
            SnakeAndLadderGame.getInstance().addSnake(scanner.next(), scanner.next());
        }
        int ladder = Integer.parseInt(scanner.next());
        for(int i= 0; i<ladder; i++) {
            SnakeAndLadderGame.getInstance().addLadder(scanner.next(), scanner.next());
        }
        int playerCount = Integer.parseInt(scanner.next()) ;
        for(int i = 0; i<playerCount; i++) {
            SnakeAndLadderGame.getInstance().addPlayer(scanner.next());
        }

        SnakeAndLadderGame.getInstance().startGame();
    }
}
