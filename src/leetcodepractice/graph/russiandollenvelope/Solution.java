package leetcodepractice.graph.russiandollenvelope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    private static class GraphVertex {
        List<GraphVertex> edges;
        int distance = 1; // it means that it is not enveloped in any yet so the path = 1 initially
        boolean visited = false; // to run DFS on this.

        public GraphVertex(int label) {
            this.label = label;
            edges = new ArrayList<>();

        }

        int label;

    }

    public int maxEnvelopes(int[][] envelopes) {

        List<GraphVertex> graphVertices = buildGraph(envelopes);
        Deque<GraphVertex> topologicallySortedVertices = topologicalSort(graphVertices);


        return findLongestPath(topologicallySortedVertices);
    }

    private int findLongestPath(Deque<GraphVertex> topologicallySortedVertices) {
        int longestPath = 0;
        while (!topologicallySortedVertices.isEmpty()) {
            GraphVertex peekFirst = topologicallySortedVertices.peekFirst();
            longestPath = Integer.max(longestPath, peekFirst.distance);

            for (GraphVertex neighbour : peekFirst.edges) {
                neighbour.distance = Integer.max(neighbour.distance, peekFirst.distance + 1);
            }
            topologicallySortedVertices.removeFirst();
        }
        return longestPath;
    }

    private Deque<GraphVertex> topologicalSort(List<GraphVertex> graphVertices) {
        Deque<GraphVertex> orderedVertices = new LinkedList<>();
        for (GraphVertex graphVertex : graphVertices) {
            if (graphVertex.visited) {
                continue;
            }
            dfs(graphVertex, orderedVertices);
        }
        return orderedVertices;
    }

    private void dfs(GraphVertex graphVertex, Deque<GraphVertex> orderedVertices) {
        graphVertex.visited = true;
        for (GraphVertex g : graphVertex.edges) {
            if (g.visited) {
                continue;
            }
            dfs(g, orderedVertices);
        }
        orderedVertices.addFirst(graphVertex);
    }

    private List<GraphVertex> buildGraph(int[][] envelopes) {
        Map<Integer, GraphVertex> vertexMap = new HashMap<>();
        if(envelopes.length == 0) {
            return Collections.emptyList();
        }
        vertexMap.put(0, new GraphVertex(0));


        for (int i = 0; i < envelopes.length; i++) {
            for (int j = i + 1; j < envelopes.length; j++) {
                int f = i;
                int s = j;

                vertexMap.putIfAbsent(f, new GraphVertex(f));
                vertexMap.putIfAbsent(s, new GraphVertex(s));

                int[] first = envelopes[i];
                int[] second = envelopes[j];

                if (first[0] > second[0] && first[1] > second[1]) {
                    int temp = f;
                    f = s;
                    s = temp;
                } else if (first[0] < second[0] && first[1] < second[1]) {
                    //it is not to change first and second places
                } else {
                    continue;
                }
                GraphVertex src = vertexMap.get(f);
                GraphVertex dest = vertexMap.get(s);

                src.edges.add(dest);

            }

        }
        return new ArrayList<>(vertexMap.values());
    }

    public static void main(String[] args) {
//        int [][] envelopes = {{1,1}, {1,1}, {1,1}};
        int [][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(new Solution().maxEnvelopes(envelopes));
    }
}