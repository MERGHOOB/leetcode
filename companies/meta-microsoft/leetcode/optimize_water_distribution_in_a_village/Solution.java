package leetcode.optimize_water_distribution_in_a_village;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Solution {

    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
        /*
		 N = 3;
		 WELLS[] = [1,2,2]
		 PIPES[] = { {1,2,1}, {2,3,1}		}

		  1 - 2  = 1;
		  2 - 3 = 1;
		  0 -1 = 1;
		  0 - 2 = 2;
		  0- 3 = 2;

		*/
        List<Edge> costs = new ArrayList<>();
        for(int i = 0; i<wells.length; i++) {
            costs.add(new Edge(wells[i], 0, i+1 ));
        }

        for(int [] pipe : pipes) {
            costs.add(new Edge(pipe[2], pipe[1], pipe[0]));
        }
        Collections.sort(costs);
        int minCosts = 0;

        UnionFind uf = new UnionFind(n);
        for(Edge edge: costs) {
            int rootX = uf.find(edge.from);
            int rootY = uf.find(edge.to);
            if(rootX == rootY) continue;

            minCosts += edge.cost;
            uf.union(rootX, rootY);
            n--;
            if(n == 0) {
                return minCosts;
            }
        }
        return minCosts;


    }

    private static class Edge implements Comparable<Edge> {

        int cost;
        int from;
        int to;

        Edge(int cost,int from, int to) {
            this.cost = cost;
            this.from = from;
            this.to = to;
        }

        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }

    }
    private static class UnionFind {
        int [] parent;
        int [] rank;

        UnionFind(int n) {
            parent = new int[n+1];
            for(int i= 0 ; i<=n; i++) {
                parent[i] = i;
            }
            rank = new int[n+1];
        }

        int find(int x) {
            return x == parent[x]? x : find(parent[x]);
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if(px == py) return;
            if(rank[px] >= rank[py]) {
                parent[py] = px;
                rank[px] += rank[py];
            } else {
                parent[px] = py;
                rank[py] += rank[px];
            }
        }
    }

}