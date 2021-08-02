package leetcoe_the_skyline_problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for (int[] building : buildings) {
            points.add(new Point(building[0], building[2], true));
            points.add(new Point(building[1], building[2], false));
        }

        Collections.sort(points);
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // pq.offer(0); // initial height is zero

        TreeMap<Integer, Integer> queue = new TreeMap<>();
        queue.put(0, 1);

        int prevMaxHeight = 0;
        for (Point point : points) {

            if (point.isStart) {

                // pq.offer(point.height);
                queue.compute(point.height, (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });


            } else {

                // pq.remove(point.height); // this is costly
                queue.compute(point.height, (key, value) -> {
                    if (value == null || value == 1) {
                        return null;
                    }
                    return value - 1;
                });

            }

            // if(pq.peek() != prevMaxHeight) {
            //     prevMaxHeight = pq.peek();

            int curMaxHeight = queue.lastKey();
            if (curMaxHeight != prevMaxHeight) {
                prevMaxHeight = curMaxHeight;
                res.add(List.of(point.x, prevMaxHeight));
            }
        }
        return res;
    }

    static class Point implements Comparable<Point> {

        int x;
        int height;
        boolean isStart;


        Point(int x, int height, boolean isStart) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }

        public int compareTo(Point other) {

            if (this.x != other.x) {
                return this.x - other.x;
            } else {
                if (this.isStart && other.isStart) {
                    return other.height - this.height;
                } else if (!this.isStart && !other.isStart) {
                    return this.height - other.height;
                } else {
                    return this.isStart ? -1 : 1;
                }
            }

        }
    }


}
