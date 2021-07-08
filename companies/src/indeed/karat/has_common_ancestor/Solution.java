package indeed.karat.has_common_ancestor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class Solution {

    public boolean hasCommonAncestor(int[][] parentChildPairs, Integer first, Integer second) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] pair : parentChildPairs) {
            map.putIfAbsent(pair[1], new ArrayList<>());
            map.get(pair[1]).add(pair[0]);
        }

        List<Integer> firstParents = new ArrayList<>();
        List<Integer> secondParents = new ArrayList<>();
        if (map.containsKey(first) && map.containsKey(second)) {

            dfs(map, first, new HashSet<>(), firstParents);
            dfs(map, second, new HashSet<>(), secondParents);


        }
//        firstParents.remove(first);
//        secondParents.remove(second);

        for(Integer firstParent: firstParents) {
            for(Integer secondParent: secondParents) {
                if(firstParent.equals(secondParent)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void dfs(Map<Integer, List<Integer>> map, int node, HashSet<Integer> visited, List<Integer> firstParents) {

        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        firstParents.add(node);
        if (!map.containsKey(node)) {
            return;
        }
        for (Integer parent : map.get(node)) {
            dfs(map, parent, visited, firstParents);


        }
    }


//    public static void main(String[] args) {
//        int[][] pairs = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9}};
//        System.out.println(new Solution().hasCommonAncestor(pairs, 3, 8));// => false
//        System.out.println(new Solution().hasCommonAncestor(pairs, 5, 8));// => true
//        System.out.println(new Solution().hasCommonAncestor(pairs, 6, 8));// => true
//        System.out.println(new Solution().hasCommonAncestor(pairs, 6, 9));// => true
//        System.out.println(new Solution().hasCommonAncestor(pairs, 1, 3));// => false
//        System.out.println(new Solution().hasCommonAncestor(pairs, 3, 1));// => false
//        System.out.println(new Solution().hasCommonAncestor(pairs, 7, 11));//) => true
//        System.out.println(new Solution().hasCommonAncestor(pairs, 6, 5));// => true
//        System.out.println(new Solution().hasCommonAncestor(pairs, 5, 6));// => true
//        System.out.println(new Solution().hasCommonAncestor(pairs, 4, 13));// => false
//
//    }


}
