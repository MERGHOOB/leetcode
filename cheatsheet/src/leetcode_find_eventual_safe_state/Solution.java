package leetcode_find_eventual_safe_state;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {

        // just apply dfs

        int n = graph.length;
        int[] color = new int[n];  // color == 0(not visited) 1 == safe and 2== not safe

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, color))
                res.add(i);
        }
        return res;
    }

    boolean dfs(int[][] graph, int i, int[] color) {
        if (color[i] != 0) {
            return color[i] == 1;
        }
        color[i] = 2;
        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs(graph, graph[i][j], color))
                return false;
        }
        color[i] = 1;
        return true;
    }
}