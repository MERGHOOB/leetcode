package all_ancestor_of_a_node_in_a_directed_acyclic_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class Solution {


    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        Map<Integer, List<Integer>> curParent = new HashMap<>();

        for (int[] e : edges) {
            curParent.computeIfAbsent(e[1], k -> new ArrayList<>()).add(e[0]);
        }

        List<List<Integer>> result = new ArrayList<>();

        Map<Integer, Set<Integer>> ancestors = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new TreeSet<>(); // because we need sorted order in answer
            collect(i, ancestors, set, curParent);
            result.add(new ArrayList<>(set));
        }

        return result;
    }

    private void collect(int source,
                         Map<Integer, Set<Integer>> ancestors,
                         Set<Integer> set, Map<Integer,
            List<Integer>> curParent) {

        if (ancestors.containsKey(source)) {
            set.addAll(ancestors.get(source));
            return;
        }

        Set<Integer> res = new HashSet<>();
        curParent.getOrDefault(source, new ArrayList<>())
                .forEach(immediateAncestor -> {
                    res.add(immediateAncestor);
                    collect(immediateAncestor, ancestors, res, curParent);
                });
        ancestors.put(source, res);
        set.addAll(res);
    }


}
