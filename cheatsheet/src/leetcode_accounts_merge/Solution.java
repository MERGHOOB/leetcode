package leetcode_accounts_merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        UnionFind unionFind = new UnionFind(accounts.size());

        // Mail to Index
        Map<String, Integer> mailToIndexMap = new HashMap<>();
        //Mail to ownner
        Map<String, String> mailToOwner = new HashMap<>();

        // Step-1: traverse all excepts names, if we have not seen before put it in mailToIndexMap.
        // otherwise- union the email to its parent index
        for (int i = 0; i < accounts.size(); i++) {
            String owner = accounts.get(i).get(0);

            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (mailToIndexMap.containsKey(mail)) {
                    unionFind.union(i, mailToIndexMap.get(mail));
                } else {
                    mailToIndexMap.put(mail, i);
                    mailToOwner.put(mail, owner);
                }
            }
        }

        // Step-2: traverse every email list and find the parent of current-list and put all emails into the set list
        // that belongs to key of its parent index
        Map<Integer, Set<String>> disjointSet = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            // find parent index of current list index in parent array
            int parentIndex = unionFind.find(i);
            disjointSet.putIfAbsent(parentIndex, new HashSet<>());
            Set<String> curSet = disjointSet.get(parentIndex);
            for (int j = 1; j < accounts.get(i).size(); j++) {
                curSet.add(accounts.get(i).get(j));
            }
            disjointSet.put(parentIndex, curSet);
        }

        // Step-3: traverse key set of disjoint set group, retrive all emails from each disjoint set and group them into list
        // and sort them and add them
        List<List<String>> result = new ArrayList<>();
        for (int index : disjointSet.keySet()) {
            List<String> currList = new ArrayList<>(disjointSet.get(index));
            Collections.sort(currList);
            currList.add(0, accounts.get(index).get(0));
            result.add(currList);
        }

        return result;
    }


    static class UnionFind {
        int size;
        int[] parents;

        UnionFind(int size) {
            this.size = size;
            this.parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }

        public void union(int m, int n) {
            parents[find(m)] = parents[find(n)];
        }

        private int find(int n) {
            if (n != parents[n]) {
                parents[n] = find(parents[n]);
            }

            return parents[n];
        }
    }
}