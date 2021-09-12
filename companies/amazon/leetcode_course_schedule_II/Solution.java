package leetcode_course_schedule_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        int[][] pre = {{1, 0}};
        int[] order = new Solution().findOrder(2, pre);
        for (int val : order) {
            System.out.println(val);
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> visited = new HashMap<>();


        for (int[] pre : prerequisites) {

            graph.putIfAbsent(pre[0], new ArrayList<>());
            graph.get(pre[0]).add(pre[1]);

        }

        try {
            for (int i = 0; i < numCourses; i++) {
                dfs(graph, i, visited, res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return new int[0];
        }

        int[] array = new int[res.size()];
        int x = 0;
        for (int val : res) {
            array[x++] = val;
        }

        return array;

    }

    public void dfs(Map<Integer, List<Integer>> graph,
                    int course,
                    Map<Integer, Integer> visited,
                    List<Integer> res) throws Exception {

        if (visited.containsKey(course)) {

            if (visited.get(course) == 0) {
                // it is already in rec-stack it means there is a cycle;
                throw new Exception("Cycle is present");
            }

            return;
        }

        visited.put(course, 0);
        for (int neighbour : graph.getOrDefault(course, new ArrayList<>())) {
            dfs(graph, neighbour, visited, res);
        }

        res.add(course);
        visited.put(course, 1);
        System.out.println("course: " + course + " value: " + visited.get(course));

    }
}