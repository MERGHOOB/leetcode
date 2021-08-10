package leetcode_graph_valid_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * @param n:     An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        int [][] input  = {{1,3},{2,6},{8,10},{15,18}};
        List<List<Integer>> list  = new ArrayList<>();
        for(int [] in: input) {
            List<Integer> temp  = new ArrayList<>();
            for(int x: in) {
                temp.add(x);
            }
            list.add(temp);
        }
        if (edges.length + 1 != n) {
            return false;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {

            if (!uf.union(edge[0], edge[1])) {
                return false;
            }
        }

        // if it is connected.
        return uf.component == 1;
    }

    static class UnionFind {
        int[] parent;
        int size;
        int component;

        UnionFind(int n) {
            size = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            component = n;
        }

        boolean union(int a, int b) {
            int pu = find(a);
            int pb = find(b);

            if (pu == pb) {
                return false;
            }

            parent[pb] = pu;
            component--;
            return true;
        }

        int find(int x) {
            int a = x;
            while (a != parent[a]) {
                a = parent[a];
            }
            return a;
        }
    }
}
