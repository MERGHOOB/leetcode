package leetcodepractice.graph.cheapestflight;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class Solution {

    private static class Holder {
        public Holder(int node, int distance, int stop) {
            this.node = node;
            this.distance = distance;
            this.stop = stop;
        }

        int node, distance, stop;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < flights.length; i++) {
            graph[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        int[] visited = new int[n]; // to compute the distance
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[src] = 0;

        Deque<Holder> deque = new LinkedList<>();
        deque.addLast(new Holder(0, 0, 0));

        while (!deque.isEmpty()) {

            Holder holder = deque.removeFirst();

            for (int i = 0; i < n; i++) {
                if (graph[holder.node][i] != 0
                        && holder.stop <= K
                        && visited[i] > visited[holder.node] + graph[holder.node][i]) {

                    visited[i] = visited[holder.node] + graph[holder.node][i];
                    deque.addLast(new Holder(i, visited[i], holder.stop + 1));
                }
            }

        }
        return visited[dst] == Integer.MAX_VALUE ? -1 : visited[dst];
    }

}
