package indeed.karat.find_nodes_with_zeros_and_one_parents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    public List<List<Integer>> findNodesWithZeroAndOneParent(int[][] parentChildPairs) {

        Map<Integer, Integer> childParentMap = new HashMap<>();
        Set<Integer> parents = new HashSet<>();
        for (int[] pair : parentChildPairs) {
            childParentMap.put(pair[1], childParentMap.getOrDefault(pair[1], 0) + 1);
            parents.add(pair[0]);
        }

        List<Integer> nodesWithOneParent = new ArrayList<>();
        List<Integer> nodesWithZeroParent = new ArrayList<>();

        childParentMap.forEach((child, count) -> {
            if (count == 1) {
                nodesWithOneParent.add(child);
            }
        });

        for (Integer node : parents) {
            if (childParentMap.containsKey(node)) {
                continue;
            }
            nodesWithZeroParent.add(node);
        }

        return List.of(nodesWithZeroParent, nodesWithOneParent);
    }


}
