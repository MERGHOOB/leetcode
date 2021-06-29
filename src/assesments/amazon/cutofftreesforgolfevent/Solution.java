package assesments.amazon.cutofftreesforgolfevent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */
class Solution {


    public int cutOffTree(List<List<Integer>> forest) {

        PriorityQueue<Tree> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Tree::getValue));

        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    priorityQueue.add(new Tree(i, j, forest.get(i).get(j)));
                }
            }
        }

        // start with 00
        Tree source = new Tree(0, 0, forest.get(0).get(0));
        int minSteps = 0;

        while (!priorityQueue.isEmpty()) {
            Tree destination = priorityQueue.poll();
            int steps = traverse(forest, source, destination);
            if (steps == -1) {
                return -1;
            }
            minSteps += steps;
            source = destination;
            forest.get(source.x).set(source.y, 1);
        }

        return minSteps;
    }

    private int traverse(List<List<Integer>> forest, Tree source, Tree destination) {

        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];

        Queue<Tree> toVisit = new LinkedList<>();
        toVisit.add(source);
        toVisit.add(null); // to mark a level
        int level = 0;
        while (!toVisit.isEmpty()) {
            Tree poll = toVisit.poll();
            if (poll == null) {
                if (toVisit.peek() != null) {
                    level++; // one level is reached
                    toVisit.add(null);
                    continue;
                } else {
                    return -1; // we are not able to reach destination
                }
            }
            if (poll.equals(destination)) {
                return level;
            }

            int[] r = {-1, 1, 0, 0};
            int[] c = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                int row = poll.x + r[i];
                int col = poll.y + c[i];

                if (row < 0 || row >= forest.size() ||
                        col < 0 || col >= forest.get(row).size() || visited[row][col] ||
                        forest.get(row).get(col) == 0) {
                    continue;
                }
                Tree nextTree = new Tree(row, col, forest.get(row).get(col));
                visited[row][col] = true;
                toVisit.add(nextTree);
            }

        }
        return level;
    }


    private static class Tree {
        int x;
        int y;

        public int getValue() {
            return value;
        }

        int value;

        public void setLength(int length) {
            this.length = length;
        }

        int length = 0;

        Tree(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.length = 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Tree)) {
                return false;
            }

            Tree tree = (Tree) obj;
            return tree.value == this.value; // values are unique(guaranteed)
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();

        Integer[][] forest = {{1, 2, 3}, {0, 0, 4}, {7, 6, 5}};
        /*
        {{1,2,3},{0,0,0},{7,6,5}}
{{2,3,4},{0,0,5},{8,7,6}}
         */
//        Integer[][] forest =  {{1,2,3},{0,0,0},{7,6,5}};
//        Integer[][] forest = {{54581641, 64080174, 24346381, 69107959},
//                {86374198, 61363882, 68783324, 79706116},
//                {668150, 92178815, 89819108, 94701471},
//                {83920491, 22724204, 46281641, 47531096},
//                {89078499, 18904913, 25462145, 60813308}};
        for (Integer[] st : forest) {
            lists.add(Arrays.asList(st));
        }
        int len = new Solution().cutOffTree(lists);
        System.out.println(len);

    }
}