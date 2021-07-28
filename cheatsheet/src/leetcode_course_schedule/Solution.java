package leetcode_course_schedule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
    Map<Integer, Boolean> map = new HashMap<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {


        return canFinishWithTOPOSORT(numCourses, prerequisites);
//        return solutionWithDFS(numCourses, prerequisites);

    }

    private boolean canFinishWithTOPOSORT(int numCourses, int[][] prerequisites) {

        // convert graph representation of edges to in-degree of adjacent list
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // How many course does not need pre-requisite
        int canFinishCount = queue.size();
        while (!queue.isEmpty()) {
            int prerequisite = queue.remove(); // Already finished this pre-requisite course

            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisite == prerequisites[i][1]) {
                    inDegree[prerequisites[i][0]]--;

                    if (inDegree[prerequisites[i][0]] == 0) {
                        canFinishCount++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }

        }

        return canFinishCount == numCourses;
    }

    private boolean solutionWithDFS(int numCourses, int[][] prerequisites) {
        boolean[][] graph = new boolean[numCourses][numCourses];

        for (int[] edge : prerequisites) {
            graph[edge[1]][edge[0]] = true;
        }

        // check if cycle is present
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < numCourses; j++) {
                if (graph[i][j]) {
                    if (dfs(graph, i, visited)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean dfs(boolean[][] graph, int start, Set<Integer> visited) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        if (visited.contains(start)) {
            map.put(start, true);
            return true;
        }

        visited.add(start);

        for (int i = 0; i < graph.length; i++) {

            if (graph[start][i]) {
                if (dfs(graph, i, visited)) {
                    map.put(i, true);
                    return true;
                }
            }
        }
        visited.remove(start);

        map.put(start, false);
        return false;

    }

}
