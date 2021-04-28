package leetcodepractice.graph.criticalconnectionsinnetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 * Example 1:
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * n - 1 <= connections.length <= 105
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * There are no repeated connections.
 * Accepted
 * 113,786
 * Submissions
 * 221,425
 */
class Solution {
    private static int time = 0;
    boolean [] ap ;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        int [] disc = new int[n];
        int [] low = new int[n];
        int  [] parents = new int[n];
        ap = new boolean[n];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer>[] adjacencyList = createGraph(n, connections);

        for(int vertex = 0; vertex<n; vertex++) {
            if(disc[vertex] ==0) {
                parents[vertex] = -1;
                criticalConnectionsUsingDFS(vertex, adjacencyList, disc, low, parents, result);
            }
        }

        return result;
    }



    private void criticalConnectionsUsingDFS(int curr,
                                             List<Integer>[] graph,
                                             int[] disc,
                                             int[] low,
                                             int[] parents,
                                             List<List<Integer>> result) {
        disc[curr] = low[curr] = ++time;
        int children = 0; // it is for articulation point
        for(int next: graph[curr]) {
            if(disc[next] == 0) { // it means it is not visited yet
                    children ++;
                    parents[next] = curr;
                    criticalConnectionsUsingDFS(next, graph,disc, low, parents, result);
                    low[curr] = Integer.min(low[curr], low[next]);
                    //start articulation point -- not necessary if you interested only in cutEdge
                        if(parents[curr] == -1 && children > 1) { // root of dfs and more than one independent children
                            ap[curr] = true;
                        }
                        if(parents[curr] != -1  && disc[curr] <= low[next]) { // discovery of curr is less than or equals
                            // to any of its children then it is articluation point
                            ap[curr] = true;
                        }

                //end - Articulation point
                    if(low[next] > disc[curr]) {
                        // curr --> next is cut edge/bridge
                        List<Integer> cutEdge = new ArrayList<>();
                        cutEdge.add(curr);
                        cutEdge.add(next);
                        result.add(cutEdge);
                    }
            }
            else if( next != parents[curr]) { // it is not parent
                low[curr] = Integer.min(low[curr], disc[next]);
            }
        }




    }

    private List<Integer>[] createGraph(int n, List<List<Integer>> connections) {
        ArrayList<Integer>[] adj = new ArrayList[n];
        connections.forEach(connection ->  {
            if(adj[connection.get(0)] == null) {
                adj[connection.get(0)] = new ArrayList<>();
            }
            adj[connection.get(0)].add(connection.get(1));

            if(adj[connection.get(1)] == null) {
                adj[connection.get(1)] = new ArrayList<>();
            }
            adj[connection.get(1)].add(connection.get(0));
        });
        return adj;
    }

    public static void main(String[] args) {
        List<List<Integer> > connections = new ArrayList<>();

        connections.add(getArraylist(0,1));
        connections.add(getArraylist(1,2));
        connections.add(getArraylist(0,2));

        new Solution().criticalConnections(3, connections);
    }

    private static List<Integer> getArraylist(int x, int y) {
        ArrayList<Integer> e = new ArrayList<>();
        e.add(x);
        e.add(y);
        return e;
    }
}
