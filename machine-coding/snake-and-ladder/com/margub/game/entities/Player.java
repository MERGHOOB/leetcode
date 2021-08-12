package com.margub.game.entities;

import java.util.Random;

public class Player {
    String name;
    Long id;
    private int position;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int rollDice() {
        int rand = 0;
        Random random = new Random();
        while (true) {
            rand = random.nextInt(7);
            if (rand != 0) {
                return rand;
            }
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
