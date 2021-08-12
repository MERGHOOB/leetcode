package com.margub.game.services;

import com.margub.game.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {
    public static final int INITIAL_POSITION = 0;
    List<Player> players = new ArrayList<>();
    private Long ID = 0L;

    public List<Player> getPlayers() {
        return players;
    }

    public void add(String playerName) {
        //Assumption: that player with same comes they will be considered different
        Player player = new Player();
        player.setPosition(INITIAL_POSITION);
        player.setName(playerName);
        player.setId(++ID);
        players.add(player);
    }
}
