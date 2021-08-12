package com.margub.game;

import com.margub.constants.Constants;
import com.margub.game.entities.Player;
import com.margub.game.services.JumpService;
import com.margub.game.services.PlayerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderGame {
    private final static SnakeAndLadderGame instance = new SnakeAndLadderGame();
    //services
    PlayerService playerService = new PlayerService();
    JumpService jumpService = new JumpService();
    Map<Integer, Integer> snakes = new HashMap<>();
    Map<Integer, Integer> ladders = new HashMap<>();
    List<String> players = new ArrayList<>();

    private SnakeAndLadderGame() {
    }

    public static SnakeAndLadderGame getInstance() {
        return instance;
    }

    public void init() {

    }

    public void addSnake(String head, String tail) {
        jumpService.add(head, tail);
    }

    public void addLadder(String start, String end) {
        jumpService.add(start, end);
    }

    public void addPlayer(String playerName) {
        playerService.add(playerName);
    }

    public void startGame() {
        List<Player> players = playerService.getPlayers();
        int turn = 0;
        while (true) {
            Player player = players.get(turn);
            turn = (turn + 1) % players.size();

            int advance = player.rollDice();
            if (player.getPosition() + advance > Constants.cellCount) {
                continue;
            }

            int initialPosition = player.getPosition();

            player.setPosition(player.getPosition() + advance);

            player.setPosition(jumpService.getJump(player.getPosition()));

            System.out.println(player + " rolled a " + advance + " and moved from " + initialPosition + " to " + player.getPosition() );
            if (player.getPosition() == Constants.cellCount) {
                System.out.println(player + " won the game");
                return;
            }


        }
    }
}
