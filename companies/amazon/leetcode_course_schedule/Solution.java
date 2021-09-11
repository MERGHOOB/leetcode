package leetcode_course_schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    Map<Integer, Boolean> memo = new HashMap<>();//Memorization HashMap for DFS pruning

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // create a graph
        // if cycle you cannot finish
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : prerequisites) {

            if (edge[0] == edge[1]) {
                return false;
            }

            map.putIfAbsent(edge[1], new ArrayList<>());

            map.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (visited.contains(i)) {
                continue;
            }

            if (dfs(map, visited, recStack, i)) {
                return false;
            }
        }

        return true;


    }


    boolean dfs(Map<Integer, List<Integer>> map, Set<Integer> visited, Set<Integer> recStack, int vertex) {
        if (memo.containsKey(vertex)) {
            return memo.get(vertex);
        }
        if (recStack.contains(vertex)) {
            memo.put(vertex, true);
            return true;
        }

        if (visited.contains(vertex)) {
            memo.put(vertex, false);
            return false;
        }

        //mark visited and put it on recStack
        visited.add(vertex);
        recStack.add(vertex);

        for (Integer neighbour : map.getOrDefault(vertex, new ArrayList<>())) {
            boolean hasCycle = dfs(map, visited, recStack, neighbour);
            memo.put(neighbour, hasCycle);
            if (hasCycle) {
                return true;
            }
        }

        recStack.remove(vertex);
        memo.put(vertex, false);
        return false;

    }


    // Graph is what list of edges;
    // v --> (e1, e2)

    /// Map<K, list<E>>
}