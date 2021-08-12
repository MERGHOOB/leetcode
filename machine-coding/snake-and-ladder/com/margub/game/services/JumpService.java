package com.margub.game.services;

import java.util.HashMap;
import java.util.Map;

public class JumpService {
    Map<Integer, Integer> jumps = new HashMap<>();

    public void add(String start, String end) {
        try {
            validate(start);
            validate(end);
            jumps.put(Integer.parseInt(start), Integer.parseInt(end));
        } catch (NumberFormatException nfe) {
            System.out.println("This jump is not valid");
        }
    }

    private void validate(String val) {
//        if()
    }

    public int getJump(int position) {
        return jumps.getOrDefault(position, position);
    }
}
