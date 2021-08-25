package lintcode_optimal_account_balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * @param edges: a directed graph where each edge is represented by a tuple
     * @return: the number of edges
     */
    public int balanceGraph(int[][] edges) {

//
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges) {
            map.put(edge[0], map.getOrDefault(edge[0], 0) + edge[2]);
            map.put(edge[1], map.getOrDefault(edge[1], 0) - edge[2]);
        }

        List<Integer> list = new ArrayList<>();
        int res = 0;
        for (Integer val : map.values()) {
            if (val == 0) continue;

            if (list.contains(-val)) {
                res++;
                list.remove(-val);
            }
            list.add(val);
        }

        int[] debts = new int[list.size()];

        return res + dfs(debts, 0);
    }

    private int dfs(int[] debts, int start) {
        if (start == debts.length) {
            return 0;
        }
        if (debts[start] == 0) return dfs(debts, start + 1);

        int res = debts.length;
        for (int i = start + 1; i < debts.length; i++) {

            debts[i] += debts[start];
            res = Math.min(res, 1+ dfs(debts, i + 1));
            debts[i] -= debts[start];
        }
        return res;
    }


}