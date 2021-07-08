package indeed.karat.find_word_location;

// Start typing here

import java.util.*;
import java.math.*;
import java.io.*;
// import java.lang.*;

class Solution {


    static List<List<Integer>> find_word_location(Character[][] grid, String word) {

        // i guess is dfs is the answer; but we need to maintain the order; you can go to bottom or right;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == word.charAt(0)) {
                    if (dfs(grid, word, 0, i, j, result)) {
                        return result;
                    } else {
                        result.clear();
                    }
                }
            }
        }


        return result;
    }

    static boolean dfs(Character[][] grid, String word, int index, int i, int j, List<List<Integer>> result) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return false;
        }
        if (grid[i][j] != word.charAt(index)) {
            return false;
        }
//        System.out.println("hiii");
        List<Integer> coordinate = new ArrayList<>();
        coordinate.add(i);
        coordinate.add(j);
        result.add(coordinate);
        if (dfs(grid, word, index + 1, i + 1, j, result) || dfs(grid, word, index + 1, i, j + 1, result)) {
            return true;
        }
        result.remove(coordinate);
//        System.out.println("bi");

        return false;


    }

    public static void main(String[] args) {

        Character[][] grid1 = {
                {'c', 'c', 'c', 'a', 'r', 's'},
                {'c', 'c', 'i', 't', 'n', 'b'},
                {'a', 'c', 'n', 'n', 't', 'i'},
                {'t', 'c', 'i', 'i', 'p', 't'}
        };

        String word1_1 = "catnip";
        System.out.println(find_word_location(grid1, word1_1));//-> [ (0, 2), (0, 3), (1, 3), (2, 3), (3, 3), (3, 4) ]

        System.out.println();


    }

}